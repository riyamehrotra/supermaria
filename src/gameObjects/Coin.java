package gameObjects;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Coin {
	
	public BufferedImage normal; 
	public String type = "NORMAL";
	
	public void Coin() {
		SetImage();
	}
	
	public void SetImage() {
		try {
			File file = new File("src/gameObjects/coin.png");
			normal = ImageIO.read(file);
			//System.out.println(file.toString());
		} catch (IOException e) {
			//System.out.println("fail");
			e.printStackTrace();
		}
	}
	
	public BufferedImage GetImage() {
		return normal;
	}
	
}