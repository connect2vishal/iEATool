package com.ea.tool.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class InternalWindow extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5378481346739912314L;
	static int openFrameCount = 0;
    static final int xOffset = 30, yOffset = 30;
 
    public InternalWindow(String title, JPanel window) {
        super(title + (++openFrameCount), 
              true, //resizable
              true, //closable
              true, //maximizable
              true);
        
        //...Create the GUI and put it in the window...
 
        //...Then set the window size or call pack...
        // get the window/ panel size whic is about to open
        //setSize(742,534);
        setSize(window.getPreferredSize());
        
        //Set the window's location.
        setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
        
        //getContentPane().add(window);
        getContentPane().setLayout(new BorderLayout(0, 0));
        
        JPanel internalWindowContainer = new JPanel();
        getContentPane().add(internalWindowContainer, BorderLayout.NORTH);
        internalWindowContainer.setLayout(new BorderLayout(0, 0));
        internalWindowContainer.add(window);
    }

    
}
