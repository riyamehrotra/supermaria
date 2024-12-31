package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class WinScreen {
	
	GamePanel gp;
	
	public WinScreen(GamePanel gp) {
		this.gp = gp;
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage file;
		try {
			file = ImageIO.read(new File("src/main/win.png"));
			g2.drawImage(file, 0, 0, gp.getScreenWidth(), gp.getScreenHeight(), null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
		    //create the font to use. Specify the size!
		    Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/font.ttf")).deriveFont(55f);
		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    //register the font
		    ge.registerFont(customFont);
		    

			g2.setColor(new Color(0, 0, 0));
			g2.setFont(customFont);
			g2.drawString("Score: " + String.format("%05d", gp.getScore()), 260, 475);
		    
		} catch (IOException e) {
		    e.printStackTrace();
		} catch(FontFormatException e) {
		    e.printStackTrace();
		}
		
		
		
		/*g2.setColor(new Color(100, 100, 100));
		g2.fillRect(0, 0, gp.getScreenWidth(), gp.getScreenHeight());
		
		g2.setColor(new Color(255, 255, 255));
		g2.setFont(new Font("title", 0, 30));
		g2.drawString("You Win!", gp.getScreenWidth()/2 - 80, gp.getScreenHeight()/4);
		
		g2.drawString("Your score was " + gp.getScore(), gp.getScreenWidth()/2 - 140, gp.getScreenHeight()/2);
		
		g2.setFont(new Font("title", 0, 30));
		g2.drawString("Press Space to restart", gp.getScreenWidth()/2 - 140, 2*gp.getScreenHeight()/3);*/

		
	}
	
	
}