package GUI;

import System.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
    private JButton createCancelButton;
    private JScrollPane tablePanel;
    private JTextField priceField;
    private JTextField statusField;
    private JButton cretaeConfirmButton;
    private JCheckBox createIsUrgentCheckBox;
    private JComboBox createTaskComboBox;
    private JLabel createAmountLabel;
    private JLabel createTasksAddedLabel;
    private JScrollPane createTasksScrollPane;
    private JList createTasksList;
    private JButton createRemoveButton;
    private JButton createAddButton;
    private JLabel createCustomerIDLabel;
    private JLabel createCustomerIDValue;
    private JPanel customerLookupPanel;
    private JTextField lastNameField;
    private JTextField firstNameField;
    private JScrollPane customerScrollPane;
    private JTable customerTable;
    private JButton lookupSelectButton;
    private JButton lookupCancelButton;
    private JButton createLookupButton;
    private JLabel createDeadlineValue;
    private JButton createFirstTierButton;
    private JButton createSecondTierButton;
    private JButton createThirdTierButton;
    private JLabel createUrgencyLabel;
    private JPanel createUrgencyPanel;
    private JPanel urgencyLabelPanel;
    private JLabel createIsUrgentLabel;
    private JButton lookupCreateButton;
    private JLabel createDeadlineLabel;
    private ImageIcon checkBoxIcon;
    private ImageIcon selectedCheckBoxIcon;
    private List<String[]> jobData;
    private List<Product> productList = new ArrayList<>();
    private List<String> productCart = new ArrayList<>();
    private List<String[]> customerData;
    private List<String[]> availableTaskData;
    private String[] availableTaskNames;
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
    private final String[] customerColumns = {
            "ID",
            "Title",
            "First Name",
            "Surname",
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

        createAmountLabel.setText('£'+df2.format(totalPrice));
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
        } catch (Exception e) { e.printStackTrace(); }

        bannerIcon = new ImageIcon("data/banners/job.png");
        bannerLabel.setIcon(bannerIcon);

        checkBoxIcon = new ImageIcon("data/graphics/test.png");
        selectedCheckBoxIcon = new ImageIcon("data/graphics/test2.png");
        createIsUrgentCheckBox.setIcon(checkBoxIcon);
        createIsUrgentCheckBox.setSelectedIcon(selectedCheckBoxIcon);

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
        //ApplicationWindow.currentDateNTime();
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tablePanel.setVisible(false);
                buttonPanel.setVisible(false);
                createPanel.setVisible(true);

                resetCreatePanel();
            }
        });
        createAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = 0;
                while (!productList.get(i).name.equals(String.valueOf(createTaskComboBox.getSelectedItem())))
                    i++;
                totalPrice += productList.get(i).price;

                productCart.add(productList.get(i).toString());
                createTasksList.setListData(productCart.toArray());

                if (!createRemoveButton.isVisible()) {
                    createTasksList.setVisible(true);
                    createTasksAddedLabel.setVisible(true);
                    createTasksScrollPane.setVisible(true);
                    createRemoveButton.setVisible(true);
                    createIsUrgentLabel.setVisible(true);
                    createIsUrgentCheckBox.setVisible(true);
                }

                createAmountLabel.setText('£'+df2.format(totalPrice)+" before VAT/Discounts");
            }
        });
        createRemoveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!createTasksList.isSelectionEmpty()) {
                    int i = 0;
                    while (!productList.get(i).toString().equals(String.valueOf(createTasksList.getModel().getElementAt(createTasksList.getSelectedIndex()))))
                        i++;
                    totalPrice -= productList.get(i).price;
                    productCart.remove(createTasksList.getSelectedIndex());
                    createTasksList.setListData(productCart.toArray());
                    createTasksList.setVisible(true);

                    createAmountLabel.setText('£'+df2.format(totalPrice)+" before VAT/Discounts");

                    if (productCart.isEmpty()) {
                        createTasksList.setVisible(false);
                        createTasksAddedLabel.setVisible(false);
                        createTasksScrollPane.setVisible(false);
                        createRemoveButton.setVisible(false);
                        createIsUrgentLabel.setVisible(false);
                        createIsUrgentCheckBox.setVisible(false);
                        createUrgencyLabel.setVisible(false);
                        createIsUrgentCheckBox.setSelected(false);
                        createUrgencyPanel.setVisible(false);
                    }
                }
            }
        });

        createCancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createPanel.setVisible(false);
                buttonPanel.setVisible(true);
                tablePanel.setVisible(true);
            }
        });

        createLookupButton.addActionListener(new ActionListener() {
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
                    createCustomerIDValue.setText(ID);
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
                        int ID = Integer.parseInt(jobData.get(id)[0]);
                        DatabaseConnection.removeJob(ID);
                        system.changeScreen("jobs", mainPanel);
                    }
                } else if (!isDeleteAllowed)
                    JOptionPane.showMessageDialog(mainPanel, "Selected row(s) contain completed jobs.");
            }
        });
        createIsUrgentCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createUrgencyPanel.setVisible(createIsUrgentCheckBox.isSelected());
                createUrgencyLabel.setVisible(createIsUrgentCheckBox.isSelected());
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
        availableTaskData = DatabaseConnection.getData("availableTask");
        availableTaskNames = new String[availableTaskData.size()];
        int i = 0;
        for (String[] at : availableTaskData) {
            productList.add(new Product(at[1], at[3], Double.parseDouble(at[4])));
            availableTaskNames[i] = at[1]; i++;
        }
        createTaskComboBox.setModel(new DefaultComboBoxModel<>(availableTaskNames));
        createTaskComboBox.setVisible(true);
        createTasksScrollPane.setBorder(null);
        createTasksScrollPane.getVerticalScrollBar().setBackground(new Color(76,84,118));
        createTasksScrollPane.getVerticalScrollBar().setForeground(new Color(124,134,175));


        createTasksAddedLabel.setVisible(false);
        createTasksScrollPane.setVisible(false);
        createRemoveButton.setVisible(false);
        totalPrice = 0;
        createAmountLabel.setText('£'+df2.format(totalPrice));
        createCustomerIDValue.setText("Please select using 'Customer ID Lookup'");

        createIsUrgentLabel.setVisible(false);
        createIsUrgentCheckBox.setVisible(false);
        createUrgencyLabel.setVisible(false);
        createUrgencyPanel.setVisible(false);
    }

    private void addMouseListeners() {
        logoutButton.addMouseListener(ApplicationWindow.highlightListener);
        jobsButton.addMouseListener(ApplicationWindow.highlightListener);
        customerButton.addMouseListener(ApplicationWindow.highlightListener);
        paymentsButton.addMouseListener(ApplicationWindow.highlightListener);
        staffButton.addMouseListener(ApplicationWindow.highlightListener);
        tasksButton.addMouseListener(ApplicationWindow.highlightListener);
        reportsButton.addMouseListener(ApplicationWindow.highlightListener);
        databaseButton.addMouseListener(ApplicationWindow.highlightListener);
        createButton.addMouseListener(ApplicationWindow.highlightListener);
        editButton.addMouseListener(ApplicationWindow.highlightListener);
        deleteButton.addMouseListener(ApplicationWindow.highlightListener);
        createAddButton.addMouseListener(ApplicationWindow.highlightListener);
        createRemoveButton.addMouseListener(ApplicationWindow.highlightListener);
        cretaeConfirmButton.addMouseListener(ApplicationWindow.highlightListener);
        createCancelButton.addMouseListener(ApplicationWindow.highlightListener);
    }

    private void removeMouseListeners() {
        logoutButton.removeMouseListener(ApplicationWindow.highlightListener);
        jobsButton.removeMouseListener(ApplicationWindow.highlightListener);
        customerButton.removeMouseListener(ApplicationWindow.highlightListener);
        paymentsButton.removeMouseListener(ApplicationWindow.highlightListener);
        staffButton.removeMouseListener(ApplicationWindow.highlightListener);
        tasksButton.removeMouseListener(ApplicationWindow.highlightListener);
        reportsButton.removeMouseListener(ApplicationWindow.highlightListener);
        databaseButton.removeMouseListener(ApplicationWindow.highlightListener);
        createButton.removeMouseListener(ApplicationWindow.highlightListener);
        editButton.removeMouseListener(ApplicationWindow.highlightListener);
        deleteButton.removeMouseListener(ApplicationWindow.highlightListener);
        createAddButton.removeMouseListener(ApplicationWindow.highlightListener);
        createRemoveButton.removeMouseListener(ApplicationWindow.highlightListener);
        cretaeConfirmButton.removeMouseListener(ApplicationWindow.highlightListener);
        createCancelButton.removeMouseListener(ApplicationWindow.highlightListener);
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
