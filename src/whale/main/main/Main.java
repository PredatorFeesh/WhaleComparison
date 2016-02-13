package whale.main.main;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import whale.file.handler.FileHandler;
import whale.main.window.Window;
import whale.whale.whale.Whale;

public class Main implements ActionListener, MouseListener {
	
	Window windowMaker = new Window();
	FileHandler fileHandler = new FileHandler(this);
	boolean selectMode=false;

	
	public JFrame frame;
	
	//Arrays and array lists
		static HashMap<JLabel, Whale> pics = new HashMap<JLabel, Whale>(); // Could use pics.getKey() and pics.getValue()	
		static ArrayList<Whale> whales = new ArrayList<Whale>();
		
		//JPanels
		JPanel pictures = new JPanel();
		JPanel rightPanel = new JPanel();
		
		
		JLabel rightFramePicture;
	
	//Menu items
	JMenuBar menuBar= new JMenuBar(); 
	JMenu file = new JMenu("File");
	JMenuItem importButton = new JMenuItem("Import picture");
	JMenuItem deleteButton = new JMenuItem("Delete picture");
	JMenuItem refreshFrame = new JMenuItem("Refresh frame");
	JMenuItem importFolder = new JMenuItem("Import folder");
	
	JMenu rightFrame = new JMenu("Right frame");
	JMenuItem selectPicture = new JMenuItem("Select Image to move");
	JMenuItem moveBack = new JMenuItem("Move image back");
	
	//JFileChoosers
		JFileChooser fileToImport = new JFileChooser(new File("../../"));
		JFileChooser fileToDelete = new JFileChooser(new File("res"));
		JFileChooser folderSelected = new JFileChooser(new File("../../"));
		
		//JScrollBars
		JScrollPane bar;
		
	
	
	public static void main(String[] args){
		Main main = new Main();
		
	}
	
	public void refresh(){
		frame.dispose();
		new Main();
	}
	
	public Main(){
		 frame = windowMaker.makeFrame("Whale Database");
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setJMenuBar(menuBar);
		 
		
			
			//Add all the JMenu parts
			file.add(importButton);
			file.add(deleteButton);
			file.add(refreshFrame);
			file.add(importFolder);
			importFolder.addActionListener(this);
			refreshFrame.addActionListener(this);
			importButton.addActionListener(this);
			deleteButton.addActionListener(this);
			menuBar.add(file);

			
			
			rightFrame.add(selectPicture);
			rightFrame.add(moveBack);
			moveBack.addActionListener(this);
			selectPicture.addActionListener(this);
			menuBar.add(rightFrame);
			
			
			frame.setJMenuBar(menuBar);	
			//End JMenu initilization
			FileFilter imageFilter = new FileNameExtensionFilter(
				    "Image files", ImageIO.getReaderFileSuffixes());
			fileToImport.addChoosableFileFilter(imageFilter);
			fileToImport.setAcceptAllFileFilterUsed(false);
			fileToDelete.addChoosableFileFilter(imageFilter);
			fileToDelete.setAcceptAllFileFilterUsed(false);
			folderSelected.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			//End init of JFIleChoosers
			bar = new JScrollPane(pictures, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			bar.setBounds(0, 20, 600, 600);
			//scroll.setLayout(null);
			
			//end init of jscroller
			pictures.setLayout(new BoxLayout(pictures,BoxLayout.PAGE_AXIS));
			//init of labels
			
			
			
			
			
			
			
			
			
			//ALL THE CODE GOES UNDER HERE
			
			
			
			
			drawImagesInRes();
			drawImagesInRightFrame();
			


			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			

			
			frame.add(rightPanel,BorderLayout.EAST);
			pictures.revalidate();
			rightPanel.revalidate();
			frame.add(bar);
			pictures.revalidate();
			rightPanel.revalidate();
	}
	
	

	private void drawImagesInRes() {
		// TODO Auto-generated method stub
		for(File f : new File("res").listFiles()){
			if(f.getName().substring(f.getName().indexOf(".")).equals(".jpg") || 
					f.getName().substring(f.getName().indexOf(".")).equals(".JPG") ||
					f.getName().substring(f.getName().indexOf(".")).equals(".png") ||
					f.getName().substring(f.getName().indexOf(".")).equals(".PNG") 
			){
				Whale whale = new Whale(f);
				JLabel p = new JLabel(new ImageIcon(whale.getImage()));
				Main.pics.put(p, whale);
				Main.whales.add(whale);
				pictures.add(p);
				p.addMouseListener(this);
			}// End if image
			
		}
		
	}
	
	
	
	public void moveImageToRightFrame(File picToMove) throws IOException{
		File movedTo = new File("rightFrame");
		if(movedTo.isDirectory()){
		}else{movedTo.mkdir();}
		int count=0;
		for(File f: movedTo.listFiles()){
			count++;
		}
		if(count>1){
		File n = null;
			for(File f: movedTo.listFiles()){
				n = f;
				break;
			}
			Files.move(n.toPath(),new File("res/"+n.getName()).toPath());
			//Files.delete(n.toPath());
			
		}else{
		
		Files.move(picToMove.toPath(),(new File(movedTo.getName()+"/"+picToMove.getName())).toPath());
//		Files.delete(picToMove.toPath());
		
		selectMode=false; 
		refresh();
		}
		
	}
	public void drawRightPanel(){
		if(new File("rightFrame").isDirectory()){
		File file = null;
		for(File f: new File("rightFrame").listFiles()){
			file = f;
			System.out.println(file);
			break;
		}
		if(file.exists()){
		Image img =new ImageIcon(file.toString()).getImage();  
		Image newimg = img.getScaledInstance(230, 310,  java.awt.Image.SCALE_SMOOTH);  
		ImageIcon newIcon = new ImageIcon(newimg);
		rightFramePicture = new JLabel(newIcon);
		rightFramePicture.addMouseListener(this);
		rightPanel.add(rightFramePicture);
		frame.add(rightPanel,BorderLayout.EAST);
		
		}else{}
		}else{
			
		}
	}
	

	private void drawImagesInRightFrame() {
		// TODO Auto-generated method stub
		if(new File("rightFrame").isDirectory()){
			File file = null;
			for(File f: new File("rightFrame").listFiles()){
				file = f;
				System.out.println(file);
				break;
			}
			if(file.exists()){
			Image img =new ImageIcon(file.toString()).getImage();  
			Image newimg = img.getScaledInstance(230, 310,  java.awt.Image.SCALE_SMOOTH);  
			ImageIcon newIcon = new ImageIcon(newimg);
			rightFramePicture = new JLabel(newIcon);
			rightFramePicture.addMouseListener(this);
			rightPanel.add(rightFramePicture);
			frame.add(rightPanel,BorderLayout.EAST);
			
			}else{}
			}else{
				
			}
	}

	public void actionPerformed(ActionEvent e) {
		Object a = e.getSource();
		
		if(a==importButton){
			int returnValue = fileToImport.showOpenDialog(null);
			try {
				if(returnValue == JFileChooser.APPROVE_OPTION)
				fileHandler.importFile(fileToImport.getSelectedFile());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			finally{
				refresh();
			}
			
			
		}else if(a==deleteButton){
			int val = fileToDelete.showOpenDialog(null);
			if(val == JFileChooser.APPROVE_OPTION)
			fileHandler.deleteFile(fileToDelete.getSelectedFile());
			refresh();
		}else if(a==refreshFrame){
			refresh();
		} else if (a==importFolder){
			int n = JOptionPane.showConfirmDialog(null,"We recommend to have only pictures in your folder. We filter out non-images so continuing should work either way.","Images",JOptionPane.YES_NO_OPTION);
			
			if(n==JOptionPane.YES_OPTION){
			int val =folderSelected.showOpenDialog(null);
			if(val==JFileChooser.APPROVE_OPTION){
			try {
				fileHandler.folderUpload(folderSelected.getSelectedFile());
			} catch (IOException e1) {
				e1.printStackTrace();
			}finally{
				refresh();
				
			}
			}
			}else{
				JOptionPane.showMessageDialog(null,"Please check check and come back");
			}
		}else if(a==selectPicture){
			JOptionPane.showMessageDialog(null, "Select a file to move to the right frame");
			selectMode=true;
			
		}else if(a==moveBack){
			int n = JOptionPane.showConfirmDialog(null,"Are you sure you want to move the picture back?");
			if(n==JOptionPane.YES_OPTION){
			File fi=null;
			for(File f:new File("rightFrame").listFiles()){
				fi=f;
				break;
			}
			try {
				Files.move(fi.toPath(),new File("res/"+fi.getName()).toPath());
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}finally{
				refresh();
			}
			}else{}
			
		}
		
	}

	public void mouseClicked(MouseEvent e) {
		
Object o = e.getSource();
		
		if(o!=rightFramePicture){
		System.out.println(pics.get(o));//WORKS!
		if(!selectMode){
		try {
			new ImageWindow(pics.get(o).getFile());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		}else {//End if select=false
		try{
			moveImageToRightFrame(pics.get(o).getFile());
		}catch(IOException e1){
			e1.printStackTrace();
		}
		}
		}else if( o == rightFramePicture){
			File f = new File("rightFrame");
			int count=0;
			for(File a : f.listFiles()){
				count++;
			}
			if(count>1){
				for(File a : f.listFiles()){
					f = a;
					break;
				}
			}
			try{
			new ImageWindow(f);
			}catch(IOException e1){
				e1.printStackTrace();
			}
		}
			
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}
	

}


