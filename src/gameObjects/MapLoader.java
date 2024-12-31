package gameObjects;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entity.Monster;
import main.GamePanel;

public class MapLoader {
	
	private ArrayList<Monster> monsters = new ArrayList<Monster>();
	private int[][] map;
	private GamePanel gp;
	
	public MapLoader(GamePanel gp) {
		this.gp = gp;
	}
	
	
	public int[][] GetMap(int i) {
		
		String val = "";
		String source = "";
		int numOfCol = 0;
		int numOfRow = 0;
		if (i == 1) {
			source = "gameObjects/map01.txt";
		}
		if (i == 2) {
			source = "gameObjects/map02.txt";
		}
		
		try {
			Class cls = Class.forName("gameObjects.TileManager");

         	// returns the ClassLoader object associated with this Class
         	ClassLoader cLoader = cls.getClassLoader();


         	// input stream
         	InputStream inputStream = cLoader.getResourceAsStream(source);
         	if(inputStream != null) {
        	 	BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream));

            	// reads each line
            	String l;
            	while((l = buffer.readLine()) != null) {
            		numOfCol = l.length()/2;
            		val = val + l;
            		//System.out.println(l);
            		numOfRow++;
            	} 
            	buffer.close();
            	inputStream.close();
   
            	
            	
         	}
         	//System.out.print(val);
         	String currentMap = val;
         	int[][]mapTileNum = new int[numOfCol][numOfRow];
         	
         	int col = 0, row = 0;
         	
         	while (col < numOfCol && row < numOfRow) {
         		int index = 2*row*numOfCol + 2*col;
         		mapTileNum[col][row] = currentMap.charAt(index) - '0';
    			col++;
    			
    			if (col == numOfCol) {
    				col = 0;
    				row++;
    			}
    			
    			
    		}
         	
         	return mapTileNum;
         	
		} catch(Exception e) {
			System.out.println(e);
      	}
		
		return null;
	}
	

	public ArrayList<Monster> GetMonsters(int i) {
		monsters.clear();
		if (i == 1) {
			monsters.add(new Monster(gp, gp.getTileSize() * 24, gp.getTileSize() * 27, gp.getTileSize() * 8));
			monsters.add(new Monster(gp, gp.getTileSize() * 29, gp.getTileSize() * 33, gp.getTileSize() * 8));
			monsters.add(new Monster(gp, gp.getTileSize() * 35, gp.getTileSize() * 39, gp.getTileSize() * 8));
		}
		return monsters;
	}
	
	public Tile[] GetTiles(int i) {
		Tile[] tile = new Tile[10];
		if (i == 1) {
			try {
				tile[0] = new Tile(true, 0);
				BufferedImage file = ImageIO.read(new File("src/gameObjects/wall.png"));
				tile[0].setImage(file);
				
				tile[1] = new Tile(false, 1);
				file = ImageIO.read(new File("src/gameObjects/sky.png"));
				tile[1].setImage(file);
				
				tile[2] = new Cloud(2);
				
				tile[4] = tile[1];
				tile[5] = tile[1];
				
				tile[6] = new Tile(true, 6);
				file = ImageIO.read(new File("src/gameObjects/blockcoin.png"));
				tile[6].setImage(file);
				
				tile[3] = new Tile(false, 3);
				file = ImageIO.read(new File("src/gameObjects/finish.png"));
				tile[3].setImage(file);
				
				tile[7] = new Tile(true, 7);
				file = ImageIO.read(new File("src/gameObjects/pipe.png"));
				tile[7].setImage(file);
				
				tile[8] = new Tile(true, 8);
				file = ImageIO.read(new File("src/gameObjects/pipetop.png"));
				tile[8].setImage(file);
				
				tile[9] = tile[8];
				
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		if (i == 2) {
			try {
				tile[0] = new Tile(true, 0);
				tile[9] = new Tile(false, 9);
				BufferedImage file = ImageIO.read(new File("src/gameObjects/undergroundwall.png"));
				tile[0].setImage(file);
				tile[9].setImage(file);
				
				
				tile[1] = new Tile(false, 1);
				file = ImageIO.read(new File("src/gameObjects/undergroundsky.png"));
				tile[1].setImage(file);
				
				tile[2] = new Cloud(2);
				
				tile[4] = tile[1];
				tile[5] = tile[1];
				
				tile[6] = new Tile(true, 6);
				file = ImageIO.read(new File("src/gameObjects/blockcoin.png"));
				tile[6].setImage(file);
				
				tile[3] = new Tile(false, 3);
				file = ImageIO.read(new File("src/gameObjects/finish.png"));
				tile[3].setImage(file);
				
				tile[7] = new Tile(true, 7);
				file = ImageIO.read(new File("src/gameObjects/pipe.png"));
				tile[7].setImage(file);
				
				tile[8] = new Tile(true, 8);
				file = ImageIO.read(new File("src/gameObjects/pipetop.png"));
				tile[8].setImage(file);
				
				
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		return tile;
	}
	
	public ArrayList<Integer> GetClouds(int i) {
		ArrayList<Integer> clouds = new ArrayList<Integer>();
		
		int rand = (int)(Math.random() * 2);
		
		if (rand == 0) {
			clouds.add(0);
			clouds.add(0);
			clouds.add(1);
			clouds.add(0);
			clouds.add(0);
			clouds.add(0);
			clouds.add(0);
			clouds.add(1);
			clouds.add(0);
			clouds.add(0);
			clouds.add(0);
			clouds.add(1);
			clouds.add(0);
			clouds.add(0);
			clouds.add(0);
			clouds.add(1);
			clouds.add(0);
			clouds.add(0);

		}
		
		else {
			clouds.add(0);
			clouds.add(1);
			clouds.add(0);
			clouds.add(0);
			clouds.add(0);
			clouds.add(1);
			clouds.add(0);
			clouds.add(0);
			clouds.add(0);
			clouds.add(0);
			clouds.add(1);
			clouds.add(0);
			clouds.add(0);
			clouds.add(1);
			clouds.add(0);
			clouds.add(0);
			clouds.add(0);
			clouds.add(0);

		}
		
		if (i == 2) {
			clouds.clear();
			clouds.add(0);
			clouds.add(0);
			clouds.add(0);
			clouds.add(0);
			clouds.add(0);
			clouds.add(0);
			clouds.add(0);
			clouds.add(0);
			clouds.add(0);
			clouds.add(0);
			clouds.add(0);
			clouds.add(0);
			clouds.add(0);
			clouds.add(0);
			clouds.add(0);
			clouds.add(0);
			clouds.add(0);
			clouds.add(0);
		}
		
		return clouds;
	}

}