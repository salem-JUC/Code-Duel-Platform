class Sprite {
  constructor(config) {
    this.image = new Image();
    this.image.src = config.src;
    this.image.onload = () => {
      this.isLoaded = true;
      console.log("✅ Image loaded!");
    }

    this.scale = config.scale || 2.5;
    this.animations = config.animations;
    this.currentAnimation = config.currentAnimation || Object.keys(this.animations)[0];
    this.currentAnimationFrame = 0;

    this.frameDuration = config.frameDuration || 150;
    this.lastFrameUpdate = performance.now();

    this.frameWidth = config.frameWidth || 32;
    this.frameHeight = config.frameHeight || 32;

    this.isLoaded = false;
    this.gameObject = config.gameObject;
  }

  updateAnimation() {
    if (!this.isLoaded) return;

    const now = performance.now();
    const elapsed = now - this.lastFrameUpdate;

    if (elapsed >= this.frameDuration) {
      const framesToAdvance = Math.floor(elapsed / this.frameDuration);
      const totalFrames = this.animations[this.currentAnimation].length;

      this.currentAnimationFrame = (this.currentAnimationFrame + framesToAdvance) % totalFrames;
      this.lastFrameUpdate += framesToAdvance * this.frameDuration;
    }
  }

  draw(ctx) {
    if (!this.isLoaded) return;

    this.updateAnimation();

    const x = this.gameObject.x * 14 - 6;
    const y = this.gameObject.y * 16 - 6;

    ctx.imageSmoothingEnabled = false;

    const scaledWidth = this.frameWidth * this.scale;
    const scaledHeight = this.frameHeight * this.scale;

    const [frameX, frameY] = this.animations[this.currentAnimation][this.currentAnimationFrame];

    ctx.drawImage(
      this.image,
      frameX * this.frameWidth,
      frameY * this.frameHeight,
      this.frameWidth,
      this.frameHeight,
      x,
      y,
      scaledWidth,
      scaledHeight
    );
  }

  setAnimation(animationName) {
    if (this.animations[animationName]) {
      if (this.currentAnimation !== animationName) {
        this.currentAnimation = animationName;
        this.currentAnimationFrame = 0;
        this.lastFrameUpdate = performance.now();
      }
    }
  }
}