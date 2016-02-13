package whale.file.handler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

import javax.swing.JOptionPane;

import whale.main.main.Main;
import whale.main.window.Window;

public class FileHandler {

	public File lastImported;
	
	File res = new File("res/");
	File sett = new File("sett/");
	File reup = new File("reup/");
	File rightFrame = new File("rightFrame/");
	
	Main m;
	
	public FileHandler(Main m){this.m=m;}
	
	public String getName(){
		return lastImported.getName().substring(0 , lastImported.getName().indexOf("."));
	}
	public String getExtention(){
		return lastImported.getName().substring(lastImported.getName().indexOf("."));
	}
	/*public int getNumUpTo(){
		checkAllFilesExist();
		int num=0;
		try{
		BufferedReader reader = new BufferedReader(new FileReader(new File(reup.toString()+"reup.txt")));//get the num
		String line = reader.readLine();
		num = Integer.parseInt(line);
		reader.close();
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(reup.toString()+"reup.txt"))); //update the num
		writer.println(num++);
		writer.close();
		}catch(Exception e){e.printStackTrace();}
		return num % 4; // The number that we are modding with is the amount of files we have.
	}*/
	public void checkAllFilesExist(){
		
		if(!res.exists() || !res.canRead()){
			res.mkdir();	
		}
		if(!sett.exists() || !sett.canRead()){
			sett.mkdir();
			try{
			PrintWriter w = new PrintWriter(new File(sett.toString() + "settings.txt"));
			//Settings to be implemented here later....
			w.close();
			}catch(Exception e){e.printStackTrace();}
		}
		if(!reup.exists() || !reup.canRead()){
			reup.mkdir();
			try{
				PrintWriter w = new PrintWriter(new File(reup.toString() + "reup.txt"));
				w.print("0");
				w.close();
			}catch(Exception e){e.printStackTrace();}
		}
		if(!rightFrame.exists() || !rightFrame.canRead()){
			rightFrame.mkdir();
		}
		
		
	}
	
	/*public void importFile(String file){
		checkAllFilesExist();
		lastImported = new File(file);
		try{
		Files.copy(lastImported.toPath(), new File(res.toString()+getNumUpTo()+getExtention() ).toPath() );
		//Now that we imported the original, let us start making more files that we will use in the Res folder
		PrintWriter writer = new PrintWriter("res/"+getNumUpTo()+"name.txt","UTF-8");
		PrintWriter w = new PrintWriter("res/"+getNumUpTo()+"date.txt","UTF-8");
		PrintWriter wr = new PrintWriter("res/"+getNumUpTo()+"description.txt","UTF-8");
		writer.close();
		w.close();
		wr.close();
		
		}catch(Exception e){e.printStackTrace();}
	}*/
	
	
	public int getNumUpTo() throws IOException{
		int count=updateReuptake();
		
		if(count%4==0){
			return count/4;
		}else{
			return (count/4);
		}
	}
	public void importFile(File f) throws IOException{
		checkAllFilesExist();
		String extension;
		int i = f.toString().indexOf(".");
		extension = f.toString().substring(i);
		Files.copy(f.toPath(),new File("res/"+getNumUpTo()+extension).toPath());
		PrintWriter writer = new PrintWriter("res/"+getNumUpTo()+"name.txt","UTF-8");
		PrintWriter w = new PrintWriter("res/"+getNumUpTo()+"date.txt","UTF-8");
		PrintWriter wr = new PrintWriter("res/"+getNumUpTo()+"description.txt","UTF-8");
		writer.close();
		w.close();
		wr.close();
		
		m.refresh();
		
	}
	

	public void createReuptake() throws IOException{
		File reup = new File("reuptake");
		File reuptake = new File("reuptake/reup.txt");
		if(reup.isDirectory()){
		}else{
			reup.mkdir();
		}
		if(reuptake.exists()){
		}else{
			PrintWriter writer = new PrintWriter("reuptake/reup.txt");
			System.out.println("Writing to new file");
			writer.write("-1\n");
			writer.close();
		
		}
		
	}
	public int updateReuptake() throws IOException{
		createReuptake();
		System.out.println("In update reuptake");
		int fileUpTo=0;
		BufferedReader reader = new BufferedReader(new FileReader("reuptake/reup.txt"));
		String line;
		fileUpTo = Integer.parseInt(reader.readLine());
		fileUpTo++;
		System.out.println(fileUpTo);
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("reuptake/reup.txt")));
		writer.print(fileUpTo);
		writer.close();
		reader.close();
		return fileUpTo;
		
	}
	
	
	
public void folderUpload(File f) throws IOException{
		
		for(File temp: f.listFiles()){
			int posOfDot = temp.getName().indexOf(".");
			String ext = temp.getName().substring(posOfDot+1).toLowerCase();
			if(ext.equals("jpg") || ext.equals("png")){
				importFile(temp);				
			}else{
			}
		}
		JOptionPane.showMessageDialog(null, "Import successful!");
		m.refresh();
		new Window();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public void deleteFile(File f){
		lastImported = f;
		try{
		Files.delete(f.toPath());
		Files.delete(new File(res.toString()+"/"+getName()+"name.txt").toPath());
		Files.delete(new File(res.toString()+"/"+getName()+"date.txt").toPath());
		Files.delete(new File(res.toString()+"/"+getName()+"description.txt").toPath());
		}catch(Exception e){e.printStackTrace();}
		m.refresh();
	}
	
	
	
	
}





