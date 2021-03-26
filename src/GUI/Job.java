package GUI;

import System.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Job {
    private Bapers system;
    private JPanel sidePanel;
    private JLabel usernameLabel;
    private JLabel roleLabel;
    private JButton logoutButton;
    private JButton jobsButton;
    private JButton customerButton;
    private JButton paymentsButton;
    private JButton staffButton;
    private JButton tasksButton;
    private JButton reportsButton;
    private JButton databaseButton;
    private JPanel contentPanel;
    private ImageIcon bannerIcon;
    private JLabel bannerLabel;
    private JPanel buttonPanel;
    private JButton deleteButton;
    private JButton editButton;
    private JButton createButton;
    private JTable table;
    private JPanel mainPanel;
    private JPanel createPanel;
    private JButton popupCancelButton;
    private JScrollPane tablePanel;
    private JTextField priceField;
    private JTextField statusField;
    private JButton popupCreateButton;
    private JCheckBox isUrgentCheckBox;
    private JComboBox taskComboBox;
    private JLabel amountLabel;
    private JLabel tasksAddedLabel;
    private JScrollPane tasksScrollPane;
    private JList tasksList;
    private JButton removeButton;
    private JButton addButton;
    private JLabel customerIDLabel;
    private JLabel customerIDValue;
    private JPanel customerLookupPanel;
    private JTextField lastNameField;
    private JTextField firstNameField;
    private JScrollPane customerScrollPane;
    private JTable customerTable;
    private JButton lookupSelectButton;
    private JButton lookupCancelButton;
    private JButton lookupButton;
    private JLabel deadlineValue;
    private JButton firstTierButton;
    private JButton secondTierButton;
    private JButton thirdTierButton;
    private JLabel urgencyLabel;
    private JPanel urgencyPanel;
    private JPanel urgencyLabelPanel;
    private JLabel isUrgentLabel;
    private JButton lookupCreateButton;
    private ImageIcon checkBoxIcon;
    private ImageIcon selectedCheckBoxIcon;
    private List<String[]> jobData;
    private List<Product> productList = new ArrayList<>();
    private List<String[]> customerData;
    private static DecimalFormat df2 = new DecimalFormat("0.00");
    private double totalPrice = 0.00;
    private final String[] tableColumns = {
            "ID",
            "Is Urgent",
            "Price",
            "Start Date",
            "End Date",
            "Deadline",
            "Status",
            "Customer ID"
    };
    private final String[] tasks = {
            "Use of large copy camera",
            "Black and white film processing",
            "Bag up",
            "Colour film processing",
            "Colour Transparency processing",
            "Use of small copy camera",
            "Mount Transparencies"
    };
    private final String[] customerColumns = {
            "ID",
            "First Name",
            "Surname",
            "Contact Number",
            "Address",
            "Email"
    };
    private class Product {
        private String name;
        private String duration;
        private double price;

        private Product(String name, String duration, double price) {
            this.name = name;
            this.duration = duration;
            this.price = price;
        }

        public String toString() {
            return name+", "+duration+", £"+df2.format(price);
        }
    }

    public Job(Bapers system) {
        this.system = system;

        amountLabel.setText('£'+df2.format(totalPrice));
        taskComboBox.setModel(new DefaultComboBoxModel<>(tasks));
        taskComboBox.setVisible(true);
        tasksScrollPane.setBorder(null);
        tasksScrollPane.getVerticalScrollBar().setBackground(new Color(76,84,118));
        tasksScrollPane.getVerticalScrollBar().setForeground(new Color(124,134,175));

        firstNameField.setBorder(null);
        lastNameField.setBorder(null);
        customerTable.setModel(new DefaultTableModel(null, customerColumns));

        try {
            jobData = DatabaseConnection.getData("job");
            assert jobData != null;
            for (String[] js : jobData) {
                js[1] = js[1].equals("true") ? "Yes" : "No";
                js[2] = '£' + js[2];
                js[5] = js[5].substring(0,10)+" "+js[5].substring(11,16);
            }
        }
        catch (Exception e) { e.printStackTrace(); }

        bannerIcon = new ImageIcon("data/banners/job.png");
        bannerLabel.setIcon(bannerIcon);

        checkBoxIcon = new ImageIcon("data/graphics/test.png");
        selectedCheckBoxIcon = new ImageIcon("data/graphics/test2.png");
        isUrgentCheckBox.setIcon(checkBoxIcon);
        isUrgentCheckBox.setSelectedIcon(selectedCheckBoxIcon);

        addMouseListeners();

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                system.changeScreen("logout", mainPanel);
                removeMouseListeners();
            }
        });
        jobsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                system.changeScreen("jobs", mainPanel);
                removeMouseListeners();
            }
        });
        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                system.changeScreen("customers", mainPanel);
                removeMouseListeners();
            }
        });
        paymentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                system.changeScreen("payments", mainPanel);
                removeMouseListeners();
            }
        });
        staffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                system.changeScreen("staff", mainPanel);
                removeMouseListeners();
            }
        });
        tasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                system.changeScreen("tasks", mainPanel);
                removeMouseListeners();
            }
        });
        reportsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                system.changeScreen("reports", mainPanel);
                removeMouseListeners();
            }
        });
        databaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                system.changeScreen("database", mainPanel);
                removeMouseListeners();
            }
        });

        ApplicationWindow.displayTable(table, jobData, tableColumns);
        ApplicationWindow.currentDateNTime();
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tablePanel.setVisible(false);
                buttonPanel.setVisible(false);
                createPanel.setVisible(true);
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (String.valueOf(taskComboBox.getSelectedItem())) {
                    case "Use of large copy camera":
                        productList.add(new Product(String.valueOf(taskComboBox.getSelectedItem()), "120min", 19));
                        break;
                    case "Black and white film processing":
                        productList.add(new Product(String.valueOf(taskComboBox.getSelectedItem()), "60min", 49.5));
                        break;
                    case "Bag up":
                        productList.add(new Product(String.valueOf(taskComboBox.getSelectedItem()), "30min", 6));
                        break;
                    case "Colour film processing":
                        productList.add(new Product(String.valueOf(taskComboBox.getSelectedItem()), "90min", 80));
                        break;
                    case "Colour Transparency processing":
                        productList.add(new Product(String.valueOf(taskComboBox.getSelectedItem()), "180min", 110.3));
                        break;
                    case "Use of small copy camera":
                        productList.add(new Product(String.valueOf(taskComboBox.getSelectedItem()), "75min", 8.3));
                        break;
                    case "Mount Transparencies":
                        productList.add(new Product(String.valueOf(taskComboBox.getSelectedItem()), "45min", 55.5));
                        break;

                }

                totalPrice += productList.get(productList.size()-1).price;

                List<String> outputList = new ArrayList<>();

                for (Product p : productList)
                    outputList.add(p.toString());

                tasksList.setListData(outputList.toArray());
                tasksList.setVisible(true);

                tasksAddedLabel.setVisible(true);
                tasksScrollPane.setVisible(true);
                removeButton.setVisible(true);

                isUrgentLabel.setVisible(true);
                isUrgentCheckBox.setVisible(true);

                amountLabel.setText('£'+df2.format(totalPrice)+" before VAT/Discounts");
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!tasksList.isSelectionEmpty()) {
                    totalPrice -= productList.get(tasksList.getSelectedIndex()).price;

                    productList.remove(tasksList.getSelectedIndex());
                    tasksList.setListData(productList.toArray());
                    tasksList.setVisible(true);

                    amountLabel.setText('£'+df2.format(totalPrice)+" before VAT/Discounts");

                    if (productList.isEmpty()) resetCreatePanel();
                }
            }
        });

        popupCancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createPanel.setVisible(false);
                buttonPanel.setVisible(true);
                tablePanel.setVisible(true);
                resetCreatePanel();
            }
        });

        lookupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createPanel.setVisible(false);
                customerLookupPanel.setVisible(true);
            }
        });

        firstNameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (firstNameField.getText().length() >= 3 || lastNameField.getText().length() >= 3) {
                    customerSearch();
                } else customerTable.setModel(new DefaultTableModel(null, customerColumns));
            }
        });
        lastNameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (lastNameField.getText().length() >= 3 || firstNameField.getText().length() >= 3) {
                    customerSearch();
                } else customerTable.setModel(new DefaultTableModel(null, customerColumns));
            }
        });
        lookupSelectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!customerTable.getSelectionModel().isSelectionEmpty()) {
                    String ID = customerData.get(customerTable.getSelectedRow())[0];
                    customerIDValue.setText(ID);
                    customerLookupPanel.setVisible(false);
                    createPanel.setVisible(true);
                }
            }
        });
        lookupCancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerLookupPanel.setVisible(false);
                createPanel.setVisible(true);
                resetLookupPanel();
            }
        });

        lookupCreateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isDeleteAllowed = true;
                for (int i : table.getSelectedRows()) {
                    if (!jobData.get(i)[6].equals("Created")) {
                        isDeleteAllowed = false;
                        break;
                    }
                }

                if (!table.getSelectionModel().isSelectionEmpty() && isDeleteAllowed) {
                    for (int id : table.getSelectedRows()) {
                        try {
                            int ID = Integer.parseInt(jobData.get(id)[0]);
                            DatabaseConnection.deleteJobsFromTables(ID);
                            DatabaseConnection.removeJob(ID);
                            system.changeScreen("jobs", mainPanel);
                        } catch (SQLException exception) { exception.printStackTrace(); }
                    }
                } else if (!isDeleteAllowed)
                    JOptionPane.showMessageDialog(mainPanel, "Selected row(s) contain completed jobs.");
            }
        });
        isUrgentCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                urgencyPanel.setVisible(isUrgentCheckBox.isSelected());
                urgencyLabelPanel.setVisible(isUrgentCheckBox.isSelected());


            }
        });

    }

    private void customerSearch() {
        try {
            customerData = DatabaseConnection.searchCustomer(firstNameField.getText(), lastNameField.getText());
            assert customerData != null;
            ApplicationWindow.displayTable(customerTable, customerData, customerColumns);
        }
        catch (Exception e) { e.printStackTrace(); }
    }

    public void resetLookupPanel() {
        firstNameField.setText("");
        lastNameField.setText("");
        customerTable.setModel(new DefaultTableModel(null, customerColumns));
    }

    public void resetCreatePanel() {
        tasksAddedLabel.setVisible(false);
        tasksScrollPane.setVisible(false);
        removeButton.setVisible(false);
        totalPrice = 0;
        amountLabel.setText('£'+df2.format(totalPrice));
        productList.clear();
        customerIDValue.setText("");

        isUrgentLabel.setVisible(false);
        isUrgentCheckBox.setVisible(false);
        urgencyLabelPanel.setVisible(false);
        urgencyPanel.setVisible(false);
    }

    private void addMouseListeners() {
        logoutButton.addMouseListener(ApplicationWindow.mouseListener);
        jobsButton.addMouseListener(ApplicationWindow.mouseListener);
        customerButton.addMouseListener(ApplicationWindow.mouseListener);
        paymentsButton.addMouseListener(ApplicationWindow.mouseListener);
        staffButton.addMouseListener(ApplicationWindow.mouseListener);
        tasksButton.addMouseListener(ApplicationWindow.mouseListener);
        reportsButton.addMouseListener(ApplicationWindow.mouseListener);
        databaseButton.addMouseListener(ApplicationWindow.mouseListener);
        createButton.addMouseListener(ApplicationWindow.mouseListener);
        editButton.addMouseListener(ApplicationWindow.mouseListener);
        deleteButton.addMouseListener(ApplicationWindow.mouseListener);
        addButton.addMouseListener(ApplicationWindow.mouseListener);
        removeButton.addMouseListener(ApplicationWindow.mouseListener);
        popupCreateButton.addMouseListener(ApplicationWindow.mouseListener);
        popupCancelButton.addMouseListener(ApplicationWindow.mouseListener);
    }

    private void removeMouseListeners() {
        logoutButton.removeMouseListener(ApplicationWindow.mouseListener);
        jobsButton.removeMouseListener(ApplicationWindow.mouseListener);
        customerButton.removeMouseListener(ApplicationWindow.mouseListener);
        paymentsButton.removeMouseListener(ApplicationWindow.mouseListener);
        staffButton.removeMouseListener(ApplicationWindow.mouseListener);
        tasksButton.removeMouseListener(ApplicationWindow.mouseListener);
        reportsButton.removeMouseListener(ApplicationWindow.mouseListener);
        databaseButton.removeMouseListener(ApplicationWindow.mouseListener);
        createButton.removeMouseListener(ApplicationWindow.mouseListener);
        editButton.removeMouseListener(ApplicationWindow.mouseListener);
        deleteButton.removeMouseListener(ApplicationWindow.mouseListener);
        addButton.removeMouseListener(ApplicationWindow.mouseListener);
        removeButton.removeMouseListener(ApplicationWindow.mouseListener);
        popupCreateButton.removeMouseListener(ApplicationWindow.mouseListener);
        popupCancelButton.removeMouseListener(ApplicationWindow.mouseListener);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
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
