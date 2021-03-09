package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.util.List;

public class ApplicationWindow extends JFrame {
    public static String username;
    public static String role;

    public ApplicationWindow(String title) {
        super(title);
    }

    public static void displayTable(final JTable table, final List<String[]> records, final String[] tableColumns) {
        table.setModel(new DefaultTableModel(records.toArray(new Object[][] {}), tableColumns));
        TableColumnModel columns = table.getColumnModel();
        columns.getColumn(2).setMinWidth(100);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        columns.getColumn(0).setCellRenderer(centerRenderer);
        columns.getColumn(3).setCellRenderer(centerRenderer);
        columns.getColumn(5).setCellRenderer(centerRenderer);
        columns.getColumn(6).setCellRenderer(centerRenderer);

        //columns.getColumn(0).setMinWidth(30);
        columns.getColumn(1).setMinWidth(30);
        columns.getColumn(2).setMinWidth(30);

        columns.getColumn(0).setPreferredWidth(10);
    }
}
