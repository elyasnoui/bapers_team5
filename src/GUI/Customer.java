package GUI;

import System.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Customer extends JFrame{

    private Bapers system;
    private JPanel panel;
    private JPanel sidePanel;
    private JButton customerButton;
    private JPanel contentPanel;
    private JLabel bannerLabel;
    private JTable table;
    private JLabel nameLabel;
    private JLabel roleLabel;
    private JButton logoutButton;
    private JButton jobsButton;
    private JButton paymentsButton;
    private JButton staffButton;
    private JButton tasksButton;
    private JButton reportsButton;
    private JButton databaseButton;
    private JPanel buttonPanel;
    private JButton deleteButton;
    private JButton editButton;
    private JButton createButton;
    private ImageIcon bannerIcon;
    private List<String[]> customerData;
    private List<String[]> valuedCustomerData;
    private final String[] tableColumns = {
            "ID",
            "First Name",
            "Surname",
            "Contact Number",
            "Address",
            "Email",
            "Agreed Discount",
            "Discount Rate"
    };

    public Customer(Bapers system) {
        this.system = system;

        try {
            customerData = DatabaseConnection.getData("customer");
            valuedCustomerData = DatabaseConnection.getData("valuedCustomer");
            assert customerData != null && valuedCustomerData != null;
            String[] temp;
            for (String[] vs : valuedCustomerData) {
                int i = 0;
                for (String[] cs : customerData) {
                    if (vs[0].equals(cs[0])) {
                        temp = new String[] { cs[0], cs[1], cs[2], cs[3], cs[4], cs[5], vs[1], vs[2] };
                        customerData.set(i, temp);
                    }
                    i++;
                }
            }
        } catch (Exception e) { e.printStackTrace(); }

        bannerIcon = new ImageIcon("data/banners/login.png");
        bannerLabel.setIcon(bannerIcon);

        jobsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                system.changeScreen("jobs", panel);
            }
        });
        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                system.changeScreen("customers", panel);
            }
        });
        paymentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                system.changeScreen("payments", panel);
            }
        });
        staffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                system.changeScreen("staff", panel);
            }
        });
        tasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                system.changeScreen("tasks", panel);
            }
        });
        reportsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                system.changeScreen("reports", panel);
            }
        });
        databaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                system.changeScreen("database", panel);
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                system.changeScreen("logout", panel);
            }
        });

        ApplicationWindow.createTable(table, customerData, tableColumns);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    /*
    private void createTable(){
        table.setModel(new DefaultTableModel(customerData.toArray(new Object[][] {}), tableColumns));
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

     */
}
