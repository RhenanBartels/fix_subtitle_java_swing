import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.sun.javafx.application.PlatformImpl.FinishListener;

import java.util.ArrayList;
import java.util.Arrays;

public class TextAreaMouseEvent implements MouseListener {
	private JTextArea textArea;
	private String PATTERN = "\\d{2}:\\d{2}:\\d{2},\\d{3} --> \\d{2}:\\d{2}:\\d{2},\\d{3}";
	String text;
	JTextField offsetField;
	String selectedText;
	ArrayList<String> selectedArrayText;
	FixButtonEvent buttonEvent;
	int offset;

	public TextAreaMouseEvent(JTextArea textArea, JTextField offsetField,
    		FixButtonEvent buttonEvent){
    	this.textArea = textArea;
    	this.offsetField = offsetField;
    	this.selectedText = "";
    	this.buttonEvent = buttonEvent;
    	this.offset = 0;
    	this.selectedArrayText = new ArrayList<String>();
    }	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		this.text = this.textArea.getText();
		String textOffset = this.offsetField.getText();	
		if (!textOffset.equals("")){
		    this.offset = Integer.parseInt(textOffset);
			this.selectedText = this.textArea.getSelectedText();			
			if (this.selectedText!=null) {
				parseSelectedText(this.selectedText);
			}
		} 	
	}

	private String addOffset(String line) {
        String outputLine = "";
        outputLine = this.buttonEvent.addOffset(line);
        return outputLine;
	}

	public void parseSelectedText(String areaSelectedText) {
		this.selectedArrayText = new ArrayList<String>(
				Arrays.asList(areaSelectedText.split("\n")));

		ArrayList<String> finalFileContent = new ArrayList<String>();
		String finalSubtitle = "";
		String subtitleTime1 = "";
		String subtitleTime2 = "";
		for (String line : this.selectedArrayText) {
			if (line.matches(PATTERN)) {				
				subtitleTime1 = addOffset(line.split("-->")[0]);				
				subtitleTime2 = addOffset(line.split("-->")[1]);
				finalSubtitle = subtitleTime1 + " --> " + subtitleTime2;				
			} else {
				finalSubtitle = line;
			}
			finalFileContent.add(finalSubtitle);
		}
		String newSubtitleToReplace = "";
		for (String line : finalFileContent){
			newSubtitleToReplace += line + "\n";		
		}
		 int caretPosition = this.textArea.getCaretPosition();
		newSubtitleToReplace = newSubtitleToReplace.substring(0, newSubtitleToReplace.length()-1);
		System.out.println(this.selectedText);
		//System.out.println(this.text);
		this.text = this.text.replace(this.selectedText, newSubtitleToReplace);
		this.textArea.setText("");
		this.textArea.setText(this.text);       
        this.textArea.setCaretPosition(caretPosition);
	}
}
