import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JFrame;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.*;


public class LoadButtonEvent implements ActionListener {
	private static JTextField fileName;
	private static JFrame frame;
	private static ArrayList<String> fileContent;

	public LoadButtonEvent(){
		this.fileContent = new ArrayList<String>();
	}
	
	public LoadButtonEvent(JTextField fileName, JFrame frame){
		this.fileName = fileName;
		this.frame = frame;
		this.fileContent = new ArrayList<String>();
		

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"SRT Files", "srt");

		chooser.setFileFilter(filter);
		BufferedReader in;//needed*
		File selectedFile;
		FileReader reader = null;//needed*,look these three up for further info
		//opens dialog if button clicked*/
		if (chooser.showOpenDialog(this.frame) == JFileChooser.APPROVE_OPTION) {
			//gets file from dialog
			selectedFile = chooser.getSelectedFile();
			//makes sure files can be processed before proceeding
			this.fileName.setText("Requiem.srt");
			if (selectedFile.canRead() && selectedFile.exists()) {
				String fileChoosedName = selectedFile.getName();
				try {
					this.fileContent = readSubtitleFile(selectedFile.getAbsolutePath());		           
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				this.fileName.setText(fileChoosedName);

			}
		}
	}
	
	public ArrayList<String> getFileContent(){
		return this.fileContent;		
	}

   private ArrayList<String> readSubtitleFile(String filePath) throws IOException{
	   BufferedReader subtitleFile = new BufferedReader(new FileReader(filePath));
	   ArrayList<String> fileContent = new ArrayList<String>(); 
	   String line = subtitleFile.readLine();
	   try {		   
		   while(line != null){			   
			   fileContent.add(line);
			   line = subtitleFile.readLine();
		   }
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		subtitleFile.close();
	}
	  
       return fileContent;
	   
   }

}
