const wrapper = document.querySelector('.wrapper');
const registerLink = document.querySelector('.register-link');
const loginLink = document.querySelector('.login-link');
const btPopupL = document.querySelector('.btnlogin-popup');
const btPopupR = document.querySelector('.btnregister-popup');
const iconClose = document.querySelector('.icon-close');

registerLink.onclick = () => {
    wrapper.classList.add('activeR'); // Switch to Register form
    wrapper.classList.remove('active');
};

loginLink.onclick = () => {
    wrapper.classList.remove('activeR');
    wrapper.classList.add('active'); // Switch to Login form
};

btPopupL.onclick = () => {
    wrapper.classList.add('active-popup'); // Show popup
    wrapper.classList.add('active'); // Default to Login form
};

btPopupR.onclick = () => {
    wrapper.classList.add('active-popup'); // Show popup
    wrapper.classList.add('activeR'); // Switch to Register form
};

iconClose.onclick = () => {
    wrapper.classList.remove('active-popup');
    wrapper.classList.remove('active');
    wrapper.classList.remove('activeR');
};
iconClose.onclick = () => {
    wrapper.style.transition = 'none'; // Disable transition immediately
    wrapper.classList.remove('active-popup');
    wrapper.classList.remove('active');
    wrapper.classList.remove('activeR');

    // Re-enable transition after a short delay
    setTimeout(() => {
        wrapper.style.transition = 'opacity 0.3s ease, transform 0.3s ease';
    });
};
