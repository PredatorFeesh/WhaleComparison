package whale.main.main;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import whale.main.window.Window;


public class ImageWindow extends JFrame{
	private static final long serialVersionUID = 1L;
	public int fileName;
	
	JLabel image = new JLabel();
	
	//JTextAreas and JLabels for them
	JPanel namePanel = new JPanel();
	JTextField name;
	JLabel nameLabel = new JLabel("Name: ");
	
	JPanel items = new JPanel(new GridLayout(6,1));
	JTextField date = new JTextField();
	JLabel dateLabel = new JLabel("\nDate spotted: ");
	
	JPanel descriptionPanel = new JPanel();
	JTextArea description = new JTextArea(10,20);
	JLabel descriptionLabel = new JLabel("Add a Description: ");
	
	
	
	Dimension SIZE = new Dimension(Window.WIDTH,Window.HEIGHT);
	@SuppressWarnings("resource")
	public ImageWindow(File f) throws IOException{
		super(f.toString());
		setVisible(true);
		setPreferredSize(SIZE);
		setMinimumSize(SIZE);
		setMaximumSize(SIZE);
		pack();
		setLocationRelativeTo(null);
		setLayout(new GridLayout(2,3));
		//End default init for window
		int posOfDot = f.getName().indexOf(".");
		String fileitself = f.getName().substring(0,posOfDot);
		fileName = Integer.parseInt(fileitself);
		//end init for filename
		
		BufferedReader reader = new BufferedReader(new FileReader("res/"+fileName+"name.txt"));
		name=new JTextField(reader.readLine());
		reader = new BufferedReader(new FileReader("res/"+fileName+"date.txt"));
		date=new JTextField(reader.readLine());
		reader = new BufferedReader(new FileReader("res/"+fileName+"description.txt"));
		description.setText(reader.readLine());
		reader.close();
		
		
		
		//DO NOT FORGET TO INIT ALL FOR THE TEXTAREA AND TEXTFIELD HERE
		date.setPreferredSize(new Dimension(230,24));
		name.setPreferredSize(new Dimension(230,24));
		//Set Preffered Size here
		Image img =new ImageIcon(f.toString()).getImage();  
		Image newimg = img.getScaledInstance(200, 300,  java.awt.Image.SCALE_SMOOTH);  
		ImageIcon newIcon = new ImageIcon(newimg);
		namePanel.add(new JLabel(newIcon));
		name.addKeyListener(
	            new KeyListener(){
	                public void keyPressed(KeyEvent e){
	                    if(e.getKeyCode() == KeyEvent.VK_ENTER){
	                    	try {
								PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("res/"+fileName+"name.txt")));
								writer.write(""+name.getText());
								writer.close();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
	                    }       
	                }public void keyReleased(KeyEvent arg0) {}public void keyTyped(KeyEvent arg0) {}
	            }
	        );
		
		date.addKeyListener(
	            new KeyListener(){
	                public void keyPressed(KeyEvent e){
	                    if(e.getKeyCode() == KeyEvent.VK_ENTER){
	                    	try {
								PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("res/"+fileName+"date.txt")));
								writer.write(""+date.getText().replace(" ","/"));
								writer.close();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
	                    }       
	                }public void keyReleased(KeyEvent arg0) {}public void keyTyped(KeyEvent arg0) {}
	            }
	        );
		//Date init
		items.add(nameLabel);
		items.add(name);
		items.add(dateLabel);
		items.add(date);
		
		//Description init
		descriptionPanel.add(descriptionLabel);
		descriptionPanel.add(description);
		description.addKeyListener(
	            new KeyListener(){
	                public void keyPressed(KeyEvent e){
	                    if(e.getKeyCode() == KeyEvent.VK_ENTER){
	                    	try {
								PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("res/"+fileName+"description.txt")));
								writer.write(""+description.getText());
								writer.close();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
	                    }       
	                }public void keyReleased(KeyEvent arg0) {}public void keyTyped(KeyEvent arg0) {}
	            }
	        );
		
		
		add(namePanel);
		add(items);
		add(descriptionPanel);
		
		
		
		
		
		
	}
	
	
	public void writeToFile(String editing,String value) throws IOException{

	
		
	}
	public void createFile(File file){
		
	}
	public void readFile(File file){
		BufferedReader reader;		
		
	}
	
	
}
