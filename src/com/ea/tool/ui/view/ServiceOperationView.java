package com.ea.tool.ui.view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.FlowLayout;

public class ServiceOperationView extends JPanel {

	/**
	 * Create the panel.
	 */
	public ServiceOperationView() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel topPanel = new JPanel();
		topPanel.setBorder(new TitledBorder(null, "Analysis & Filter", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel reportingPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) reportingPanel.getLayout();
		topPanel.add(reportingPanel, BorderLayout.WEST);
		
		JPanel panel_2 = new JPanel();
		topPanel.add(panel_2, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		topPanel.add(panel_1, BorderLayout.EAST);
		
		JPanel middlePanel = new JPanel();
		middlePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		add(middlePanel, BorderLayout.CENTER);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		add(bottomPanel, BorderLayout.SOUTH);

	}

}
