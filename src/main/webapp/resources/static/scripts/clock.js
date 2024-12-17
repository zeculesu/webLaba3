function drawClock() {
    const canvas = document.getElementById('clock');
    const ctx = canvas.getContext('2d');
    const centerX = canvas.width / 2;
    const centerY = canvas.height / 2;
    const radius = 120;
    ctx.clearRect(0, 0, canvas.width, canvas.height);

    ctx.beginPath();
    ctx.arc(centerX, centerY, radius, 0, Math.PI * 2);
    ctx.strokeStyle = '#000';
    ctx.lineWidth = 4;
    ctx.stroke();

    ctx.font = '20px Arial';
    ctx.textAlign = 'center';
    ctx.textBaseline = 'middle';

    for (let i = 0; i < 12; i++) {
        const angle = (Math.PI / 6) * (i - 3);
        const x = centerX + (radius - 30) * Math.cos(angle);
        const y = centerY + (radius - 30) * Math.sin(angle);
        ctx.fillText((12 - i).toString(), x, y);
    }

    const now = new Date();
    const hours = now.getHours() % 12;
    const minutes = now.getMinutes();
    const seconds = now.getSeconds();

    drawHand(ctx, centerX, centerY, -(hours + minutes / 60) * (Math.PI / 6) + Math.PI * 3 / 2, radius * 0.5, 6, 'black');

    drawHand(ctx, centerX, centerY, -(minutes + seconds / 60) * (Math.PI / 30) + Math.PI * 3 / 2, radius * 0.7, 4, 'black');

    drawHand(ctx, centerX, centerY, -seconds * (Math.PI / 30) + Math.PI * 3 / 2, radius * 0.8, 2, 'red');
}

function drawHand(ctx, centerX, centerY, angle, length, width, color) {
    const x = centerX + length * Math.cos(angle);
    const y = centerY + length * Math.sin(angle);
    ctx.beginPath();
    ctx.moveTo(centerX, centerY);
    ctx.lineTo(x, y);
    ctx.strokeStyle = color;
    ctx.lineWidth = width;
    ctx.stroke();
}

setInterval(drawClock, 9000);

drawClock();