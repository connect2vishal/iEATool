package com.ea.tool.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.ea.tool.data.DataLoader;
import com.ea.tool.ui.component.table.MultiLineCellRenderer;
import com.ea.tool.ui.util.GridViewUtil;

import java.awt.BorderLayout;
import javax.swing.border.LineBorder;

public class Uploader extends JPanel {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private String[] columnNames = { "ProductID", "Name", "ProductNumber", "Color", "SafetyStockLevel", "ListPrice",
			"Size" };

	String columns[] = { "Service Group", "Service Engine", "WSDL File", "Service Name", "port", "Binding Operation",
			"Operation Input", "Operation Output" };

	private Object[][] data = {
			{ new Integer(986), "Mountain-500 Silver", "BK-M18S-44", "Silver", new Double(308.2179), new Double(564.99),
					new Integer(44), "testing", "testing" },
			{ new Integer(987), "Mountain-500 Silver", "BK-M18S-48", "Silver", new Double(308.2179), new Double(564.99),
					new Integer(48), "testing", "testing" },
			{ new Integer(988), "Mountain-500 Silver", "BK-M18S-52", "Silver", new Double(308.2179), new Double(564.99),
					new Integer(52), "testing", "testing" },
			{ new Integer(989), "Mountain-500 Black", "BK-M18B-40", "Black", new Double(294.5797), new Double(539.99),
					new Integer(40), "testing", "testing" },
			{ new Integer(990), "Mountain-500 Black", "BK-M18B-42", "Black", new Double(294.5797), new Double(539.99),
					new Integer(42), "testing", "testing" } };

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
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 1025, 704);
		setPreferredSize(new Dimension(1024, 704));
		setLayout(new BorderLayout(0, 0));
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(Color.CYAN));
		contentPane.setPreferredSize(this.getPreferredSize());
		//setContentPane(contentPane);
		this.add(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel tablePanel = new JPanel();
		tablePanel.setBackground(Color.CYAN);
		tablePanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(tablePanel);

		tableModel = new DefaultTableModel(columns, 0) {
			@Override
			public boolean isCellEditable(int i, int i1) {
				return false; // To change body of generated methods, choose
								// Tools | Templates.
			}

			public Class getColumnClass(int columnIndex) {
				return String.class;
			}
		};// data,columns);
		table = new JTable(tableModel);
		// @Override
		// public Component prepareRenderer(TableCellRenderer renderer, int row,
		// int column) {
		// Component component = super.prepareRenderer(renderer, row, column);
		// int rendererWidth = component.getPreferredSize().width;
		// TableColumn tableColumn = getColumnModel().getColumn(column);
		// tableColumn.setPreferredWidth(
		// Math.max(rendererWidth + getIntercellSpacing().width,
		// tableColumn.getPreferredWidth()));
		// return component;
		// }

		int lines = 2;
		table.setRowHeight(table.getRowHeight() * lines);
		table.setDefaultRenderer(String.class, new MultiLineCellRenderer());
		// Object.class, new MultiLineCellRenderer());

		table.setFont(new Font("Calibri", Font.PLAIN, 13));
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setBounds(12, 7, 983, 602);
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setBackground(Color.PINK);
		table.setAutoCreateRowSorter(true);

		// set column model

		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		table.getColumnModel().getColumn(4).setPreferredWidth(200);
		table.getColumnModel().getColumn(5).setPreferredWidth(200);
		table.getColumnModel().getColumn(6).setPreferredWidth(200);
		table.getColumnModel().getColumn(7).setPreferredWidth(200);
		tablePanel.setLayout(new BorderLayout(0, 0));

		// muline column renderer
		// table.getColumnModel().addColumnModelListener( new WrapColListener(
		// table ) );
		// table.getModel().addTableModelListener(new ModelListener( table ));

		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		tablePanel.add(scrollPane, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();
		contentPane.add(buttonPanel, BorderLayout.NORTH);

		JButton btnUploadFileData = new JButton("Upload File Data");
		buttonPanel.add(btnUploadFileData);

		JButton btnTest = new JButton("Test");
		btnTest.addMouseListener(new MouseAdapter() {
			private ResultSet rs;

			@Override
			public void mouseClicked(MouseEvent e) {

				// tableModel = new DefaultTableModel(columnNames);

				table.setModel(GridViewUtil.getTabelModel(tableModel));
				// setTableModel(getData());
				tableModel.fireTableDataChanged();

			}
		});
		buttonPanel.add(btnTest);
	}

	protected void setTableModel(ResultSet rs) {
		// tableModel = new DefaultTableModel();
		// tableModel.setRowCount(0);
		Vector product = new Vector();

		// Prepare Columns

		// prepare rows
		try {
			while (rs.next()) {
				product = new Vector();
				// product = new Product();
				try {
					product.addElement(rs.getInt(1));
				} catch (Exception e) {
					product.addElement(00);
					System.out.println("ProductID was NULL");
				}
				try {
					product.addElement(rs.getString(2));
				} catch (Exception e) {
					product.addElement("--");
					System.out.println("ProductID was NULL");
				}
				try {
					product.addElement(rs.getString(3));
				} catch (Exception e) {
					product.addElement("--");
					System.out.println("ProductNumber was NULL");
				}
				try {
					product.addElement(rs.getString(4));
				} catch (Exception e) {
					product.addElement("--");
					System.out.println("Color was NULL");
				}
				try {
					product.addElement(rs.getDouble(5));
				} catch (Exception e) {
					product.addElement(00.0000);
					System.out.println("SafetyStockLevel was NULL");
				}
				try {
					product.addElement(rs.getDouble(6));
				} catch (Exception e) {
					product.addElement(00.0000);
					System.out.println("ListPrice was NULL");
				}
				try {
					product.addElement(rs.getString(7));
				} catch (Exception e) {
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
