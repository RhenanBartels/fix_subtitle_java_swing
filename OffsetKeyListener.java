import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;
import javax.swing.JButton;

public class OffsetKeyListener implements KeyListener {
	private JTextField fileName;
	private JTextField offset;
	private JButton fix;
	
	public OffsetKeyListener(JTextField fileName, JTextField offset, JButton fix){
		this.fileName = fileName;
		this.offset = offset;
		this.fix = fix;		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {			
		if (!this.offset.getText().equals("") && !this.fileName.getText().equals("")){
			this.fix.setEnabled(true);
		}
		else{
			this.fix.setEnabled(false);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
