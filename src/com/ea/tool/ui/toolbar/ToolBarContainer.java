package com.ea.tool.ui.toolbar;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.TitledBorder;

import com.ea.tool.ui.DialogWindow;
import com.ea.tool.ui.MainContainer;
import com.ea.tool.ui.constants.Screen;

import javax.swing.UIManager;
import javax.swing.JSeparator;
import java.awt.Font;

public class ToolBarContainer extends JPanel {

	/**
	 * Create the panel.
	 * @param mainContainer 
	 * @param height 
	 * @param width 
	 */
	public ToolBarContainer(MainContainer mainContainer, int width, int height) {
		setLayout(new BorderLayout(0, 0));
		//setPreferredSize(new Dimension(width, height));
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		add(tabbedPane);
		
		JPanel homePanel = new JPanel();
		tabbedPane.addTab("Home", null, homePanel, null);
		
		JPanel viewPanel = new JPanel();
		//viewPanel.set
		tabbedPane.addTab("Views", null, viewPanel, null);
		viewPanel.setLayout(null);
		
		JPanel wsdlPanel = new JPanel();
		wsdlPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "WSDLs", TitledBorder.CENTER, TitledBorder.ABOVE_BOTTOM, null, new Color(0, 0, 0)));
		wsdlPanel.setBounds(0, 0, 278, 110);
		viewPanel.add(wsdlPanel);
		wsdlPanel.setLayout(null);
		
		JButton btnWSDLView = new JButton("<html><div align=center>Multi WSDL </div> <div align=center>Viewer</div></html>");
		btnWSDLView.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("click event in ToolBar Class");
				mainContainer.openWSDLView(e);
			}
		});
		btnWSDLView.setBounds(12, 13, 120, 71);
		wsdlPanel.add(btnWSDLView);
		
		JButton btnServiceView = new JButton("<html><div align=center>Multi Service </div> <div align=center>Viewer</div></html>");
		btnServiceView.setBounds(142, 13, 120, 71);
		wsdlPanel.add(btnServiceView);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 88, 248, 2);
		wsdlPanel.add(separator);
		
		JPanel editPanel = new JPanel();
		tabbedPane.addTab("Edit", null, editPanel, null);
		
		JPanel reportPanel = new JPanel();
		tabbedPane.addTab("Report", null, reportPanel, null);
		
		JPanel Configuration = new JPanel();
		tabbedPane.addTab("Configuration", null, Configuration, null);
		tabbedPane.setEnabledAt(4, true);
		tabbedPane.setBackgroundAt(4, Color.LIGHT_GRAY);
		Configuration.setLayout(null);
		
		JButton btnSourceManager = new JButton("<html>Source <br/> Manager</html>");
		btnSourceManager.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//JOptionPane.showMessageDialog(null, "My Goodness, this is so concise");
				//System.out.println("I tried open the window");
				DialogWindow srcManager = DialogWindow.loadWindow(Screen.SoruceManager.getTitle(), Screen.SoruceManager.getDesc(), 830, 700 );//new SourceManager();
				srcManager.setVisible(true);
			}
		});
		btnSourceManager.setVerticalAlignment(SwingConstants.BOTTOM);
		btnSourceManager.setBounds(12, 13, 97, 84);
		Configuration.add(btnSourceManager);

		tabbedPane.setSelectedIndex(1);
	}
}
