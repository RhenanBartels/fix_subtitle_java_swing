import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JFrame;
import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.*;


public class LoadButtonEvent implements ActionListener {
	private static JTextField fileName;
	private static JFrame frame;
	private static String subtitlePath;

	public LoadButtonEvent(){
		
	}
	public LoadButtonEvent(JTextField fileName, JFrame frame){
		this.fileName = fileName;
		this.frame = frame;
		

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
				this.subtitlePath = selectedFile.getAbsolutePath();
				this.fileName.setText(fileChoosedName);

			}
		}
	}
	
public byte[] getByteArrayFromFile() {
		String filePath = this.subtitlePath;
		byte[] result = null;
		FileInputStream fileInStr = null;
		try {
			File imgFile = new File(filePath);
			fileInStr = new FileInputStream(imgFile);
			long imageSize = imgFile.length();

			if (imageSize > Integer.MAX_VALUE) {
				return null;    //image is too large
			}

			if (imageSize > 0) {
				result = new byte[(int) imageSize];
				fileInStr.read(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fileInStr.close();
			} catch (Exception e) {
			}
		}
		return result;
	}

}
