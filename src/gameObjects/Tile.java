package gameObjects;

import java.awt.Image;
import java.awt.image.*;

public class Tile {
	
	private int type = 0;
	private BufferedImage image; 
	private boolean collision = false; 
	
	public Tile (BufferedImage image, boolean collision, int type) {
		this.image = image;
		this.collision = collision; 
		this.type = type;
	}
	
	public Tile (boolean collision, int type) {
		this.collision = collision; 
		this.type = type;
	}
	
	public Image getImage() {
		return (Image)image;
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public boolean GetCollision() {
		return collision;
	}
	
}