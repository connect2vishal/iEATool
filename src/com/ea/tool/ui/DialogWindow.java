package com.ea.tool.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.ea.tool.ui.common.SourceManager;
import com.ea.tool.ui.constants.Screen;

public class DialogWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * @wbp.parser.constructor
	 */
	private DialogWindow(String title, String description){
		setTitle(title);
		
	}
	/**
	 * Create the frame.
	 */
	private DialogWindow(String title, String description,  int width, int height) {

		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 830, 700);
//		contentPane = new JPanel();
//		contentPane.setBorder(null);
//		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane = loadSourceManager(title, description, width, height);
		setContentPane(contentPane);
		
		//panel.setBounds(this.getX() - 1, this.getY() -1 , contentPane.getWidth()-400, contentPane.getHeight()-300);
//		contentPane.add(loadWindowPanel(), BorderLayout.CENTER);
		
	}

	private SourceManager loadSourceManager(String title, String description, int width, int height){
		//JOptionPane.showMessageDialog(null, "width : "+this.getWidth()+" h: "+this.getHeight());
		SourceManager panel = new SourceManager(title, description, width, height);
		panel.setBorder(null);
		this.setTitle(panel.getLblTitle().getText());
		return panel;
	}
	
	public static DialogWindow loadWindow(String title, String description, int width, int height) {
		DialogWindow dw = new DialogWindow(title, description, width, height);
		return dw;
	}
}
