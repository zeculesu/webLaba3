// "use strict";
function getPoints() {
    let points = [];
    let rows = document.querySelectorAll('#resultTable tbody tr');

    rows.forEach(row => {
        let cells = row.querySelectorAll('td');
        let point = {
            x: parseFloat(cells[0].innerText.trim()),
            y: parseFloat(cells[1].innerText.trim()),
            r: parseFloat(cells[2].innerText.trim()),
            hit: cells[3].innerText.trim() === 'Да'
        };
        points.push(point);
    });

    return points;
}

function renderAll(r) {
    if (r) {
        draw(r);
    } else {
        const savedR = sessionStorage.getItem('selectedR');
        if (savedR !== null) {
            selectedR = parseFloat(savedR);
            draw(selectedR);
        } else {
            draw();
        }
    }
    drawAllPoints();
}

document.addEventListener("DOMContentLoaded", function () {
    renderAll();
});

document.getElementById('graphCanvas').addEventListener('click', function (event) {
    const canvas = event.target;

    const height = canvas.height;
    const width = canvas.width;

    const step = width / 6 / 2;
    const centerX = width / 2;
    const centerY = height / 2;

    const x = Math.round((event.offsetX - centerX) / step);
    const y = (centerY - event.offsetY) / step;

    console.log(x, y, selectedR);

    if (checkY(y) && checkX(x)) {
        document.getElementById('canvasX').value = x;
        document.getElementById('canvasY').value = y;

        document.getElementById('canvasR').value = selectedR;

        document.getElementById('submitFormCanvas').click();
        renderAll();
    }
});