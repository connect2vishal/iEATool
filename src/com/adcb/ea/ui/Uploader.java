package com.adcb.ea.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.adcb.ea.data.DataLoader;

public class Uploader extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private String[] columnNames = {"ProductID",
            "Name",
            "ProductNumber",
            "Color",
            "SafetyStockLevel",
            "ListPrice",
            "Size"};
	
	private Object[][] data = {
		    {new Integer(986), "Mountain-500 Silver","BK-M18S-44","Silver", new Double(308.2179),  new Double(564.99), new Integer(44)},
		    {new Integer(987), "Mountain-500 Silver","BK-M18S-48","Silver", new Double(308.2179),  new Double(564.99), new Integer(48)},
		    {new Integer(988), "Mountain-500 Silver","BK-M18S-52","Silver", new Double(308.2179),  new Double(564.99), new Integer(52)},
		    {new Integer(989), "Mountain-500 Black","BK-M18B-40","Black", new Double(294.5797),  new Double(539.99), new Integer(40)},
		    {new Integer(990), "Mountain-500 Black","BK-M18B-42","Black", new Double(294.5797),  new Double(539.99), new Integer(42)}
		};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Uploader frame = new Uploader();
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
	public Uploader() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 1025, 704);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel tablePanel = new JPanel();
		tablePanel.setBackground(Color.CYAN);
		tablePanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tablePanel.setBounds(5, 40, 1007, 622);
		contentPane.add(tablePanel);
		tablePanel.setLayout(null);
		
		tableModel = new DefaultTableModel(data,columnNames);
		table = new JTable(tableModel);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setBounds(12, 7, 983, 602);
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setBackground(Color.PINK);
		table.setAutoCreateRowSorter(true);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setSize(1006, 621);
		table.setFillsViewportHeight(true);
		tablePanel.add(scrollPane);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(5, 5, 1007, 35);
		contentPane.add(buttonPanel);
		
		JButton btnUploadFileData = new JButton("Upload File Data");
		buttonPanel.add(btnUploadFileData);
		
		JButton btnTest = new JButton("Test");
		btnTest.addMouseListener(new MouseAdapter() {
			private ResultSet rs;

			@Override
			public void mouseClicked(MouseEvent e) {
				
				setTableModel(getData());
				tableModel.fireTableDataChanged();
			       
			}
		});
		buttonPanel.add(btnTest);
	}
	
	
	
	protected void setTableModel(ResultSet rs) {
		//tableModel = new DefaultTableModel();
		//tableModel.setRowCount(0);
		Vector product = new Vector();
	    
		//Prepare Columns

		
		// prepare rows
		try {
			while(rs.next())	{  
				product = new Vector();
				//product = new Product();
				try{
					product.addElement(rs.getInt(1));
				}catch(Exception e){
					product.addElement(00);
					System.out.println("ProductID was NULL");
				}
				try{
					product.addElement(rs.getString(2));
				}catch(Exception e){
					product.addElement("--");
					System.out.println("ProductID was NULL");
				}
				try{
					product.addElement(rs.getString(3));
				}catch(Exception e){
					product.addElement("--");
					System.out.println("ProductNumber was NULL");
				}
				try{
					product.addElement(rs.getString(4));
				}catch(Exception e){
					product.addElement("--");
					System.out.println("Color was NULL");
				}
				try{
					product.addElement(rs.getDouble(5));
				}catch(Exception e){
					product.addElement(00.0000);
					System.out.println("SafetyStockLevel was NULL");
				}
				try{
					product.addElement(rs.getDouble(6));
				}catch(Exception e){
					product.addElement(00.0000);
					System.out.println("ListPrice was NULL");
				}
				try{
					product.addElement(rs.getString(7));
				}catch(Exception e){
					product.addElement("--");
					System.out.println("Size was NULL");
				}

				tableModel.addRow(product);
			}
			
			table.setModel(tableModel);

			System.out.println("Refresed Jtable...");
		} catch (SQLException e) {
			System.out.println("Cant update Jtable...");
			e.printStackTrace();
		}
	   
		
	}

	protected ResultSet getData() {

		return DataLoader.GetConnection();
	}


}
