function updateHealth(playerType, percent) {
  const bar = document.querySelector(`.${playerType} .health-fill`);
  const text = document.querySelector(`.${playerType} .health-text`);
  
  // تحديد الحالة بناء على النسبة
  const healthStates = {
    full: { class: 'full', color: '#4CAF50', icon: '🛡️' },
    half: { class: 'half', color: '#FFC107', icon: '⚡' },
    empty: { class: 'empty', color: '#f44336', icon: '💀' }
  };

  // تحديث العرض واللون
  bar.style.width = `${percent}%`;
  
  // إزالة جميع الفئات السابقة
  bar.classList.remove('full', 'half', 'empty', 'glow');
  
  // تحديد الحالة الحالية
  let currentState;
  if (percent >= 75) {
    currentState = healthStates.full;
  } else if (percent >= 25) {
    currentState = healthStates.half;
  } else {
    currentState = healthStates.empty;
  }

  // تطبيق الأنماط حسب الحالة
  bar.style.background = `linear-gradient(90deg, 
    ${currentState.color} 0%, 
    ${darkenColor(currentState.color, 20)} 100%
  )`;
  bar.classList.add(currentState.class);

  // إضافة تأثير التألق للحالة الكاملة
  if (percent === 100) {
    bar.classList.add('glow');
  }

  // تحديث النص مع الأيقونة
  const statusText = playerType === 'player1' 
    ? `BATTLE CODE: ${currentState.icon} ${percent}%` 
    : `ENEMY: ${currentState.icon} ${percent}%`;
  
  text.textContent = statusText;

  // إدارة تأثير التحذير
  const healthBar = bar.parentElement;
  healthBar.classList.toggle('low', percent <= 25);
}

// دالة مساعدة لتغميق الألوان
function darkenColor(hex, percent) {
  const num = parseInt(hex.replace("#",""), 16),
    amt = Math.round(2.55 * percent),
    R = (num >> 16) - amt,
    B = (num >> 8 & 0x00FF) - amt,
    G = (num & 0x0000FF) - amt;
  return "#" + (0x1000000 + (R < 255 ? R < 1 ? 0 : R : 255) * 0x10000 
    + (B < 255 ? B < 1 ? 0 : B : 255) * 0x100 
    + (G < 255 ? G < 1 ? 0 : G : 255)).toString(16).slice(1);
}