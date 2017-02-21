package com.ea.tool.main;

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class MultiWrapColDemo {
  public static void main(String[] args) throws FileNotFoundException {
    EventQueue.invokeLater(new ShowIt());
  }
}

class ShowIt implements Runnable {
  @Override
  public void run() {
    JTable table = new JTable();
    table.getColumnModel().addColumnModelListener( new WrapColListener( table ) );
    table.setDefaultRenderer( Object.class, new JTPRenderer() );

    // examples:
//    table.setIntercellSpacing( new Dimension( 40, 20 ));
//    table.setIntercellSpacing( new Dimension( 4, 2 ));

    Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
    String lorem1 = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore";
    String lorem2 = "et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum";

    for (int i = 0; i < 12; i++) {
      Vector<String> row = null;
      if (i % 4 == 0) {
        row = new Vector<String>(Arrays.asList(new String[] { "iggle", lorem1, "poggle", "poke" }));
      } else if (i % 4 == 1) {
        row = new Vector<String>(Arrays.asList(new String[] { lorem2, "piggle", "poggle", lorem1 }));
      } else if (i % 4 == 2) {
        row = new Vector<String>(Arrays.asList(new String[] { lorem1, "piggle", lorem2, "poke" }));
      } else
        row = new Vector<String>(Arrays.asList(new String[] { "iggle", lorem2, "poggle", lorem2 }));
      dataVector.add(row);
    }
    Vector<String> columnIdentifiers = new Vector<String>(Arrays.asList(new String[] { "iggle", "piggle", "poggle",
        "poke" }));
    table.getTableHeader().setFont(table.getTableHeader().getFont().deriveFont(20f).deriveFont(Font.BOLD));
    ((DefaultTableModel) table.getModel()).setDataVector(dataVector, columnIdentifiers);
    JFrame frame = new JFrame("MultiWrapColTable");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JScrollPane jsp = new JScrollPane(table);
    frame.getContentPane().add(jsp);
    frame.pack();
    frame.setBounds(50, 50, 800, 500);
    frame.setVisible(true);
  }
}


// if the renderer on a column (or the whole table) is not a JTextComponent calculating its preferredSize will not do 
// any wrapping ... but it won't do any harm....
class JTPRenderer extends JTextPane implements TableCellRenderer {
  @Override
  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
      int row, int column) {
    setText(value.toString());
    return this;
  }
}

class WrapColListener implements TableColumnModelListener {

  JTable m_table;

  WrapColListener( JTable table ){
    m_table = table;
  }

  void refresh_row_heights() {
    int n_rows = m_table.getRowCount();
    int n_cols = m_table.getColumnCount();
    int intercell_width = m_table.getIntercellSpacing().width;
    int intercell_height = m_table.getIntercellSpacing().height;
    TableColumnModel col_model = m_table.getColumnModel();
    // these null checks are due to concurrency considerations... much can change between the col margin change
    // event and the call to refresh_row_heights (although not in this SSCCE...)
    if( col_model == null ) return;
    // go through ALL rows, calculating row heights
    for (int row = 0; row < n_rows; row++) {
      int pref_row_height = 1;
      // calculate row heights from cell, setting width constraint by means of setBounds...
      for (int col = 0; col < n_cols; col++) {
        Object value = m_table.getValueAt(row, col);
        TableCellRenderer renderer = m_table.getCellRenderer(row, col);
        if( renderer == null ) return;
        Component comp = renderer.getTableCellRendererComponent( m_table, value, false, false,
            row, col);
        if( comp == null ) return;
        int col_width = col_model.getColumn(col).getWidth();
        // constrain width of component
        comp.setBounds(new Rectangle(0, 0, col_width - intercell_width, Integer.MAX_VALUE ));
        // getPreferredSize then returns "true" height as a function of attributes (e.g. font) and word-wrapping
        int pref_cell_height = comp.getPreferredSize().height  + intercell_height;
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

}