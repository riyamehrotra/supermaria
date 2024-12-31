package main;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class Music {
	
	private Clip clip2;
	private int file = 0; 
	private int background = 0;
	
	public Music() {
		try {
			clip2 = AudioSystem.getClip();

        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public void play(int file) {
		
		
		
		try {
			Clip clip = AudioSystem.getClip();
			//clip.close();
            // Load the audio file
            File audioFile = new File("src/main/" + String.format("%02d", file) + ".wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
           
            if (file == 1 || file == 5) {
            	//System.out.print("got coin!");
            	if (clip2.isRunning()) {
            		return;
            	}
            	//System.out.println("here");
            	clip2.close();
            	clip2.open(audioStream);           	
            	clip2.start();
            	return;
            }
            
            if (file == 6) {
            	//System.out.println("here");
            }
            this.file = file;
            // Open the clip and load the audio stream
            clip.close();
            clip.open(audioStream);
            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public void stopAll() {
		clip2.close();
	}
	
}