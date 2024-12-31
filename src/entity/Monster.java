package entity;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Monster extends Entity {
	
	private int boundLeft;
	private int boundRight;
	private int height;
	
	private GamePanel gp;
	
	public Monster(GamePanel gp) {
		this.gp = gp;
		direction = "left";
		worldX = boundRight;
		worldY = height;
		speed = 1;
		LoadImage();
	}
	
	public Monster(GamePanel gp, int boundLeft, int boundRight, int height) {
		this.gp = gp;
		this.boundLeft = boundLeft;
		this.boundRight = boundRight;
		this.height = height;
		direction = "left";
		worldX = boundRight;
		worldY = height;
		speed = 1;
		LoadImage();
	}
	
	private void LoadImage() {
		try {
			File file = new File("src/entity/blob.png");
			normal = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void draw(Graphics2D g2, int cameraX) {
		if (worldX < cameraX - gp.getTileSize() * (gp.getMaxCol()/2 + 1) || worldX > cameraX + gp.getTileSize() * gp.getMaxCol()/2) 
			return;
		
		g2.drawImage(normal, worldX - cameraX + gp.getTileSize() * gp.getMaxCol()/2, worldY, gp.getTileSize(), gp.getTileSize(), null);
		//g2.drawOval(worldX - cameraX, worldY, 5, 5);
	}
	
	public void update() {
		if (direction == "left") {
			if (worldX <= boundLeft)
				direction = "right";
			else
				worldX -= speed;
		}
		else {
			if (worldX >= boundRight)
				direction = "left";
			else
				worldX += speed;
		}
	}
	
	public double GetX() {
		return worldX;
	}
	
	public double GetY() {
		return worldY;
	}
	
	
}