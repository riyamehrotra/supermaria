package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import gameObjects.Tile;
import gameObjects.TileManager;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
	
	private boolean jumping = false;
	private int jumpingTimer = 0;
	private final int JUMP_TIME = 45;
	private int player;
	private boolean pastLine = false;
	private String world = "over"; 
	
	public Player (GamePanel gp, KeyHandler keyH, TileManager tileM, int player) {
		this.player = player;
		this.gp = gp;
		this.keyH = keyH;
		this.tileM = tileM;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues () {
		worldX = gp.getTileSize()*8;
		worldY = gp.getTileSize()*7 - 2; 
		if (player == 2) {
			worldX = gp.getTileSize()*7;
		}
		speed = 4;
		jumpSpeed = 12;
		hitboxRadius = gp.getTileSize() * 3 / 8;
	}
	
	public void reset () {
		jumpingTimer = 0;
		pastLine = false;
		setDefaultValues();
	}
	
	public void getPlayerImage() {
		try {
			File file = new File("src/entity/player.png");
			if (player == 2) {
				file = new File("src/entity/player2.png");
			}
			normal = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update () {
		
		if (pastLine == false)
			pastLine = tileM.ReachFinish(worldX, worldY);
		
		int col = (int)worldX/gp.getTileSize() + 1;
		int row = (int)worldY/gp.getTileSize() + 1;
		double centerX = worldX + gp.getTileSize()/2;
		double centerY = worldY + gp.getTileSize()/2;
		if (keyH.getKeys(player)[0] == true && jumping == false && tileM.CheckIfCollide(worldX, worldY+5)) {
			
			if (!tileM.CheckIfCollide(worldX, worldY-gp.getTileSize()-speed)) {
				worldY -= speed;
				jumping = true;
				jumpingTimer = JUMP_TIME;
			}
			//if (!tileM.CheckIfCollideByCorner(worldX, worldY-speed)) {worldY -= speed;}
		}
		if (keyH.getKeys(player)[1] == true) {
			if (!(tileM.CheckIfCollide(worldX, worldY + gp.getTileSize() + speed) && tileM.CheckIfCollide(worldX + gp.getTileSize()/2, worldY + gp.getTileSize()))) {worldY += speed;}
			else {
				if (tileM.CollideWithTopPipe(worldX, worldY+speed)) {
					//world = "under";
					//System.out.println("switching world");	
				}
			}
			
			//if (!tileM.CheckIfCollideByCorner(worldX, worldY+speed)) {worldY += speed;}
		}
		if (keyH.getKeys(player)[2] == true) {
			if (worldX >= gp.getTileSize()*gp.getMaxCol()/2 && !tileM.CheckIfCollide(worldX-speed - gp.getTileSize()/2, worldY)) {worldX -= speed;}
			//if (!tileM.CheckIfCollideByCorner(worldX-speed, worldY)) {worldX -= speed;}
		}
		if (keyH.getKeys(player)[3] == true) {
			if (!tileM.CheckIfCollide(worldX+gp.getTileSize()/2, worldY)) {worldX += speed;}
			//if (!tileM.CheckIfCollideByCorner(worldX+speed, worldY)) {worldX += speed;}

		} 
		
		if (!(tileM.CheckIfCollide(worldX, worldY+speed) || tileM.CheckIfCollide(worldX+gp.getTileSize(), worldY+speed))) {
			
			//System.out.println("true");	
			worldY += speed/2;
		}
		
		//if (!(tileM.CheckIfCollide(worldX, worldY + gp.getTileSize() + speed/2) || tileM.CheckIfCollide(worldX + gp.getTileSize()/2, worldY + gp.getTileSize()))) {
			
		//}
		if (!tileM.CheckIfCollide(worldX, worldY+ 2)) {worldY += 2;}
		//if (!tileM.CheckIfCollideByCorner(worldX, worldY + 2)) {worldY += 2;}
		
		if (jumpingTimer > 0) {
			jumpingTimer--;
			if (jumpingTimer > JUMP_TIME/2) 
				if(!tileM.CheckIfCollide(worldX, worldY-gp.getTileSize()-jumpSpeed)) {worldY -= jumpSpeed;}
				
		}
		if (jumpingTimer == 0) {
			jumping = false;
		}
		
		//System.out.println(worldX + ", " + worldY + ", " + speed);
	}
	
	public void draw (Graphics2D g2, int center) {
		int x;
		if (center ==  worldX)
			x = (int)(gp.getScreenWidth()/2 - gp.getTileSize()/2);
		else {
			x = (int)(gp.getScreenWidth()/2 - gp.getTileSize()/2) - center + worldX;
		}
		
		
		g2.drawImage(normal, x, worldY, gp.getTileSize(), gp.getTileSize(), null); 
		//g2.fillOval(worldX - worldX + (int)(gp.getScreenWidth()/2 - gp.getTileSize()/2), worldY + gp.getTileSize() + speed, 5, 5);
		//g2.drawImage(normal, gp.screenWidth/2 - gp.tileSize/2, gp.screenHeight/2 - gp.tileSize/2, gp.tileSize, gp.tileSize, null); 
		//g2.draw3DRect(centerX, centerY, 5, 5, (Boolean) null);
		//g2.setColor(Color.BLACK);a
		//g2.fillRect(gp.screenWidth/2 + gp.tileSize/2 + worldX - centerX, gp.screenHeight/2 + gp.tileSize/2 + worldY - centerY, 2*hitboxRadius, 2*hitboxRadius);
	}
	
	public int GetWorldX() {
		return worldX;
	}
	
	public boolean GetPastFinish() {
		return pastLine;
	}
	
	public int GetJumpingTimer() {
		return jumpingTimer;
	}
	
	public int GetWorldY() {
		return worldY;
	}
	
	public String GetWorld() {
		return world;
	}
	
	public void SetWorld(String world) {
		this.world = world;
	}
}

