function duel() {
    document.querySelector('.submit').disabled = true; /*Block the Button */
}

function startCountdown() {
    let count = 3;
    const countdownElement = document.getElementById('countdown');

    // نضيف متغير الصوت
    const countdownSound = new Audio('./Asset/Countdown.mp3'); // تأكد من المسار الصحيح للملف

    // نقوم بتشغيل الصوت بعد أول تفاعل مع الصفحة
    countdownSound.play().then(() => {
        console.log("Sound is playing");
    }).catch(error => {
        console.error("Sound could not play:", error);
    });

    const updateCountdown = () => {
        const urlParams = new URLSearchParams(window.location.search);
        matchId = urlParams.get('matchId');
        
        if (count > 0) {
            countdownElement.textContent = count;
            count--;
            setTimeout(updateCountdown, 1000);
        } else {
            countdownElement.textContent = 'GO!';
            setTimeout(() => {
                window.location.href = `GameField.html?matchId=${matchId}`; // بعد العد، ننتقل للعبة
            }, 1000);
        }
    };

    updateCountdown();
}


window.onload = async () => {
    startCountdown();
};

// نبدأ العد التنازلي بعد تحميل الصفحة مع أول تفاعل
