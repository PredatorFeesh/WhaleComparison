package whale.whale.whale;

public class Pixel {
	
	private int x;
	private int y;
	private Whale ref;
	
	public Pixel(int x, int y, Whale w){
		this.x=x;
		this.y=y;
		this.ref=w;
		
	}
	
	public int getRed(){
		return (this.ref.getRGB(x, y) >> 16 ) & 0xff;
	}
	public int getGreen(){
		return (this.ref.getRGB(x,y) >> 8) & 0xff;
	}
	public int getBlue(){
		return this.ref.getRGB(x,y) & 0xff;
	}
	public void setRed(int a){
		this.ref.setRGB(x,y, (a << 16) + (getGreen() << 8) + getBlue() );
	}
	public void setGreen(int a){
		this.ref.setRGB(x,y, (getRed() << 16) + (a << 8) + getBlue() );
	}
	public void setBlue(int a){
		this.ref.setRGB(x,y, (getRed() << 16) + (getGreen() << 8) + a );
	}

}
