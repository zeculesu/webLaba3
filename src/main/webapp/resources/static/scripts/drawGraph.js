let selectedR = null;

function draw(radius) {
    if (radius) {
        selectedR = radius;
        sessionStorage.setItem('selectedR', selectedR);
    }

    const canvas = document.getElementById('graphCanvas');
    const ctx = canvas.getContext('2d');

    const width = canvas.width;
    const height = canvas.height;

    clearCanvas(ctx, width, height);

    const centerX = width / 2;
    const centerY = height / 2;
    const R = radius * width / 6 / 2;
    const step = width / 6 / 2;

    drawCircle(ctx, 2, centerX, centerY, R);
    drawTriangle(ctx, 3, centerX, centerY, R / 2, R);
    drawRectangle(ctx, 4, centerX, centerY, R, R, R);
    drawCoordinateGrid(ctx, centerX, centerY, width, height, step);

    // if (!isNaN(getInputR()) || getInputR() !== null) {
    //     canvas.onclick = e => {
    //         handleSubmit(e, [Math.round((e.offsetX - centerX) / step)], (centerY - e.offsetY) / step).then();
    //     }
    // } else {
    //     showNotification("Не выбран радиус");
    // }
}

function clearCanvas(ctx, width, height) {
    ctx.clearRect(0, 0, width, height);
}

function drawPoint(x, y, hit) {
    const canvas = document.getElementById('graphCanvas');
    const ctx = canvas.getContext('2d');
    const width = canvas.width;
    const height = canvas.height;
    const centerX = width / 2;
    const centerY = height / 2;
    const step = width / 6 / 2;

    let circle = new Path2D();
    circle.arc(centerX + x * step, centerY - y * step, 3, 0, 2 * Math.PI);
    if (hit) {
        ctx.fillStyle = "#25CE00";
    } else {
        ctx.fillStyle = "#EA526F";
    }
    ctx.fill(circle);
}

function drawAllPoints() {
    getPoints().forEach(function (point) {
        drawPoint(point.x, point.y, point.hit);
    });
}

function drawRectangle(ctx, quarter, centerX, centerY, radius, sideByX, sideByY) {
    ctx.beginPath();
    switch (quarter) {
        case 1:
            ctx.rect(centerX, centerY - sideByY, sideByX, sideByY);
            break;
        case 2:
            ctx.rect(centerX - sideByX, centerY - sideByY, sideByX, sideByY);
            break
        case 3:
            ctx.rect(centerX - sideByX, centerY, sideByX, sideByY);
            break
        case 4:
            ctx.rect(centerX, centerY, sideByX, sideByY);
            break
    }
    ctx.closePath();
    ctx.fillStyle = '#25CED1';
    ctx.fill();
}

function drawCircle(ctx, quarter, centerX, centerY, radius) {
    ctx.beginPath();
    ctx.moveTo(centerX, centerY);
    ctx.arc(centerX, centerY, radius, Math.PI * (4 - quarter) / 2, Math.PI * (5 - quarter) / 2);
    ctx.lineTo(centerX, centerY);
    ctx.closePath();
    ctx.fillStyle = '#25CED1';
    ctx.fill();
}

function drawTriangle(ctx, quarter, centerX, centerY, sideByX, sideByY) {
    ctx.beginPath();
    ctx.moveTo(centerX, centerY);
    switch (quarter) {
        case 1:
            ctx.lineTo(centerX, centerY - sideByY);
            ctx.lineTo(centerX + sideByX, centerY);
            break;
        case 2:
            ctx.lineTo(centerX + sideByX, centerY);
            ctx.lineTo(centerX, centerY + sideByY);
            break
        case 3:
            ctx.lineTo(centerX, centerY + sideByY);
            ctx.lineTo(centerX - sideByX, centerY);
            break
        case 4:
            ctx.lineTo(centerX - sideByX, centerY);
            ctx.lineTo(centerX, centerY - sideByY);
            break
    }
    ctx.closePath();
    ctx.fillStyle = '#25CED1';
    ctx.fill();
}

function drawCoordinateGrid(ctx, centerX, centerY, width, height, step) {
    ctx.strokeStyle = 'black';
    ctx.lineWidth = 2;
    ctx.beginPath();
    ctx.moveTo(centerX, 0);
    ctx.lineTo(centerX, height);
    ctx.moveTo(0, centerY);
    ctx.lineTo(width, centerY);
    ctx.stroke();

    ctx.lineWidth = 1;
    for (let i = 1; i <= 11; i++) {
        ctx.beginPath();
        ctx.moveTo(i * step, centerY - 5);
        ctx.lineTo(i * step, centerY + 5);
        ctx.stroke();

        ctx.beginPath();
        ctx.moveTo(centerX - 5, i * step);
        ctx.lineTo(centerX + 5, i * step);
        ctx.stroke();
    }
    ctx.lineWidth = 1.5;
    ctx.beginPath();
    ctx.moveTo(centerX - 8, 10);
    ctx.lineTo(centerX, 1);
    ctx.lineTo(centerX + 8, 10);
    ctx.stroke();

    ctx.lineWidth = 1.5;
    ctx.beginPath();
    ctx.moveTo(centerX * 2 - 10, centerY - 8);
    ctx.lineTo(centerX * 2 - 1, centerY);
    ctx.lineTo(centerX * 2 - 10, centerY + 8);
    ctx.stroke();

    ctx.font = '12px Arial';
    ctx.fillStyle = 'black';
    for (let i = -5; i <= 5; i++) {
        if (i !== 0) {
            ctx.fillText(i, centerX + i * step - 5, centerY + 20);
            ctx.fillText(-i, centerX - 20, centerY + i * step + 5);
        }
    }
    ctx.font = 'bold 12px Arial';
    ctx.fillText("X", centerX + 5.8 * step - 5, centerY + 20);
    ctx.fillText("Y", centerX - 20, centerY - 5.8 * step + 5);
}
