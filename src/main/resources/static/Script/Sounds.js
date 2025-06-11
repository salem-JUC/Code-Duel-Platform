const sounds = {
    background: './Asset/BackGroundMusic.mp3',
    click: './Asset/ClickSFX.mp3',
    hoverSound: './Asset/HoverSFX.mp3',
};

document.addEventListener('DOMContentLoaded', () => {
    const isMusicPlaying = localStorage.getItem('musicIsPlaying') === 'true';

    // ====== إعداد الموسيقى ======
    const audio = document.createElement('audio');
    audio.src = sounds.background;
    audio.loop = true;
    audio.preload = 'auto';
    audio.volume = 1;

    if (isMusicPlaying) {
        audio.play().catch(err => console.log("مشكلة في تشغيل الصوت:", err));
    }

    const setMusicState = (isPlaying) => {
        localStorage.setItem('musicIsPlaying', isPlaying);
    };

    document.body.addEventListener('click', () => {
        if (audio.paused) {
            audio.play().then(() => {
                setMusicState(true);
                playSFXByName('click');
            }).catch(err => console.log("مشكلة في تشغيل الصوت:", err));
        }
    }, { once: true });

    // ====== عناصر التحكم للموسيقى ======
    const musicVolumeSlider = document.getElementById('musicVolume');
    const musicMute = document.getElementById('musicMute');
    let lastMusicVolume = 1;

    // ====== عناصر التحكم للمؤثرات ======
    const sfxVolumeSlider = document.getElementById('sfxVolume');
    const sfxMute = document.getElementById('sfxMute');
    let lastSfxVolume = 1;

    // ====== استرجاع الإعدادات ======
    const loadSettings = () => {
        // Music Settings
        const musicVol = parseFloat(localStorage.getItem('musicVolume'));
        const musicMuted = localStorage.getItem('musicMuted') === 'true';
        if (!isNaN(musicVol)) {
            audio.volume = musicVol;
            musicVolumeSlider.value = musicVol;
            lastMusicVolume = musicVol;
        }
        if (musicMuted) {
            musicMute.checked = true;
            audio.volume = 0;
            musicVolumeSlider.value = 0;
        }

        // SFX Settings
        const sfxVol = parseFloat(localStorage.getItem('sfxVolume'));
        const sfxMuted = localStorage.getItem('sfxMuted') === 'true';
        if (!isNaN(sfxVol)) {
            sfxVolumeSlider.value = sfxVol;
            lastSfxVolume = sfxVol;
        } else {
            sfxVolumeSlider.value = 1;
        }
        if (sfxMuted) {
            sfxMute.checked = true;
            sfxVolumeSlider.value = 0;
        }
    };

    loadSettings();

    // ====== تحكم مستوى صوت الموسيقى ======
    musicVolumeSlider.addEventListener('input', (e) => {
        const value = parseFloat(e.target.value);
        if (!isNaN(value)) {
            audio.volume = value;
            lastMusicVolume = value;
            localStorage.setItem('musicVolume', value);
            localStorage.setItem('musicMuted', false);
            musicMute.checked = false;
        }
    });

    // ====== كتم الموسيقى ======
    musicMute.addEventListener('change', (e) => {
        if (e.target.checked) {
            audio.volume = 0;
            musicVolumeSlider.value = 0;
            localStorage.setItem('musicMuted', true);
        } else {
            audio.volume = lastMusicVolume;
            musicVolumeSlider.value = lastMusicVolume;
            localStorage.setItem('musicMuted', false);
        }
    });

    // ====== تحكم مستوى صوت المؤثرات ======
    sfxVolumeSlider.addEventListener('input', (e) => {
        const value = parseFloat(e.target.value);
        if (!isNaN(value)) {
            lastSfxVolume = value;
            localStorage.setItem('sfxVolume', value);
            localStorage.setItem('sfxMuted', false);
            sfxMute.checked = false;
        }
    });

    // ====== كتم المؤثرات ======
    sfxMute.addEventListener('change', (e) => {
        if (e.target.checked) {
            sfxVolumeSlider.value = 0;
            localStorage.setItem('sfxMuted', true);
        } else {
            sfxVolumeSlider.value = lastSfxVolume;
            localStorage.setItem('sfxMuted', false);
        }
    });

    // ====== تشغيل مؤثر صوتي مع مراعاة إعدادات SFX ======
    window.playSFX = (src) => {
        const isSfxMuted = localStorage.getItem('sfxMuted') === 'true';
        if (isSfxMuted) return;

        const sfx = new Audio(src);
        const sfxVol = parseFloat(localStorage.getItem('sfxVolume'));
        sfx.volume = !isNaN(sfxVol) ? sfxVol : 1;
        sfx.play();
    };

    window.playSFXByName = (name) => {
        if (sounds[name]) {
            playSFX(sounds[name]);
        } else {
            console.warn(`Sound "${name}" not found`);
        }
    };

    // ====== إضافة المؤثر الصوتي للـ hover مع مراعاة إعدادات SFX ======
    const buttons = document.querySelectorAll('button');
    buttons.forEach(button => {
        button.addEventListener('mouseenter', () => {
            playSFX(sounds.hoverSound);
        });
    });
});
