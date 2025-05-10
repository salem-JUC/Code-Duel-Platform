class GameObject {
  constructor(config) {
    this.x = config.x || 0;
    this.y = config.y || 0;

    // نمرر الإعدادات كاملة لـ Sprite
    this.sprite = new Sprite({
      gameObject: this,
      src: config.src || "./Asset/spritesheet.png",
      animations: config.animations || {
        idle: [ [0,0], [1,0], [2,0] ]
      },
      frameDuration: config.frameDuration || 200,
      frameWidth: config.frameWidth || 34,
      frameHeight: config.frameHeight || 34
    });
  }



takeHitO() {
  const originalX = this.x;
  const knockbackX = this.x -4; // يرجع خطوة (تقدر تزيد القيمة)

  this.sprite.setAnimation("Damege");
  this.x = knockbackX;

  setTimeout(() => {
    this.x = originalX;
    this.sprite.setAnimation("idle");
  }, 3000); // نص ثانية ويرجع
}
performAttackP(target) { // نمرر الهدف
  const originalX = this.x;
  const targetX = this.x + 5; // تقدم الهجوم

  this.sprite.setAnimation("Attack");
  this.x = targetX;

  // 👇 يهجم والخصم يتراجع بنفس الوقت
  target.takeHitO();

  setTimeout(() => {
    this.x = originalX;
    this.sprite.setAnimation("idle");
  }, 3000);
}



takeHitP() {
  const originalX = this.x;
  const knockbackX = this.x -4; // يرجع خطوة (تقدر تزيد القيمة)

  this.sprite.setAnimation("Attack");
  this.x = knockbackX;

  setTimeout(() => {
    this.x = originalX;
    this.sprite.setAnimation("idle");
  }, 3000); // نص ثانية ويرجع
}

performAttackO(target) { // نمرر الهدف
  const originalX = this.x;
  const targetX = this.x + 5; // تقدم الهجوم

  this.sprite.setAnimation("Damege");
  this.x = targetX;

  // 👇 يهجم والخصم يتراجع بنفس الوقت
  target.takeHitP();

  setTimeout(() => {
    this.x = originalX;
    this.sprite.setAnimation("idle");
  }, 3000);
}



WinnerP(target) { // نمرر الهدف
  const originalX = this.x;
  const targetX = this.x + 5; // تقدم الهجوم

  this.sprite.setAnimation("win");
  this.x = targetX;

  // 👇 يهجم والخصم يتراجع بنفس الوقت
 

  setTimeout(() => {
    this.x = originalX;
    this.sprite.setAnimation("idle");
  }, 5000);
}
winO() {
  const originalX = this.x;
  const knockbackX = this.x -4; // يرجع خطوة (تقدر تزيد القيمة)

  this.sprite.setAnimation("win");
  this.x = knockbackX;

  setTimeout(() => {
    this.x = originalX;
    this.sprite.setAnimation("idle");
  }, 5000); // نص ثانية ويرجع
}
winnerO(target) { // نمرر الهدف
  const originalX = this.x;
  const targetX = this.x ; // تقدم الهجوم

  this.sprite.setAnimation("idle");
  this.x = targetX;

  // 👇 يهجم والخصم يتراجع بنفس الوقت
  target.winO();

  setTimeout(() => {
    this.x = originalX;
    this.sprite.setAnimation("idle");
  }, 3000);
}


}

