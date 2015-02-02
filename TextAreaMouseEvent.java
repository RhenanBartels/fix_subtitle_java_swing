import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTextArea;

public class TextAreaMouseEvent implements MouseListener {
    private JTextArea textArea;
    String text;
	
    public TextAreaMouseEvent(JTextArea textArea){
    	this.textArea = textArea;
    }	
	@Override
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
		this.text = this.textArea.getSelectedText();
		System.out.println(this.text);
		
	}

}
