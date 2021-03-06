package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateProfile {
    private JPanel mainPanel;
    private JPanel contentPanel;
    private JPanel sidePanel;
    private JLabel bannerLabel;
    private JTable UpdateTable;
    private JButton saveButton;
    private JButton editButton;
    private JButton cancelButton;
    private ImageIcon bannerIcon;

    public UpdateProfile() {
        bannerIcon = new ImageIcon("data/banners/updateProfile.png");
        bannerLabel.setIcon(bannerIcon);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Save data");
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Cancel request");
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Edit");
            }
        });
        createTable();
    }

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

    private Object [][] info = {
            {"11","Moses", "Smith","Technician","Moses.01","password","1 PremTable Avenue","+4485454438544", "LeadersOfBPL@email.com"}
    };

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    private void createTable(){
        UpdateTable.setModel(new DefaultTableModel(info,ColumnNames));
        TableColumnModel columns = UpdateTable.getColumnModel();
        columns.getColumn(8).setMinWidth(100);
    }
}
