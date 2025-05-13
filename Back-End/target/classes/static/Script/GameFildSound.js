class GameFieldSounds {
    constructor() {
       
        this.sounds = {
            background: './Asset/GameFIldBGMusic.mp3',
            hit: './Asset/HitSFX.mp3',
            win: './Asset/victory.mp3',
            click: './Asset/ClickSFX.mp3',
            hoverSound: './Asset/HoverSFX.mp3'
        };

      
        document.addEventListener('DOMContentLoaded', () => this.initialize());
    }

    initialize() {
        this.isMusicPlaying = localStorage.getItem('musicIsPlaying') === 'true';

        this.audio = document.createElement('audio');
        this.audio.src = this.sounds.background;
        this.audio.loop = true;
        this.audio.preload = 'auto';
        this.audio.volume = 1;

        if (this.isMusicPlaying) {
            this.audio.play().catch(err => console.log("مشكلة في تشغيل الصوت:", err));
        }

 
        this.musicVolumeSlider = document.getElementById('musicVolume');
        this.musicMute = document.getElementById('musicMute');
        this.sfxVolumeSlider = document.getElementById('sfxVolume');
        this.sfxMute = document.getElementById('sfxMute');

        this.loadSettings();
   
        this.setupEventListeners();
    }

    loadSettings() {
     
        const musicVol = parseFloat(localStorage.getItem('musicVolume'));
        const musicMuted = localStorage.getItem('musicMuted') === 'true';
        
        if (!isNaN(musicVol)) {
            this.audio.volume = musicVol;
            this.musicVolumeSlider.value = musicVol;
            this.lastMusicVolume = musicVol;
        }
        
        if (musicMuted) {
            this.musicMute.checked = true;
            this.audio.volume = 0;
            this.musicVolumeSlider.value = 0;
        }

        // إعدادات المؤثرات
        const sfxVol = parseFloat(localStorage.getItem('sfxVolume'));
        const sfxMuted = localStorage.getItem('sfxMuted') === 'true';
        
        if (!isNaN(sfxVol)) {
            this.sfxVolumeSlider.value = sfxVol;
            this.lastSfxVolume = sfxVol;
        }
        
        if (sfxMuted) {
            this.sfxMute.checked = true;
            this.sfxVolumeSlider.value = 0;
        }
    }

    setupEventListeners() {
        // حدث النقر على الصفحة
        document.body.addEventListener('click', () => {
            if (this.audio.paused) {
                this.audio.play()
                    .then(() => {
                        localStorage.setItem('musicIsPlaying', true);
                        this.playSFX('click');
                    })
                    .catch(err => console.log("مشكلة في تشغيل الصوت:", err));
            }
        }, { once: true });

        // أحداث التحكم بالموسيقى
        this.musicVolumeSlider.addEventListener('input', (e) => {
            const value = parseFloat(e.target.value);
            this.audio.volume = value;
            this.lastMusicVolume = value;
            localStorage.setItem('musicVolume', value);
            localStorage.setItem('musicMuted', false);
            this.musicMute.checked = false;
        });

        this.musicMute.addEventListener('change', (e) => {
            if (e.target.checked) {
                this.audio.volume = 0;
                this.musicVolumeSlider.value = 0;
                localStorage.setItem('musicMuted', true);
            } else {
                this.audio.volume = this.lastMusicVolume;
                this.musicVolumeSlider.value = this.lastMusicVolume;
                localStorage.setItem('musicMuted', false);
            }
        });

        // أحداث التحكم بالمؤثرات
        this.sfxVolumeSlider.addEventListener('input', (e) => {
            const value = parseFloat(e.target.value);
            this.lastSfxVolume = value;
            localStorage.setItem('sfxVolume', value);
            localStorage.setItem('sfxMuted', false);
            this.sfxMute.checked = false;
        });

        this.sfxMute.addEventListener('change', (e) => {
            if (e.target.checked) {
                this.sfxVolumeSlider.value = 0;
                localStorage.setItem('sfxMuted', true);
            } else {
                this.sfxVolumeSlider.value = this.lastSfxVolume;
                localStorage.setItem('sfxMuted', false);
            }
        });


        document.querySelectorAll('button').forEach(button => {
            button.addEventListener('mouseenter', () => this.playSFX('hoverSound'));
        });
    }

    playSFX(name) {
        if (localStorage.getItem('sfxMuted') === 'true') return;
        
        const sfx = new Audio(this.sounds[name]);
        const sfxVol = parseFloat(localStorage.getItem('sfxVolume')) || 1;
        sfx.volume = sfxVol;
        sfx.play();
    }
}


const gameSounds = new GameFieldSounds();