package entity;

import java.awt.image.BufferedImage;

import gameObjects.*;
import main.*;

public class Entity {
	protected int worldX, worldY;
	protected int speed;
	protected int jumpSpeed;
	protected String direction; 
	protected int hitboxRadius;
	protected GamePanel gp;
	protected KeyHandler keyH;
	protected BufferedImage normal; 
	protected TileManager tileM; 
}
