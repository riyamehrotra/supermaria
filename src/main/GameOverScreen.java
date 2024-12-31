package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GameOverScreen {
	
	GamePanel gp;
	
	public GameOverScreen(GamePanel gp) {
		this.gp = gp;
	}
	
	public void draw(Graphics2D g2) {
		
		/*g2.setColor(new Color(100, 100, 100));
		g2.fillRect(0, 0, gp.getScreenWidth(), gp.getScreenHeight());
		
		g2.setColor(new Color(255, 255, 255));
		g2.setFont(new Font("title", 0, 30));
		g2.drawString("Game Over!", gp.getScreenWidth()/2 - 80, gp.getScreenHeight()/4);
		
		g2.setFont(new Font("title", 0, 30));
		g2.drawString("Press Space to restart", gp.getScreenWidth()/2 - 140, gp.getScreenHeight()/2);
		

		g2.drawString("Press Enter to exit", gp.getScreenWidth()/2 - 120, gp.getScreenHeight()/2 + 100);*/
		
		BufferedImage file;
		try {
			file = ImageIO.read(new File("src/main/gameover.png"));
			g2.drawImage(file, 250, 50, 300 , 231, null);
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}
	
	
}