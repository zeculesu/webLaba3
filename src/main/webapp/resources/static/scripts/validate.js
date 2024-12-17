function checkY(y) {
    if (y < -5 || y > 3) {
        showNotification("Значение X должно быть в диапазоне от -5 до 3");
        return false;
    }
    return true;
}

function checkX(x) {
    if (x < -5 || x > 3) {
        showNotification("Значение X должно быть в диапазоне от -5 до 3");
        return false;
    }
    return true;
}

function checkR(r) {
    if (r === null) {
        showNotification("Выберите значение для R!");
        return false;
    }
    const rNumber = parseInt(r);
    if (isNaN(rNumber)) {
        showNotification("R должно быть числом!");
        return false;
    }
    if (rNumber < 1 || rNumber > 5) {
        showNotification("Значение R должно быть в диапазоне от 1 до 5");
        return false;
    }
    return true;
}

function showNotification(message) {
    const container = document.getElementById('notification-container');

    const notification = document.createElement('div');
    notification.classList.add('notification');
    notification.textContent = message;

    container.prepend(notification);

    setTimeout(() => {
        notification.classList.add('hide');
        setTimeout(() => {
            notification.remove();
        }, 500);
    }, 3000);
}
