package com.ea.tool.ui.component.table;

import java.awt.Component;
import java.awt.Rectangle;

import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class WrapColListener implements TableColumnModelListener, TableModelListener {

	JTable m_table;

	public WrapColListener(JTable table) {
		m_table = table;
	}

	void refresh_row_heights() {
		int n_rows = m_table.getRowCount();
		int n_cols = m_table.getColumnCount();
		int intercell_width = m_table.getIntercellSpacing().width;
		int intercell_height = m_table.getIntercellSpacing().height;
		TableColumnModel col_model = m_table.getColumnModel();
		// these null checks are due to concurrency considerations... much can
		// change between the col margin change
		// event and the call to refresh_row_heights (although not in this
		// SSCCE...)
		if (col_model == null)
			return;
		// go through ALL rows, calculating row heights
		for (int row = 0; row < n_rows; row++) {
			int pref_row_height = 1;
			// calculate row heights from cell, setting width constraint by
			// means of setBounds...
			for (int col = 0; col < n_cols; col++) {
				Object value = m_table.getValueAt(row, col);
				TableCellRenderer renderer = m_table.getCellRenderer(row, col);
				if (renderer == null)
					return;
				Component comp = renderer.getTableCellRendererComponent(m_table, value, false, false, row, col);
				if (comp == null)
					return;
				int col_width = col_model.getColumn(col).getWidth();
				// constrain width of component
				comp.setBounds(new Rectangle(0, 0, col_width - intercell_width, Integer.MAX_VALUE));
				// getPreferredSize then returns "true" height as a function of
				// attributes (e.g. font) and word-wrapping
				int pref_cell_height = comp.getPreferredSize().height + intercell_height;
				if (pref_cell_height > pref_row_height) {
					pref_row_height = pref_cell_height;
				}
			}
			if (pref_row_height != m_table.getRowHeight(row)) {
				m_table.setRowHeight(row, pref_row_height);
			}
		}
	}

	@Override
	public void columnAdded(TableColumnModelEvent e) {
		refresh_row_heights();

	}

	@Override
	public void columnRemoved(TableColumnModelEvent e) {
		// probably no need to call refresh_row_heights

	}

	@Override
	public void columnMoved(TableColumnModelEvent e) {
		// probably no need to call refresh_row_heights

	}

	@Override
	public void columnMarginChanged(ChangeEvent e) {
		refresh_row_heights();
	}

	@Override
	public void columnSelectionChanged(ListSelectionEvent e) {
		// probably no need to call refresh_row_heights

	}

	@Override
	public void tableChanged(TableModelEvent e) {
		refresh_row_heights();
		
	}

}