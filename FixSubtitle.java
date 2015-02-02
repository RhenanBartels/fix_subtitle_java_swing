import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.awt.Insets;
import java.io.File;

public class FixSubtitle extends JFrame {
	private static JFrame gui;
	private static JButton load;
	private static JButton fix;
	private static JTextField fileName;
	private static JTextField offset;
	public  static JLabel nameLabel;
	private static JLabel offsetLabel;
	private static JScrollPane scrollPane;
	private static JTextArea textArea;
	
	public static void main(String[] args){
		gui = new JFrame();
		
		//Load button.
		load = new JButton();
		//Get icon image path
		String absolutePath = new File("src").getAbsolutePath();
		System.out.println(absolutePath + File.separator + "text70.png");
		load.setIcon(new ImageIcon(absolutePath + File.separator + "text70.png"));
		load.setLayout(null);
		load.setMargin(new Insets(0, 0, 0, 0));
		load.setBorder(null);		
		load.setSize(20, 20);
		load.setLocation(295, 12);		
		gui.add(load);
		
		//File Name JLabel.
		nameLabel = new JLabel("File Name:");
		nameLabel.setLayout(null);
		nameLabel.setSize(80, 10);
		nameLabel.setLocation(8, 15);
		gui.add(nameLabel);
		
		//Offset JLabel.
		offsetLabel = new JLabel("Offset:");
		offsetLabel.setLayout(null);
		offsetLabel.setSize(60, 10);
		offsetLabel.setLocation(8, 52);
		gui.add(offsetLabel);
		
		//File Name JTextField.
		fileName = new JTextField();
		fileName.setLayout(null);
		fileName.setSize(200, 20);
		fileName.setLocation(90, 12);
		gui.add(fileName);
	
		//File Name JTextField.
		offset = new JTextField();
		offset.setLayout(null);
		offset.setSize(50, 20);
		offset.setLocation(90, 50);
		gui.add(offset);	
		
		//Create JTextArea.
		textArea = new JTextArea();
		textArea.setVisible(false);
		//textArea.setLayout(null);
		//textArea.setSize(290, 208);
		//textArea.setLocation(30, 115);		
	    //gui.add(textArea);
		
        //Subtitle JScrollPane.
        scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(30, 115, 290, 200);
        //scrollPane.setLayout(null);
        //scrollPane.setSize(290, 208);
        //scrollPane.setLocation(30, 115);
        scrollPane.setVisible(false);      
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        gui.add(scrollPane);
		//Add EventListener	to Load Button.
		LoadButtonEvent evt = new LoadButtonEvent(fileName, gui, scrollPane, textArea);
		load.addActionListener(evt);        
		
		//Fix button.
		fix = new JButton("Fix");
		fix.setLayout(null);		
		fix.setSize(60, 20);
		fix.setLocation(135, 85);
		//fix.setEnabled(false);
        fix.addActionListener(new FixButtonEvent(evt, offset));
        gui.add(fix);
        
		gui.setLayout(null);
		gui.setSize(350, 135);
		gui.setTitle("Fix Subtitle");
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setLocationRelativeTo(null);
		gui.setVisible(true);
		
		
	}

}
