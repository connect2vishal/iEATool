package com.ea.tool.ui.component.table;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.text.View;

public class ModelListener implements TableModelListener {

	JTable mainTable;
	JTable depTable;

	public ModelListener(JTable m, JTable d) {
		mainTable = m;
		depTable = d;
	}

	public ModelListener(JTable m) {
		mainTable = m;
		depTable = null;
	}

	public void tableChanged(TableModelEvent tme) {
		int fRow = tme.getFirstRow();
		int col = tme.getColumn();

		JTextArea cellArea = (JTextArea) mainTable.getDefaultRenderer(Object.class);

		int numOfLines = getWrappedLines(cellArea); // countLines();
		int height_normal = mainTable.getRowHeight(fRow);

		System.out.println("h normal:" + height_normal);
		System.out.println("numLines:" + numOfLines);
		System.out.println("value:" + mainTable.getModel().getValueAt(fRow, col));
		System.out.println("width:" + cellArea.getPreferredSize().width);

		if (depTable == null) {
			if (height_normal < numOfLines * 16) {
				mainTable.setRowHeight(fRow, numOfLines * 16);
			}
		} else {
			// (---)
		}
		mainTable.repaint();
	}

	private int getWrappedLines(JTextArea component) {
		View view = component.getUI().getRootView(component).getView(0);
		int preferredHeight = (int) view.getPreferredSpan(View.Y_AXIS);
		int lineHeight = component.getFontMetrics(component.getFont()).getHeight();
		return preferredHeight / lineHeight;
	}
}