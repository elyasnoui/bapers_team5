
package GUI.OfficeManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Staff {

    private JButton CreateButton;
    private JButton editButton;
    private JButton deleteButton;
    private JTable staffTable;
    private JPanel mainPanel;
    private JPanel sidePanel;
    private JPanel contentPanel;
    private JButton createButton;
    private JLabel bannerLabel;
    private JPanel navigationPanel;
    private JButton customerButton;
    private JButton paymentButton;
    private JButton staffButton;
    private JButton taskButton;
    private JButton jobButton;
    private JButton reportButton;
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
        bannerIcon = new ImageIcon("data/banners/staff.png");
        bannerLabel.setIcon(bannerIcon);

         createButton.addActionListener(new ActionListener() {
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
