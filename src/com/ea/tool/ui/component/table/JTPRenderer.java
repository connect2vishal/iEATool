package com.ea.tool.ui.component.table;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.table.TableCellRenderer;

//if the renderer on a column (or the whole table) is not a JTextComponent calculating its preferredSize will not do 
//any wrapping ... but it won't do any harm....
public class JTPRenderer extends JTextPane implements TableCellRenderer {
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		setText(value.toString());
		return this;
	}
}
