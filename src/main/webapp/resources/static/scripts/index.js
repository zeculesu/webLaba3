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

function renderAll() {
    let r = document.querySelector("[id$='coordForm:r_input']").value;
    draw(r);
    drawAllPoints();
}

document.addEventListener("DOMContentLoaded", function () {
    renderAll();
});

document.addEventListener("DOMContentLoaded", function() {
    const xButtons = document.querySelectorAll(".button-link");

    xButtons.forEach(button => {
        button.addEventListener("click", function() {
            xButtons.forEach(btn => btn.classList.remove("active"));
            button.classList.add("active");
        });
    });
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
    const r = document.querySelector("[id$='coordForm:r_input']").value;

    console.log(x, y, r);

    if (checkY(y) && checkX(x)) {
        document.getElementById('canvasX').value = x;
        document.getElementById('canvasY').value = y;

        document.getElementById('canvasR').value = r;

        document.getElementById('submitFormCanvas').click();
        renderAll();
    }
});