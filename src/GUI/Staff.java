package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Staff {

    private JPanel panel;
    private JButton CreateButton;
    private JButton editButton;
    private JButton deleteButton;
    private JLabel title;
    private JScrollBar scrollBar1;
    private JTable staffTable;


    private String [] ColumnNames = {
                "ID",
                "First Name",
                "Surname",
                "Role",
                "Username",
                "Password",
                "Address",
                "Contact Number",
                "Email Address"
        };

        private Object [][] data = {
                {"01","Kathy", "Smith","Office Manager","Kathy01","password","64 road road","+44854548544", "kathy@email.com"}
        };



    public Staff() {
        CreateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Create Staff");
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Delete Staff");
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Edit Staff");
            }
        });

        createTable();

    }


    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    private void createTable(){
        staffTable.setModel(new DefaultTableModel(data,ColumnNames));
        TableColumnModel columns = staffTable.getColumnModel();
        columns.getColumn(8).setMinWidth(100);
    }


}
