package main;
import javax.swing.JFrame;

public class Runner {
	
	static JFrame window = new JFrame();
	
	public static void main(String[] args) {
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Super Mario!");
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		window.pack();

		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gamePanel.startGameThread();
	} 
	
	public static void stop() {
		System.exit(0);
	}
	
}