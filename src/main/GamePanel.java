package main;
import java.awt.*;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

import entity.Monster;
import entity.Player;
import gameObjects.Coin;
import gameObjects.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
	// Screen Dimensions & Tile Sizes
	private final int maxScreenCol = 16;
	private final int maxScreenRow = 12;
	private final int tileSize = 48; 
	
	private final int screenWidth = tileSize * maxScreenCol;
	private final int screenHeight = tileSize * maxScreenRow;
	private final int FPS = 60; 
	private int score = 0;
	
	private Thread gameThread;
	private TileManager tileM = new TileManager(this);
	private KeyHandler keyH = new KeyHandler();
	private Player player = new Player(this, keyH, tileM, 1);
	private Player player2 = new Player(this, keyH, tileM, 2);
	private StartingScreen startS = new StartingScreen(this);
	private GameOverScreen gameOverS = new GameOverScreen(this);
	private WinScreen winS = new WinScreen(this);
	private NextMapScreen nextMapS = new NextMapScreen(this);
	private Music music = new Music();
	private int cameraX = player.GetWorldX(); 
	private String world = player.GetWorld(); 
	private int timer = 0;
	private int numOfPlayers = 1;
	private int dieWaiting = 0;
	
	enum State {
		START_SCREEN,
		NORMAL,
		PAUSE,
		GAME_OVER,
		WON,
		NEXT_MAP
	}
	
	private State GameState;

	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(getScreenWidth(), screenHeight));
		this.setBackground(Color.white);
		this.setFocusable(true);
		this.addKeyListener(keyH);
		
		GameState = State.START_SCREEN;
		

		//GameState = State.NORMAL;
		
		//System.out.println((getTileSize()*getMaxCol()/2) + ", " + screenWidth/2);
	}
	
	@Override
	public void run() {
		
		double drawInterval = 1000000000 / FPS;
		double delta = 0;
		double lastTime = System.nanoTime();
		double currentTime;
		//music.play(01);
		
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime; 
			
			if (delta >= 1) {
				if (GameState == State.NORMAL) 
					this.timer++;
				update();
				repaint();
				delta = 0;
			}
			
		}
	} 
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void reset() {
		tileM.SetMap(1);
		player2.reset();
		player.reset();
		tileM.reset();
		score = 0;
		timer = 0;
		cameraX = player.GetWorldX();
	}
	
	public void resetForNewMap() {
		player2.reset();
		player.reset();
		timer = 0;
		cameraX = player.GetWorldX();
	}
	
	public void update() {
		
		switch (GameState) {
			case State.NORMAL: 
				player.update();
				
				if (numOfPlayers == 2) {
					player2.update();
					cameraX = Math.max(player.GetWorldX(), player2.GetWorldX());
				}
				else {
					cameraX = player.GetWorldX();
				}
				
				tileM.update();
				
				if (tileM.MonsterCollide(player.GetWorldX(), player.GetWorldY()) || tileM.CheckIfFall(player.GetWorldX(), player.GetWorldY())) {
					music.stopAll();
					music.play(02);
					//System.out.println("collided");
					GameState = State.GAME_OVER;
				}
				
				if (tileM.CoinCollide(player.GetWorldX(), player.GetWorldY())) {
					
					music.play(06);
					score += 100;
					//System.out.println(score);
				}
				
				if (player.GetJumpingTimer() > 0) {
					if (tileM.CollideWithBlockCoin(player.GetWorldX(), player.GetWorldY())) {
						music.play(06);
						score += 200;
						//System.out.println("block coin!");
					}
				}
				
				if (numOfPlayers == 2) {
				
					if (tileM.MonsterCollide(player2.GetWorldX(), player2.GetWorldY()) || tileM.CheckIfFall(player2.GetWorldX(), player2.GetWorldY())) {
						music.stopAll();
						music.play(02);
						//System.out.println("collided");
						GameState = State.GAME_OVER;
					}
					
					if (tileM.CoinCollide(player2.GetWorldX(), player2.GetWorldY())) {
						
						music.play(06);
						score += 100;
						//System.out.println(score);
					}
					
	
					if (player2.GetJumpingTimer() > 0) {
						if (tileM.CollideWithBlockCoin(player2.GetWorldX(), player2.GetWorldY())) {
							music.play(06);
							score += 200;
							//System.out.println("block coin!");
						}
					}
					
					
					if (player.GetPastFinish() && player2.GetPastFinish()) {
						if (timer <= 3000) {
							score += 3000 - timer;
						}
						music.stopAll();
						music.play(03);
						if (tileM.NextMap() == -1) {
							
							GameState = State.WON;
						}
						else {
							tileM.SetMap(tileM.GetMap() + 1);
							GameState = State.NEXT_MAP;
						}
						
						
					}
					
				}
				
				else {
					if (player.GetPastFinish()) {
						if (timer <= 3000) {
							score += 3000 - timer;
						}
						music.stopAll();
						music.play(03);
						if (tileM.NextMap() == -1) {
							GameState = State.WON;
						}
						else {
							tileM.SetMap(tileM.GetMap() + 1);
							GameState = State.NEXT_MAP;
						}
					}
				}
				
				if (timer == 2000) {
					music.stopAll();
					music.play(5);
				}
				
				break;
			case State.START_SCREEN:
				tileM.update();
				if (dieWaiting > 0) {
					dieWaiting--;
				}
				else {
					if (keyH.getKeys()[4] == true) {
						numOfPlayers = 1;
						music.play(01);
						reset();
						GameState = State.NORMAL;
					}
					if (keyH.getKeys()[5] == true) {
						numOfPlayers = 2;
						music.play(01);
						reset();
						GameState = State.NORMAL;
					}
				}
				break;
			case State.GAME_OVER:
				if (keyH.getKeys()[4] == true) {
					music.play(01);
					reset();
					GameState = State.START_SCREEN;
					dieWaiting = 20;
				}
				if (keyH.getKeys()[5] == true) {
					music.stopAll();
					reset();
					Runner.stop();
				}
				
				break;
				
				
			case State.WON:
				if (keyH.getKeys()[4] == true) {
					music.play(01);
					reset();
					GameState = State.START_SCREEN;
					dieWaiting = 20;
				}
				if (keyH.getKeys()[5] == true) {
					music.stopAll();
					reset();
					Runner.stop();
				}
				break;
				//System.out.println("won!");
			case State.NEXT_MAP:
				if (keyH.getKeys()[4] == true) {
					music.play(01);
					resetForNewMap();
					GameState = State.NORMAL;
				}
				if (keyH.getKeys()[5] == true) {
					music.stopAll();
					reset();
					Runner.stop();
				}
				break;
	
		}
		
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2 = (Graphics2D)g;
		
		switch (GameState) {
			case State.NORMAL: 
				tileM.draw(g2, cameraX);
				player.draw(g2, Math.max(player.GetWorldX(), player2.GetWorldX()));
				if (numOfPlayers == 2) {player2.draw(g2, Math.max(player.GetWorldX(), player2.GetWorldX()));}
				break;
			case State.START_SCREEN:
				reset();
				tileM.draw(g2, cameraX);
				player.draw(g2, Math.max(player.GetWorldX(), player2.GetWorldX()));
				if (numOfPlayers == 2) {player2.draw(g2, Math.max(player.GetWorldX(), player2.GetWorldX()));}
				startS.draw(g2);
				
				break;
			case State.GAME_OVER:
				tileM.draw(g2, cameraX);
				player.draw(g2, Math.max(player.GetWorldX(), player2.GetWorldX()));
				if (numOfPlayers == 2) {player2.draw(g2, Math.max(player.GetWorldX(), player2.GetWorldX()));}
				gameOverS.draw(g2);
				break;
			case State.WON:
				tileM.draw(g2, cameraX);
				player.draw(g2, Math.max(player.GetWorldX(), player2.GetWorldX()));
				winS.draw(g2);
				break;
			case State.NEXT_MAP:
				tileM.draw(g2, cameraX);
				player.draw(g2, Math.max(player.GetWorldX(), player2.GetWorldX()));
				nextMapS.draw(g2);
				break;
		}

		g2.dispose();
	}
	
	public int getMaxCol() {
		return maxScreenCol; 
	}
	
	public int getScore() {
		return score; 
	}
	
	public int getTime() {
		return timer; 
	}
	
	public void setScore(int score) {
		this.score = score; 
	}
	
	
	public int getMaxRow() {
		return maxScreenRow; 
	}
	
	public int getTileSize() {
		return tileSize; 
	}

	public int getScreenWidth() {
		return screenWidth;
	}
	
	public int getScreenHeight() {
		return screenHeight;
	}
	
}