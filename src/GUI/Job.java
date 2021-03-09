
package GUI;

import System.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Job {
    private Bapers system;
    private JButton CreateButton;
    private JButton editButton;
    private JButton deleteButton;
    private JTable jobTable;
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
            "Job ID",
            "Customer ID",
            "Task(s)",
            "Price (£)",
            "Urgency",
            "Duration",
            "Instructions",
            "Current Task",
            "Status",
            "Payment Received",
            "Start/End Date"
    };

    private Object [][] data = {
            {"871","02", "1,4,6","107.30","Urgent","4 Hours, 45 Mins","Quality Check","4", "Pending", "Yes","17/02/21 - 19.02.21"}
    };



    public Job(Bapers system) {
        this.system = system;

        bannerIcon = new ImageIcon("data/banners/Job.png");
        bannerLabel.setIcon(bannerIcon);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Create Job");
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Edit Job");
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Delete Job");
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
        jobTable.setModel(new DefaultTableModel(data,ColumnNames));
        TableColumnModel columns = jobTable.getColumnModel();
        columns.getColumn(9).setMinWidth(100);
        columns.getColumn(10).setMinWidth(100);
        columns.setColumnMargin(10);
    }

    /*
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
     */
}
