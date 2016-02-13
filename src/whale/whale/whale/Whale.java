package whale.whale.whale;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Whale {

	private BufferedImage whaleImage = null;
	private int height=0;
	private int width=0;
	private  Graphics g = null;
	
	private Pixel[][] pixels;
	
	private int whitePercent;
	private int bluePercent;
	private int blackPercent;
	
	//Getters and setters
	public int getRGB(int x, int y){
		return this.whaleImage.getRGB(x, y);
	}
	public void setRGB(int x, int y, int col){
		this.whaleImage.setRGB(x,y,col);
	}
	public BufferedImage getImage(){
		return this.whaleImage;
	}
	public int getHeight(){
		return this.height;
	}
	public Graphics getGraphics(){
		return this.g;
	}
	
	public Pixel[][] getPixels(){
		Pixel[][] pixels = new Pixel[this.width][this.height];
		for(int x = 0; x < pixels.length; x++ ) 
			for(int y = 0; y < pixels[x].length; y++)
				pixels[x][y] = new Pixel(x,y,this);
		return pixels;
	}
	public static BufferedImage resizeImg(BufferedImage img, int newW, int newH)
    {
    int w = img.getWidth();
    int h = img.getHeight();
    BufferedImage dimg = new BufferedImage(newW, newH, img.getType());
    Graphics2D g = dimg.createGraphics();
    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
            RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
    g.dispose();
    return dimg;      
   }
	File file;
	public File getFile(){
		return file;
	}
	
	public Whale(File f){
		
		try{
			BufferedImage image = resizeImg(ImageIO.read(f),300,300);
			file=f;
			this.whaleImage = image;
			this.g = image.getGraphics();
			this.height = image.getHeight();
			this.width = image.getWidth();
			this.pixels = getPixels();
			
		}catch(Exception e){e.printStackTrace();}
		
	}
	
	
	
}
