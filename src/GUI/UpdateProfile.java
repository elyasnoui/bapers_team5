package GUI;

import System.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateProfile {
    private Bapers system;
    private JPanel mainPanel;
    private JPanel contentPanel;
    private JPanel sidePanel;
    private JLabel bannerLabel;
    private JTable UpdateTable;
    private JButton saveButton;
    private JButton editButton;
    private JPanel buttonPanel;
    private JButton deleteButton;
    private JButton createButton;
    private JButton cancelButton;
    private ImageIcon bannerIcon;

    public UpdateProfile(Bapers system) {
        this.system = system;

        bannerIcon = new ImageIcon("data/banners/updateProfile.png");
        bannerLabel.setIcon(bannerIcon);
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
