const a = document.querySelector(".showPassword");
const input = document.getElementById("password");
const icon = document.getElementById("i");
a.addEventListener('click', () => {
    if (input.getAttribute('type') === 'password') {
        input.removeAttribute('type');
        input.setAttribute('type', 'text');
        icon.className = 'far fa-eye-slash';
    } else {
        input.removeAttribute('type');
        input.setAttribute('type', 'password');
        icon.className = 'far fa-eye';
    }
})