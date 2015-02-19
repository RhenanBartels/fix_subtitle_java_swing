import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JFrame;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import static java.nio.charset.StandardCharsets.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.*;
import javax.swing.text.BadLocationException;


public class LoadButtonEvent implements ActionListener {
	private static JTextField fileName;
	private static JFrame frame;
	private ArrayList<String> fileContent;
	private static String filePath;
	private static JScrollPane scrollPane;
	private static JTextArea textArea;

	public LoadButtonEvent(){
		this.fileContent = new ArrayList<String>();
	}
	
	public LoadButtonEvent(JTextField fileName, JFrame frame,
			JScrollPane scrollPane, JTextArea textArea){
		this.fileName = fileName;
		this.frame = frame;
		this.fileContent = new ArrayList<String>();
		this.scrollPane = scrollPane;
		this.textArea = textArea;


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
			this.textArea.setText("");
			if (selectedFile.canRead() && selectedFile.exists()) {
				this.frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				String fileChoosedName = selectedFile.getName();
				try {
					this.fileContent = readSubtitleFile(selectedFile.getAbsolutePath());	
					this.filePath = selectedFile.getAbsolutePath();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				this.fileName.setText(fileChoosedName);
				this.frame.setSize(350, 335);  
			}
			this.frame.setCursor(Cursor.getDefaultCursor());
			this.scrollPane.setVisible(true);
			this.textArea.setVisible(true);
			this.textArea.setCaretPosition(0);
			
		}
	}

	public ArrayList<String> getFileContent(){
		return this.fileContent;		
	}
	public String getFileName(){
		return this.filePath;
	}

   private ArrayList<String> readSubtitleFile(String filePath) throws IOException{	 
	   BufferedReader subtitleFile = new BufferedReader(new FileReader(filePath));
	   ArrayList<String> fileContent = new ArrayList<String>(); 
	   String line = subtitleFile.readLine();
	   try {		   		   
		   while(line != null){		
			   byte ptext[] = line.getBytes(ISO_8859_1); 
			   String value = new String(ptext, UTF_8); 
			   this.textArea.append(value + "\n");
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
