import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class FixButtonEvent implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		
        LoadButtonEvent evt = new LoadButtonEvent();
        byte[] subtitleFile = evt.getByteArrayFromFile();
        System.out.println(subtitleFile);
        
		
	}

}
