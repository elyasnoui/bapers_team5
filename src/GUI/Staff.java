package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Staff {

    private JPanel contentPanel;
    private JButton CreateButton;
    private JButton editButton;
    private JButton deleteButton;
    private JLabel title;
    private JTable staffTable;
    private JPanel mainPanel;
    private JPanel sidePanel;
    private JLabel bannerLabel;
    private JButton logoutButton;
    private ImageIcon bannerIcon;

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
        bannerIcon = new ImageIcon("data/banners/test.png");
        bannerLabel.setIcon(bannerIcon);

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

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    private void createTable(){
        staffTable.setModel(new DefaultTableModel(data,ColumnNames));
        TableColumnModel columns = staffTable.getColumnModel();
        columns.getColumn(8).setMinWidth(100);
    }


}
