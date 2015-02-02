import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.awt.Color;
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
		load.setSize(14, 18);
		load.setLocation(295, 13);	
		load.setOpaque(false);
		//load.setContentAreaFilled(false);
		load.setBorderPainted(false);
		gui.add(load);
		
		//File Name JLabel.
		nameLabel = new JLabel("File Name:");
		nameLabel.setLayout(null);
		nameLabel.setSize(80, 10);
		nameLabel.setLocation(8, 15);
		nameLabel.setForeground(Color.WHITE);
		gui.add(nameLabel);
		
		//Offset JLabel.
		offsetLabel = new JLabel("Offset:");
		offsetLabel.setLayout(null);
		offsetLabel.setSize(60, 10);
		offsetLabel.setLocation(8, 52);
		offsetLabel.setForeground(Color.WHITE);
		gui.add(offsetLabel);
		
		//File Name JTextField.
		fileName = new JTextField();
		fileName.setLayout(null);
		fileName.setSize(200, 20);
		fileName.setLocation(90, 12);
		fileName.setEditable(false);		
		gui.add(fileName);	

		//Create JTextArea.
		textArea = new JTextArea();
		textArea.setVisible(false);
		//textArea.setLayout(null);
		//textArea.setSize(290, 208);
		//textArea.setLocation(30, 115);		
	    //gui.add(textArea);
		TextAreaMouseEvent mouseEvt = new TextAreaMouseEvent(textArea);
		textArea.addMouseListener(mouseEvt);
		
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
		fix.setEnabled(false);  		
        gui.add(fix);
        
		//File Name JTextField.
		offset = new JTextField();
		offset.setLayout(null);
		offset.setSize(50, 20);
		offset.setLocation(90, 50);
		OffsetKeyListener keyEvt = new OffsetKeyListener(fileName, offset, fix);
		offset.addKeyListener(keyEvt);
		gui.add(offset);	
		
		fix.addActionListener(new FixButtonEvent(evt, offset, textArea));
		
		gui.setLayout(null);
		gui.setSize(350, 135);
		gui.setTitle("Fix Subtitle");
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setLocationRelativeTo(null);
		gui.getContentPane().setBackground( Color.BLACK );
		gui.setResizable(false);
		//gui.pack();
		gui.setVisible(true);
		
		
	}

}
