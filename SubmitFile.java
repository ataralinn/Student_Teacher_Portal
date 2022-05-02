
import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

public class SubmitFile extends JFrame implements ActionListener{
	
	JButton button;
	File file;
	
	public SubmitFile() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		
		button = new JButton("Select File");
		button.addActionListener(this);
		
		this.add(button);
		this.pack();
		this.setVisible(true);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button) {
			JFileChooser fileChooser = new JFileChooser();
			
			int response = fileChooser.showOpenDialog(null);
			System.out.println(response);
			
			if(response == JFileChooser.APPROVE_OPTION) {
				file = new File(fileChooser.getCurrentDirectory().getAbsolutePath());
			}
		}
		
	}
	
	public File getFile() {
		while(file == null);
		return file;
	}

}
