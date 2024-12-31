package gameObjects;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Cloud extends Tile{

	public Cloud(int type) {
		super(false, type);
		BufferedImage file;
		try {
			file = ImageIO.read(new File("src/gameObjects/cloud.png"));
			setImage(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}