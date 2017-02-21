package com.ea.tool.ui.common;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import org.apache.commons.io.FileUtils;

import com.ea.tool.core.FileProcessingUtility;

import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.JList;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SourceManager extends JPanel {
	
	private JTextField textField;
	private JLabel lblTitle;
	private JLabel lblDescription;

	/**
	 * Create the panel.
	 * @param jframeHeight 
	 */
	public SourceManager(String title, String description, int jframeWidth, int jframeHeight) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(Color.WHITE);
		headerPanel.setMaximumSize( new Dimension(1000, 120));
		add(headerPanel);
		headerPanel.setLayout(null);
		
		lblTitle = new JLabel("Source Manager");
		lblTitle.setFont(new Font("Calibri", Font.BOLD, 18));
		lblTitle.setBounds(10, 5, 425, 20);
		lblTitle.setVerticalAlignment(SwingConstants.TOP);
		headerPanel.add(lblTitle);
		
		lblDescription = new JLabel("Source Manager is a screent to set the source of the WSDL. ITcan be a file path or datasource");
		lblDescription.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblDescription.setBounds(30, 30, 425, 60);
		lblDescription.setVerticalAlignment(SwingConstants.TOP);
		headerPanel.add(lblDescription);

		JPanel separatorPanel = new JPanel();
		separatorPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		separatorPanel.setMaximumSize( new Dimension(830, 0));
		add(separatorPanel);

		JPanel bodyPanel = new JPanel();
		//bodyPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		bodyPanel.setMaximumSize( new Dimension(830, 700));
		add(bodyPanel);
		bodyPanel.setLayout(null);
		
		JLabel lblSetFolderPath = new JLabel("WSDL Folder Path :");
		lblSetFolderPath.setBounds(12, 13, 121, 16);
		bodyPanel.add(lblSetFolderPath);
		
		textField = new JTextField();
		textField.setBounds(130, 10, 573, 22);
		bodyPanel.add(textField);
		textField.setColumns(10);
		
		JButton btnSelect = new JButton("Select");
		btnSelect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Create a file chooser
				final JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = fc.showOpenDialog(SourceManager.this);

		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = fc.getSelectedFile();
		            //This is where a real application would open the file.
		            System.out.println("Opening: " + file.getName() + ".");
		            String filetypes[] = {"wsdl","*.*"};
		            FileUtils.listFiles(file, filetypes, true);//(file.getName(), files);
		            
		        } else {
		        	System.out.println("Open command cancelled by user.");
		        }
				
			}
		});
		btnSelect.setBounds(715, 9, 97, 25);
		bodyPanel.add(btnSelect);
		
		JLabel lblWsdlsToParse = new JLabel("WSDLs to parse : ");
		lblWsdlsToParse.setBounds(12, 49, 121, 16);
		bodyPanel.add(lblWsdlsToParse);
		
		JList listToParse = new JList();
		listToParse.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		listToParse.setBounds(12, 78, 800, 382);
		bodyPanel.add(listToParse);

		JPanel separatorPanel1 = new JPanel();
		separatorPanel1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		separatorPanel1.setMaximumSize( new Dimension(jframeWidth, 1));
		add(separatorPanel1);

		JPanel footerPanel = new JPanel();
		footerPanel.setSize( new Dimension(830, 70));
		footerPanel.setPreferredSize( new Dimension(830, 70));
		footerPanel.setMaximumSize( new Dimension(830, 70));
		add(footerPanel);
		footerPanel.setLayout(null);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(699, 13, 97, 25);
		footerPanel.add(btnCancel);
		
		JButton btnSave = new JButton("Analyze");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSave.setBounds(590, 13, 97, 25);
		footerPanel.add(btnSave);
		
	}

	public JLabel getLblTitle() {
		return lblTitle;
	}

	public void setLblTitle(JLabel lblTitle) {
		this.lblTitle = lblTitle;
	}

	public JLabel getLblDescription() {
		return lblDescription;
	}

	public void setLblDescription(JLabel lblDescription) {
		this.lblDescription = lblDescription;
	}
}
