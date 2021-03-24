package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
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
        //columns.getColumn(6).setCellRenderer(centerRenderer);

        //columns.getColumn(0).setMinWidth(30);
        columns.getColumn(1).setMinWidth(30);
        columns.getColumn(2).setMinWidth(30);

        columns.getColumn(0).setPreferredWidth(10);
    }

    public static String[] checkPrivileges() {


        return null;
    }

    public static MouseListener mouseListener = new MouseAdapter()
    {
        public void mouseEntered(java.awt.event.MouseEvent evt)
        {
            Component c = evt.getComponent();

            if (c.getBackground().equals(new Color(124, 134, 175))) {
                c.setBackground(new Color(176, 191, 241));
                return;
            }

            c.setBackground(new Color(124, 134, 175));
        }

        public void mouseExited(java.awt.event.MouseEvent evt)
        {
            Component c = evt.getComponent();

            if (c.getBackground().equals(new Color(176, 191, 241))) {
                c.setBackground(new Color(124, 134, 175));
                return;
            }

            c.setBackground(new Color(76, 84, 118));
        }
    };

    public static KeyListener inputValidator = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {}

        @Override
        public void keyReleased(KeyEvent e) {
            char c = e.getKeyChar();
            JTextField textField = ((JTextField) e.getComponent());

            switch (textField.getName()) {
                // If the field allows only integers
                case "dd": case "mm": case "yyyy": case "pound": case "penny":
                    // Checks and removes non-integers
                    if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE))
                        textField.setText(textField.getText().replaceAll("[^\\d]", ""));

                    // Ensures dd and mm are limited to 2 characters and yyyy to 4
                    // Also allows for money to be formatted correctly (5,2)
                    switch (textField.getName()) {
                        case "dd": case "mm": case "penny":
                            if (textField.getText().length() > 2)
                                textField.setText(textField.getText().substring(0, 2));
                            break;
                        case "yyyy":
                            if (textField.getText().length() > 4)
                                textField.setText(textField.getText().substring(0, 4));
                            break;
                        case "pound":
                            if (textField.getText().length() > 5)
                                textField.setText(textField.getText().substring(0, 5));
                            break;
                    }

                    break;
            }
        }
    };
}
