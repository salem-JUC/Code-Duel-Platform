// فتح النوافذ
function openPopup(popupType) {
    const allPopups = document.querySelectorAll(' .wSetting');
    allPopups.forEach(popup => popup.classList.remove('active-popup-S'));
    
    if(popupType === 'login') {
        document.querySelector('.wrapper').classList.add('active-popup-S');
    } else if(popupType === 'settings') {
        document.querySelector('.wSetting').classList.add('active-popup-S');
    }
}

// الأحداث
document.querySelector('.btnlogin-popup').addEventListener('click', () => openPopup('login'));
document.querySelector('#settingsBtn').addEventListener('click', () => openPopup('settings'));

// إغلاق النوافذ
document.querySelectorAll('.icon-close, .icon-close-Settings').forEach(btn => {
    btn.addEventListener('click', () => {
        btn.closest(' .wSetting').classList.remove('active-popup-S');
    });
});