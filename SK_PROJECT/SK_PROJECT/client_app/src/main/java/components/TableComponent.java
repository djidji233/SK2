package components;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;

public class TableComponent extends JPanel {

    private JTable table;

    public TableComponent(AbstractTableModel tableModel) {
        super(new GridLayout(1,0));

        table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
    }

    public int getSelectedRow() {
        return table.getSelectedRow();
    }

}
