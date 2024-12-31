package gameObjects;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entity.Monster;
import main.GamePanel; 

public class TileManager {
	
	private GamePanel gp; 
	private Tile[] tile; 
	private int tileSize;
	private ArrayList<Integer> clouds = new ArrayList<Integer>();
	private int[][] mapTileNum;
	private MapLoader mLoad;
	private final int FLOOR_HEIGHT = 9;
	private ArrayList<Monster> monsters;
	private Coin coin;
	private int map = 1;
	private final int FINAL_MAP = 2;
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		mLoad = new MapLoader(gp);
		
		tile = mLoad.GetTiles(map);
		tileSize = gp.getTileSize();
		clouds = mLoad.GetClouds(map);

		mapTileNum = mLoad.GetMap(map);
		monsters = mLoad.GetMonsters(map);
		coin = new Coin();
		coin.SetImage();
	}
	
	public void reset() {
		map = 1;
		mapTileNum = mLoad.GetMap(map);
		monsters = mLoad.GetMonsters(map);
		tile = mLoad.GetTiles(map);
		clouds = mLoad.GetClouds(map);
	}
	
	public boolean CollideWithTopPipe(double x, double y) {
		if ((y > gp.getTileSize() * (FLOOR_HEIGHT-1) || y < 0)  ) {
			return false;
		}
		
		int row = (int) (y / gp.getTileSize()) + 1;
		int col = (int) (x / gp.getTileSize());
		//System.out.println(row + " " + col + " " + mapTileNum.length + " " + mapTileNum[0].length);
		
		if (mapTileNum.length <= col) {
			//System.out.println("too many col");
			return false;
		}
		if (mapTileNum[0].length <= row) {
			//System.out.println("too many row");
			return false;
		}
		
		return mapTileNum[col][row] == 9;
	}
	
	
	public boolean ReachFinish(double x, double y) {
		if ((y > gp.getTileSize() * (FLOOR_HEIGHT-1) || y < 0)  ) {
			return false;
		}
		
		int row = (int) (y / gp.getTileSize()) + 1;
		int col = (int) (x / gp.getTileSize());
		//System.out.println(row + " " + col + " " + mapTileNum.length + " " + mapTileNum[0].length);
		
		if (mapTileNum.length <= col) {
			//System.out.println("too many col");
			return false;
		}
		if (mapTileNum[0].length <= row) {
			//System.out.println("too many row");
			return false;
		}
		if (mapTileNum[col][row] == 3) {
			//System.out.println(col + ", " + row);
		}
		return mapTileNum[col][row] == 3;
	}
	
	public boolean CheckIfFall(double x, double y) {
		return y > gp.getTileSize() * gp.getMaxRow();
	}
	
	public boolean CollideWithBlockCoin(double x, double y) {
		if ((y > gp.getTileSize() * (FLOOR_HEIGHT-1) || y < 0)  ) {
			return false;
		}
		
		int row = (int) (y / gp.getTileSize()) - 1;
		int col = (int) (x / gp.getTileSize());
		//System.out.println(row + " " + col + " " + mapTileNum.length + " " + mapTileNum[0].length);
		
		if (mapTileNum.length <= col) {
			//System.out.println("too many col");
			return false;
		}
		if (mapTileNum[0].length <= row || row < 0) {
			//System.out.println("too many row");
			return false;
		}
		if (mapTileNum[col][row] == 6) {
			mapTileNum[col][row] = 0;
			return true;
		}
		return false;
	}
	
	public boolean CheckIfCollide(double x, double y) {
		if (y < 0) {
			return false;
		}
		if ((int) (x / gp.getTileSize()) >= mapTileNum.length)
			return true;
		if ((y > gp.getTileSize() * (FLOOR_HEIGHT-1) || y < 0)  ) {
			if (mapTileNum[(int) (x / gp.getTileSize())][FLOOR_HEIGHT - 1] == 5 ) {

				return false;
			}
			return true;
		}
		
		int row = (int) (y / gp.getTileSize()) + 1;
		int col = (int) (x / gp.getTileSize());
		//System.out.println(row + " " + col + " " + mapTileNum.length + " " + mapTileNum[0].length);
		
		if (mapTileNum.length <= col) {
			//System.out.println("too many col");
			return false;
		}
		if (mapTileNum[0].length <= row) {
			//System.out.println("too many row");
			return true;
		}
		return tile[mapTileNum[col][row]].GetCollision();
	}
	
	public boolean MonsterCollide(double x, double y) {
		for (Monster m: monsters) {
			//System.out.println((int)(m.GetY() / gp.getTileSize()) + ", " + (int)(y / gp.getTileSize()));
			if (m.GetX() > x - 3 * gp.getTileSize() / 2 && m.GetX() < x + gp.getTileSize()/2 && m.GetY() > y && m.GetY() < y + gp.getTileSize()) 
				return true;
		}
		return false;
		
		//return MonsterCollideWCorner(x, y) || MonsterCollideWCorner(x + gp.getTileSize()/2, y) || MonsterCollideWCorner(x, y + gp.getTileSize()/2);
	}
	
	public boolean CoinCollide(double x, double y) {
		
		for (int i = 0; i < mapTileNum.length; i++) {
			
			for (int j = 0; j < mapTileNum[0].length; j++) {
				//System.out.println((int)(m.GetY() / gp.getTileSize()) + ", " + (int)(y / gp.getTileSize()));
				if (mapTileNum[i][j] == 4) {
					int coinX = i * gp.getTileSize();
					int coinY = j * gp.getTileSize();
					if (coinX > x - 3 * gp.getTileSize() / 2 && coinX < x + gp.getTileSize()/2 && coinY > y && coinY < y + gp.getTileSize()) {
						mapTileNum[i][j] = 1;
						return true;
					}
					
				}
				
				
			}
		}
		return false;
	}
	
	
	private void getCloudMap() {
		
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
		
		
	}
	
	private void getTileImage() {
		tile = mLoad.GetTiles(map);
	}
	
	public void update() {
		
		for (int i = 0; i < monsters.size(); i++) {
			monsters.get(i).update();
		}
		
	}
	
	public void draw(Graphics2D g2, int worldX) {
		
		//g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
		int startCol = (int)worldX / tileSize - gp.getMaxCol() / 2;
		int startRow = 0;
		
		int col = startCol; 
		int row = startRow; 
		int x = (int)(worldX / tileSize) * tileSize - worldX; 
		int y = 0;
		
		
		
		while (col - startCol <= gp.getMaxCol()+3 && row - startRow <= gp.getMaxRow()) {
			if (row >= FLOOR_HEIGHT && (col >= mapTileNum.length || col < 0 || mapTileNum[col][FLOOR_HEIGHT - 1] != 5)) {
				g2.drawImage(tile[0].getImage(), x, y, tileSize, tileSize, null);
			}
			else if (row >= FLOOR_HEIGHT && (col >= mapTileNum.length || col < 0 || mapTileNum[col][FLOOR_HEIGHT - 1] == 5)) {
				g2.drawImage(tile[1].getImage(), x, y, tileSize, tileSize, null);
			}
			else if (mapTileNum.length > col && mapTileNum[0].length > row && col > 0) {
				
				if (mapTileNum[col][row] == 5) {
					g2.drawImage(tile[1].getImage(), x, y, tileSize, tileSize, null);
				}
				
				else if (mapTileNum[col][row] == 4) {
					g2.drawImage(tile[1].getImage(), x, y, tileSize, tileSize, null);
					g2.drawImage(coin.GetImage(), x, y, tileSize, tileSize, null);
				}
				else if (mapTileNum[col][row] == 7|| mapTileNum[col][row] == 8 || mapTileNum[col][row] == 9) {
					g2.drawImage(tile[1].getImage(), x, y, tileSize, tileSize, null);
					g2.drawImage(tile[mapTileNum[col][row]].getImage(), x, y, tileSize, tileSize, null);
				}
				else 
					g2.drawImage(tile[mapTileNum[col][row]].getImage(), x, y, tileSize, tileSize, null);
			}
			else {
				g2.drawImage(tile[1].getImage(), x, y, tileSize, tileSize, null);
			}
			
			
			if (row == 2 && col > 15) {
				int index = Math.abs(col) % 16;
				if (index > 15) index = 15;
				//System.out.println(index);
				//System.out.println("col - start: " + (col - startCol) + "col:" + col + " row: " + row + " startCol:" + startCol);
				if (clouds.get(index) == 1 && (mapTileNum.length <= col || col <= 0 || (mapTileNum[col][2] != 6 && mapTileNum[col][2] != 0 && mapTileNum[col][2] != 3))) 
					g2.drawImage(tile[2].getImage(), x, y, tileSize, tileSize, null);
			}
			col++;
			x += tileSize; 
			
			
			if (col - startCol == gp.getMaxCol()) {
				if (row >= FLOOR_HEIGHT && (col >= mapTileNum.length || col < 0 || mapTileNum[col][FLOOR_HEIGHT - 1] != 5)) {
					g2.drawImage(tile[0].getImage(), x, y, tileSize, tileSize, null);
				}
				else if (row >= FLOOR_HEIGHT && (col >= mapTileNum.length || col < 0 || mapTileNum[col][FLOOR_HEIGHT - 1] == 5)) {
					g2.drawImage(tile[1].getImage(), x, y, tileSize, tileSize, null);
				}
				else if (mapTileNum.length > col && mapTileNum[0].length > row && col > 0) {
					
					if (mapTileNum[col][row] == 5) {
						g2.drawImage(tile[1].getImage(), x, y, tileSize, tileSize, null);
					}
					
					else if (mapTileNum[col][row] == 4) {
						g2.drawImage(tile[1].getImage(), x, y, tileSize, tileSize, null);
						g2.drawImage(coin.GetImage(), x, y, tileSize, tileSize, null);
					}
					else if (mapTileNum[col][row] == 7|| mapTileNum[col][row] == 8 || mapTileNum[col][row] == 9) {
						g2.drawImage(tile[1].getImage(), x, y, tileSize, tileSize, null);
						g2.drawImage(tile[mapTileNum[col][row]].getImage(), x, y, tileSize, tileSize, null);
					}
					else 
						g2.drawImage(tile[mapTileNum[col][row]].getImage(), x, y, tileSize, tileSize, null);
				}
				else {
					g2.drawImage(tile[1].getImage(), x, y, tileSize, tileSize, null);
				}
				
				if (row == 2 && col > 15) {
					int index = Math.abs(col) % 16;
					if (index > 15) index = 15;
					if (clouds.get(index) == 1 && (mapTileNum.length <= col || col <= 0 || (mapTileNum[col][2] != 6 && mapTileNum[col][2] != 0 && mapTileNum[col][2] != 3))) 
						g2.drawImage(tile[2].getImage(), x, y, tileSize, tileSize, null);
				}
				col = startCol;
				x = (int)( worldX / tileSize )* tileSize - worldX; 
				row++;
				y += tileSize;
			}
		}
		
		for (int i = 0; i < monsters.size(); i++) {
			monsters.get(i).draw(g2, worldX);
		}
		
		try {
		    //create the font to use. Specify the size!
		    Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/font2.ttf")).deriveFont(30f);
		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    //register the font
		    ge.registerFont(customFont);
		    


			g2.setColor(new Color(255, 255, 255));
			g2.setFont(customFont);
			g2.drawString("MARIA", 20, 35);
			int score = gp.getScore();
			g2.drawString(String.format("%05d", score), 20, 60);
			

			int time = (int) (gp.getTime() / 100) * 100;
			
			g2.drawString("TIMER", 670, 35);
			g2.drawString(String.format("%05d",time), 670, 60);
			
			
		} catch (IOException e) {
		    e.printStackTrace();
		} catch(FontFormatException e) {
		    e.printStackTrace();
		}
		
		
		/*g2.setColor(new Color(100, 100, 100));
		//g2.fillRoundRect(10, 10, gp.getTileSize()*3, gp.getTileSize(), 10, 10);
		g2.setColor(new Color(255, 255, 255));
		g2.setFont(new Font("title", 0, 20));

		
		
		
		g2.setColor(new Color(100, 100, 100));
		//g2.fillRoundRect(600, 10, gp.getTileSize()*3, gp.getTileSize(), 10, 10);
		g2.setColor(new Color(255, 255, 255));
		g2.setFont(new Font("title", 0, 20));*/
		
		
		
	}
	
	public int GetMap() {
		return map;
	}
	
	public void SetMap(int i) {
		if (i < 0 || i > FINAL_MAP) {
			return;
		}
		this.map = i; 
		monsters = mLoad.GetMonsters(map);
		mapTileNum = mLoad.GetMap(map);
		tile = mLoad.GetTiles(map);
		clouds = mLoad.GetClouds(map);
	}
	
	public int NextMap() {
		if (map == FINAL_MAP) {
			return -1;
		}
		return map+1;
	}
	
	
	
}