package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class NextMapScreen {
	
	GamePanel gp;
	
	public NextMapScreen(GamePanel gp) {
		this.gp = gp;
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage file;
		try {
			file = ImageIO.read(new File("src/main/nextmap.png"));
			g2.drawImage(file, 0, 0, gp.getScreenWidth(), gp.getScreenHeight(), null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		/*g2.setColor(new Color(100, 100, 100));
		g2.fillRect(0, 0, gp.getScreenWidth(), gp.getScreenHeight());
		
		g2.setColor(new Color(255, 255, 255));
		g2.setFont(new Font("title", 0, 30));
		g2.drawString("You Win!", gp.getScreenWidth()/2 - 70, gp.getScreenHeight()/4);
		
		g2.drawString("Your score is " + gp.getScore(), gp.getScreenWidth()/2 - 115, gp.getScreenHeight()/2);
		
		g2.setFont(new Font("title", 0, 30));
		g2.drawString("Press Space to move to the next map", gp.getScreenWidth()/2 - 270, 2*gp.getScreenHeight()/3 + 10);*/

		
	}
	
	
}