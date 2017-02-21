package com.ea.tool.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import com.ea.tool.ui.toolbar.ToolBarContainer;

public class MainContainer extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4537692007798712830L;
	//private JPanel contentPane;
	private JDesktopPane desktopContainerPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");// 
					 
				
					 //windows ---
					 //"com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
							 
					 // nimbus like mactintos look and feel---
					 //"com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
					            
					 //UIManager.getCrossPlatformLookAndFeelClassName());
					 
					MainContainer frame = new MainContainer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainContainer() {
		
		//Make the big window be indented 50 pixels from each edge
        //of the screen.
        int inset = 50;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset,
                  screenSize.width  - inset*2,
                  screenSize.height - inset*2);
        
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 1145, 664);
		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel menuContainer = new JPanel();
		//menuContainer.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(menuContainer, BorderLayout.NORTH);
		menuContainer.setPreferredSize(new Dimension((int) contentPane.getSize().getWidth(), 140));
		menuContainer.setLayout(new BorderLayout(0, 0));
		
		ToolBarContainer toolMenu = new ToolBarContainer(this, this.getWidth(),160);
		toolMenu.setBorder(new LineBorder(Color.GREEN));
		menuContainer.add(toolMenu, BorderLayout.CENTER);
		
		desktopContainerPanel = new JDesktopPane();
		desktopContainerPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		desktopContainerPanel.setBackground(UIManager.getColor("InternalFrame.activeTitleBackground"));
		desktopContainerPanel.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
		contentPane.add(desktopContainerPanel);
		
		//createFrame(desktopContainerPanel, new MyInternalFrame()); //Create first window
		
		JPanel statusPanel = new JPanel();
		statusPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		statusPanel.setPreferredSize(new Dimension(1145, 30));
		contentPane.add(statusPanel, BorderLayout.SOUTH);
	}
	
	public JDesktopPane getDesktopContainerPanel() {
		return desktopContainerPanel;
	}

	public void setDesktopContainerPanel(JDesktopPane desktopContainerPanel) {
		this.desktopContainerPanel = desktopContainerPanel;
	}

	private void createFrame(JDesktopPane desktopContainerPanel2, JInternalFrame internalFrame) {
	    
		MyInternalFrame frame;
		
		if( internalFrame == null){
			frame = new MyInternalFrame();
		}else {
			frame = (MyInternalFrame) internalFrame;
		}
	    frame.setVisible(true);
	    desktopContainerPanel2.add(frame);
	    try {
	        frame.setSelected(true);
	    } catch (java.beans.PropertyVetoException e) {}
	}
	
/*	public JPanel getContentPane() {
		return contentPane;
	}

	public void setContentPane(JPanel contentPane) {
		this.contentPane = contentPane;
	}
*/
	static int openFrameCount = 0;
	static final int xOffset = 30, yOffset = 30;

	class MyInternalFrame extends JInternalFrame{
		public MyInternalFrame() {
			super("Document #" + (++openFrameCount), true, // resizable
					true, // closable
					true, // maximizable
					true);// iconifiable
			// ...Create the GUI and put it in the window...
			// ...Then set the window size or call pack...
			// ...
			// Set the window's location.
			setLocation(xOffset * openFrameCount, yOffset * openFrameCount);
		}
	}

	public void openWSDLView(MouseEvent e) {
		// open window in container
		
		InternalWindow frame = new InternalWindow("Multi WSDL Viewer",new Uploader());
        frame.setVisible(true); //necessary as of 1.3
        getDesktopContainerPanel().add(frame);
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {}
		
	}
}
