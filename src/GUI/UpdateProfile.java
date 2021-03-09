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
    private JLabel bannerLabel;
    private JTable UpdateTable;
    private JButton saveButton;
    private JPanel sidePanel;
    private JLabel usernameLabel;
    private JButton logoutButton;
    private JButton jobsButton;
    private JButton customerButton;
    private JButton paymentsButton;
    private JButton staffButton;
    private JButton tasksButton;
    private JButton reportsButton;
    private JButton databaseButton;
    private JPanel buttonPanel;
    private JButton editButton;
    private JTable profileTable;
    private JLabel roleLabel;
    private JButton cancelButton;
    private ImageIcon bannerIcon;

    public UpdateProfile(Bapers system) {
        this.system = system;

        bannerIcon = new ImageIcon("data/banners/updateProfile.png");
        bannerLabel.setIcon(bannerIcon);

        createTable();
    }

    private String [] ColumnNames = {
            "ID",
            "First Name",
            "Last Name",
            "Address",
            "Contact Number",
            "Email"
    };

    private Object [][] info = {
            {"11", "Moses", "Smith", "1 PremTable Avenue", "+4485454438544", "LeadersOfBPL@email.com"}
    };

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    private void createTable(){
        profileTable.setModel(new DefaultTableModel(info,ColumnNames));
        TableColumnModel columns = profileTable.getColumnModel();
        columns.getColumn(5).setMinWidth(100);
    }

    public JLabel getUsername() {
        return usernameLabel;
    }

    public void setUsername(String username) {
        this.usernameLabel.setText(username);
    }

    public JLabel getRole() {
        return roleLabel;
    }

    public void setRole(String role) {
        this.roleLabel.setText(role);
    }
}
