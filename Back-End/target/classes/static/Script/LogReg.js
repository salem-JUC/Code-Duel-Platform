document.addEventListener('DOMContentLoaded', () => {
    // العناصر الرئيسية
    const wrapper = document.querySelector('.wrapper');
    const wSetting = document.querySelector('.wSetting');
    const registerLink = document.querySelector('.register-link');
    const loginLink = document.querySelector('.login-link');
    const btnLogin = document.querySelector('.btnlogin-popup');
    const btnRegister = document.querySelector('.btnregister-popup');
    const btnSettings = document.getElementById('settingsBtn');
    const iconClose = document.querySelectorAll('[class*="icon-close"]');

    // إدارة حالة النوافذ
    const popupManager = {
        activePopup: null,

        open: (popup) => {
            // لا تعيد فتح نفس النافذة لو هي مفتوحة أصلاً
            if (popupManager.activePopup === popup) return;
            popupManager.closeAll();
            popup.classList.add('active-popup');
            popupManager.activePopup = popup;
        },

        closeAll: () => {
            document.querySelectorAll('.active-popup').forEach(popup => {
                popup.classList.remove('active-popup');
            });
            popupManager.activePopup = null;
        },

        close: (popup) => {
            popup.classList.remove('active-popup');
            if (popupManager.activePopup === popup) {
                popupManager.activePopup = null;
            }
        }
    };

    // أحداث فتح النوافذ
    btnLogin?.addEventListener('click', (e) => {
        e.stopPropagation();
        popupManager.closeAll();
        wrapper.classList.add('active');
        wrapper.classList.remove('activeR');
        popupManager.open(wrapper);
    });
    
    btnRegister?.addEventListener('click', (e) => {
        e.stopPropagation();
        popupManager.closeAll();
        wrapper.classList.add('activeR');
        wrapper.classList.remove('active');
        popupManager.open(wrapper);
    });
    

    btnSettings?.addEventListener('click', (e) => {
        e.stopPropagation();
        popupManager.open(wSetting);
    });

    // أحداث الإغلاق (×)
    iconClose?.forEach(btn => {
        btn.addEventListener('click', (e) => {
            e.stopPropagation();
            const parentPopup = btn.closest('.active-popup');
            if (parentPopup) {
                popupManager.close(parentPopup);
            }
        });
    });

    // إغلاق عند النقر خارج النافذة
    document.addEventListener('click', (e) => {
        const isClickInsidePopup = e.target.closest('.active-popup');
        const isClickOnTrigger = e.target.closest('.btnlogin-popup') ||
                                 e.target.closest('.btnregister-popup') ||
                                 e.target.closest('#settingsBtn');
        if (!isClickInsidePopup && !isClickOnTrigger) {
            popupManager.closeAll();
        }
    });

    // تبديل بين النماذج (داخل الـ wrapper فقط)
    registerLink?.addEventListener('click', (e) => {
        e.preventDefault();
        // إغلاق النموذج الحالي أولاً
        wrapper.classList.remove('active');
        wrapper.classList.add('activeR');
    });

    loginLink?.addEventListener('click', (e) => {
        e.preventDefault();
        // إغلاق النموذج الحالي أولاً
        wrapper.classList.remove('activeR');
        wrapper.classList.add('active');
    });
});

document.querySelectorAll('.settings-btn').forEach(btn => {
    btn.addEventListener('click', () => {
        const heading = document.querySelector('.settings-heading');
        if (heading) {
            heading.style.opacity = '0';
            setTimeout(() => heading.style.display = 'none', 300); // إخفاء بعد الانتقال
        }
    });
});

// إعادة العرض عند فتح النافذة
document.getElementById('settingsBtn').addEventListener('click', () => {
    const heading = document.querySelector('.settings-heading');
    if (heading) {
        heading.style.display = 'block';
        heading.style.opacity = '1';
    }
});


