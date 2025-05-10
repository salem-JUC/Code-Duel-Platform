  let seconds = 0;
  let minutes = 0;
  const timerElement = document.getElementById("timer");
  
  const updateTimer = () => {
    seconds++;
    if (seconds === 60) {
      minutes++;
      seconds = 0;
    }
    
    const displaySeconds = seconds.toString().padStart(2, '0');
    const displayMinutes = minutes.toString().padStart(2, '0');
    
    timerElement.textContent = `${displayMinutes}:${displaySeconds}`;
  };

  // ابدأ المؤقت عند تحميل الصفحة
  let timerInterval = setInterval(updateTimer, 1000);

  // (اختياري) أوقف المؤقت عند تغيير الصفحة
  window.addEventListener('beforeunload', () => {
    clearInterval(timerInterval);
  });
  function resetTimer() {
    clearInterval(timerInterval);
    seconds = 0;
    minutes = 0;
    timerElement.textContent = "00:00";
    timerInterval = setInterval(updateTimer, 1000);
  }