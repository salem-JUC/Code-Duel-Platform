class Overworld {
  constructor(config) {
    this.element = config.element;
    this.canvas = this.element.querySelector(".game-canvas");
    this.ctx = this.canvas.getContext("2d");

    this.Player = null;
    this.Oponent = null;
  }

  init() {
    this.Player = new GameObject({
      x: 5,
      y: 7,
      src: "./Asset/spritesheet.png",
      animations: {
        idle: [[2,0],[0,1],[0,0]],
        Attack: [[1,0],[0,0],[0,1] ],
        Damege: [[0,2],[1,1],[2,0]],
        win: [[0,0],[2,1],[2,1],[2,0]],
        lose: [[2,0],[0,1],[0,0]]
      },
      frameWidth: 34,
      frameHeight: 34,
      frameDuration: 300
    });

    this.Oponent = new GameObject({
      x: 16,
      y: 7,
      src: "./Asset/npc1.png",
      animations: {
        idle: [[2,0],[0,1],[0,0]],
        Attack: [[2 ,0],[2,1],[0,1]],
        Damege: [[1,0],[1,1],[1,1]],
        win: [[1,0],[0,2],[0,2],[0,1]],
        lose: [[1,0],[1,1],[1,2]]
      },
      frameWidth: 34,
      frameHeight: 34,
      frameDuration: 300
    });

    const gameLoop = () => {
      this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height);
      this.Player.sprite.draw(this.ctx);
      this.Oponent.sprite.draw(this.ctx);
      requestAnimationFrame(gameLoop);
    };

    gameLoop();

    // 👇 مستمع الضغط على زر A
    window.addEventListener("keydown", (e) => {
      if (e.key === "a" || e.key === "A") {
        overworld.Player.performAttackP(overworld.Oponent);

      }
    });

    window.addEventListener("keydown", (e) => {
      if (e.key === "s" || e.key === "S") {
        overworld.Player.performAttackO(overworld.Oponent);
      }
    });

    window.addEventListener("keydown", (e) => {
      if (e.key === "d" || e.key === "D") {
        overworld.Player.WinnerP(overworld.Oponent);
      }
    });


    window.addEventListener("keydown", (e) => {
      if (e.key === "f" || e.key === "F") {
        overworld.Player.winnerO(overworld.Oponent);
      }
    });



    
  }

  
}

