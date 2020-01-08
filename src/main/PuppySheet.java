package main;

import main.gameengine.Sprite;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
//import puzzle.Settings;

public class PuppySheet extends Sprite {
	private boolean keypressed;
	private int imageRow;
	private int imageColumn;
	private int changeDelay = 5;
	private int startCount = 0;
	private int rows = 8;
	private int columns = 8;
	private BufferedImage[][] sprites = new BufferedImage[rows][columns];
	private int width = 174;
	private int height = 174;
	private String fileLocation = "src/puzzle/resources/puppy_f_warlk-1.png";
	
	   public PuppySheet() throws IOException {
	        System.out.println("PuppySheet");
	        initUI();        
	    }
	    
	    private void initUI() throws IOException {
	        BufferedImage bigImg = ImageIO.read(new File(fileLocation));
	    	for (int i = 0; i < rows; i++)
	    	{
	    	    for (int j = 0; j < columns; j++)
	    	    {
	    	        sprites[i][j] = bigImg.getSubimage(
	    	            j * width,
	    	            i * height,
	    	            width,
	    	            height
	    	        );
	    	    }
	    	}
//	    	image = sprites[0][0];
//	        getImageDimensions();
//	        x = (Settings.windowSizeX / 2) - (width / 2);
//	        y = (Settings.windowSizeY / 2) - (height / 2);
	    }
	    void move() {
	    	if (keypressed) {
	        	if (startCount >= changeDelay) {
	        		startCount = 0;
	        		if (imageColumn >= columns-1) {
	            		imageColumn = 0;
	            	} else {
	            		imageColumn++;
	            	}
	        		
	        	} else {
	        		startCount++;
	        	}
	    	}
//	    	image = sprites[imageRow][imageColumn];
	    }

	    void keyPressed(KeyEvent e) {
	    	keypressed = true;
	        int key = e.getKeyCode();

	        if (key == KeyEvent.VK_LEFT) {
	        	imageRow = 5;
	        }

	        if (key == KeyEvent.VK_RIGHT) {
	        	imageRow = 7;
	        }
	        
	        if (key == KeyEvent.VK_UP) {
	        	imageRow = 1;
	        }
	        
	        if (key == KeyEvent.VK_DOWN) {
	        	imageRow = 3;
	        }
	    }

	    void keyReleased(KeyEvent e) {
	    	keypressed = false;
	        int key = e.getKeyCode();
	        imageColumn = 0;
	        if (key == KeyEvent.VK_LEFT) {
	        	imageRow = 4; 
	        }

	        if (key == KeyEvent.VK_RIGHT) {
	        	imageRow = 6; 
	        }
	        
	        if (key == KeyEvent.VK_UP) {
	        	imageRow = 0; 
	        }

	        if (key == KeyEvent.VK_DOWN) {
	        	imageRow = 2; 
	        }
	    }

}

