import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.*;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.util.Arrays;

public class FixButtonEvent implements ActionListener {
	private ArrayList<String> fileContent;
	private String pattern = "\\d{2}:\\d{2}:\\d{2},\\d{3} --> \\d{2}:\\d{2}:\\d{2},\\d{3}";
	private String patternTime = "\\d{2}:\\d{2}:\\d{2},\\d{3}";
	private LoadButtonEvent loadEvt;
	private JTextField offsetField;
	private JTextArea textArea;
	
	public FixButtonEvent(LoadButtonEvent loadEvt, JTextField offsetField, JTextArea textArea){
		this.fileContent = new ArrayList<String>();
		this.loadEvt = loadEvt;
		this.offsetField = offsetField;
		this.textArea = textArea;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//Regexp pattern.
		//Pattern regexp = Pattern.compile(pattern);
		//Matcher m;          
        //this.fileContent = this.loadEvt.getFileContent();    
		this.fileContent = new ArrayList<String>(Arrays.asList(this.textArea.getText().split("\n")));
        ArrayList<String> finalFileContent = new ArrayList<String>();
        String finalSubtitle;
        String subtitleTime1;
        String subtitleTime2;
        for(String line : this.fileContent){
        	if (line.matches(pattern)){
        		subtitleTime1 = addOffset(line.split("-->")[0]);
        		subtitleTime2 = addOffset(line.split("-->")[1]);
                finalSubtitle = subtitleTime1 + " --> " + subtitleTime2;    
                
        	}else{
        		finalSubtitle = line;        		
        	}
        	finalFileContent.add(finalSubtitle);
        }
        try {
			writeFixSubtitleFile(finalFileContent);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
	}	
	
	public String addOffset(String time){		
		String lineWithOffset = null;
		Date subtitleTime = null;
		Date milisecondsDate = null;
		long offset = Long.parseLong(this.offsetField.getText());
		long dateInMilesecons = 100l;
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss,SSS");  
		try {
			subtitleTime = format.parse(time);			
			dateInMilesecons = subtitleTime.getTime() + offset;			
			milisecondsDate = new Date(dateInMilesecons);			
			lineWithOffset = format.format(milisecondsDate);			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lineWithOffset;
	}

	public void writeFixSubtitleFile(ArrayList<String> finalSubtitle)
			throws IOException {
		// PrintWriter writer = new PrintWriter(this.loadEvt.getFileName().
		// split(".srt")[0] + "_fixed.srt");
		int caretPosition = this.textArea.getCaretPosition();
		this.textArea.setText("");

		for (String finalLine : finalSubtitle ){        	
        	byte ptext[] = finalLine.getBytes(ISO_8859_1); 
        	String value = new String(ptext, UTF_8); 
        	this.textArea.append(value + "\n");
			//writer.println(finalLine); 			
		}       
        this.textArea.setCaretPosition(caretPosition);
		//writer.close();	
		//JOptionPane.showMessageDialog(null, "File Fixed!");
	}

}
