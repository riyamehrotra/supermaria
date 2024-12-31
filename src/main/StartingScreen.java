package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class StartingScreen{
	
	GamePanel gp; 
	
	public StartingScreen(GamePanel gp) {
		this.gp = gp;
	}
	
	public void draw(Graphics2D g2) {
	
		BufferedImage file;
		try {
			file = ImageIO.read(new File("src/main/logoblue.png"));
			g2.drawImage(file, 250, 50, 300 , 231, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		try {
		    //create the font to use. Specify the size!
		    Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/font.ttf")).deriveFont(40f);
		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    //register the font
		    ge.registerFont(customFont);
		    

			g2.setFont(customFont);
			g2.setColor(new Color(255, 255, 255));
			g2.drawString("Press Space to Start 1P", gp.getScreenWidth()/2 - 155, 3*gp.getScreenHeight()/4 + 50);
			g2.drawString("Press Enter to Start 2P", gp.getScreenWidth()/2 - 155, 7*gp.getScreenHeight()/8 + 30);
		    
		} catch (IOException e) {
		    e.printStackTrace();
		} catch(FontFormatException e) {
		    e.printStackTrace();
		}
		
		

		
	}
	
	
}