package whale.main.window;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends JFrame {
	
	public static final int HEIGHT = 600;
	public static final int WIDTH = HEIGHT/4 *5;
	public final Dimension SIZE = new Dimension(WIDTH,HEIGHT);
	
	public Window(){}

	public JFrame makeFrame(String title){
		
		JFrame frame = new JFrame();
		
		frame.setPreferredSize(SIZE);
		frame.setMinimumSize(SIZE);
		frame.setMaximumSize(SIZE);
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		frame.setTitle(title);
	    
		
		
		return frame;
	}
	

}
