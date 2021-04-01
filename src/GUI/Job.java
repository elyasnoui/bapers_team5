package GUI;

import System.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Job extends Form {
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
    private JButton createForthTierButton;
    private JButton editRemoveButton;
    private JButton editAddButton;
    private JComboBox editTaskComboBox;
    private JList editTasksList;
    private JScrollPane editTasksScrollPane;
    private JLabel editAmountLabel;
    private JLabel editCustomerIDValue;
    private JLabel editDeadlineValue;
    private JPanel editPanel;
    private JButton editConfirmButton;
    private JButton editCancelButton;
    private JLabel editTasksAddedLabel;
    private JLabel editJobIDValue;
    private JButton showActiveTasksButton;
    private ImageIcon checkBoxIcon;
    private ImageIcon selectedCheckBoxIcon;
    private List<String[]> jobData;
    private List<Product> productList = new ArrayList<>();
    private List<String> productCart = new ArrayList<>();
    private List<String[]> customerData;
    private List<String[]> availableTaskData;
    private List<String[]> taskData;
    private String[] availableTaskNames;
    private String[] rowData;
    private static DecimalFormat df2 = new DecimalFormat("0.00");
    private int totalTime = 0;
    private double totalPrice = 0.00;
    private int taskQuantity = 0;
    private boolean firstTier = false;
    private boolean secondTier = false;
    private boolean thirdTier = false;
    private boolean forthTier = false;
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
        private int ID;
        private int initialQuantity;
        private int quantity;
        private String name;
        private String department;
        private String duration;
        private double price;

        private Product(int ID, int quantity, String name, String department, String duration, double price) {
            this.ID = ID;
            this.quantity = quantity;
            this.name = name;
            this.department = department;
            this.duration = duration;
            this.price = price;
        }

        private Product(int ID, int initialQuantity, int quantity, String name, String department, String duration, double price) {
            this.ID = ID;
            this.initialQuantity = initialQuantity;
            this.quantity = quantity;
            this.name = name;
            this.department = department;
            this.duration = duration;
            this.price = price;
        }

        public String toString() {
            return "x"+quantity+" - "+name+", "+duration+" min, £"+df2.format(price);
        }

        public String previousString() {
            return "x"+(quantity - 1)+" - "+name+", "+duration+" min, £"+df2.format(price);
        }

        public String followingString() {
            return "x"+(quantity + 1)+" - "+name+", "+duration+" min, £"+df2.format(price);
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
                js[1] = "Tier "+js[1];
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

        // Table listener
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (table.getSelectionModel().getSelectedItemsCount() == 1 &&
                        String.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 6)).equals("Created")) {
                    editButton.addMouseListener(ApplicationWindow.highlightListener);
                    editButton.setToolTipText(null);
                    deleteButton.addMouseListener(ApplicationWindow.highlightListener);
                    deleteButton.setToolTipText(null);
                } else if (table.getSelectionModel().getSelectedItemsCount() > 1) {
                    editButton.removeMouseListener(ApplicationWindow.highlightListener);
                    editButton.setToolTipText("Please select only 1 record");
                    deleteButton.removeMouseListener(ApplicationWindow.highlightListener);
                    deleteButton.setToolTipText("Please select only 1 record");
                } else {
                    editButton.removeMouseListener(ApplicationWindow.highlightListener);
                    editButton.setToolTipText("Please select a record");
                    deleteButton.removeMouseListener(ApplicationWindow.highlightListener);
                    deleteButton.setToolTipText("Please select a record");
                }

                editButton.setEnabled(table.getSelectionModel().getSelectedItemsCount() == 1 &&
                        String.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 6)).equals("Created"));
                deleteButton.setEnabled(table.getSelectionModel().getSelectedItemsCount() == 1 &&
                        String.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 6)).equals("Created"));
            }
        });

        ApplicationWindow.displayTable(table, jobData, tableColumns);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tablePanel.setVisible(false);
                buttonPanel.setVisible(false);
                createPanel.setVisible(true);

                resetCreatePanel();
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tablePanel.setVisible(false);
                buttonPanel.setVisible(false);
                editPanel.setVisible(true);

                int id = Integer.parseInt(String.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 0)));
                resetEditPanel(id);
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(String.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 0)));
                if (!DatabaseConnection.checkForCompletedTasks(id)) {
                    try {
                        if (DatabaseConnection.removeAllTasksWithJobID(id)) {
                            if (!DatabaseConnection.removeJob(id))
                                JOptionPane.showMessageDialog(mainPanel, "Couldn't delete tasks");
                        } else JOptionPane.showMessageDialog(mainPanel, "Couldn't delete job");
                    } catch (SQLException exception) { exception.printStackTrace(); }
                    system.changeScreen("jobs", mainPanel);
                } else JOptionPane.showMessageDialog(mainPanel, "You cannot delete this job");
            }
        });
        createAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taskQuantity++;
                int i = 0;
                while (!productList.get(i).name.equals(String.valueOf(createTaskComboBox.getSelectedItem())))
                    i++;
                totalPrice += productList.get(i).price;
                productList.get(i).quantity++;
                if (productList.get(i).quantity > 1) {
                    int j =0;
                    while (!productCart.get(j).equals(productList.get(i).previousString()))
                        j++;
                    productCart.set(j, productList.get(i).toString());
                } else
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

                createAmountLabel.setText('£'+df2.format(totalPrice)+" before VAT/Discounts/Surcharges");
                totalTime += Integer.parseInt(productList.get(i).duration);
                if (!createIsUrgentCheckBox.isSelected()) {
                    int daysToAdd = ((int) (Math.ceil(totalTime/60)/12))+1;
                    LocalDateTime now = LocalDateTime.now().plusDays(daysToAdd);
                    String d = String.valueOf(now).substring(0,10)+" "+String.valueOf(now).substring(11,16);
                    createDeadlineValue.setText(d);
                } else calculateTime(createIsUrgentCheckBox, createFirstTierButton, createSecondTierButton, createThirdTierButton, createForthTierButton, createDeadlineValue);
            }
        });
        createRemoveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!createTasksList.isSelectionEmpty()) {
                    taskQuantity--;
                    int i = 0;
                    while (!productList.get(i).toString().equals(String.valueOf(createTasksList.getModel().getElementAt(createTasksList.getSelectedIndex()))))
                        i++;
                    totalPrice -= productList.get(i).price;
                    productList.get(i).quantity--;
                    int j =0;
                    if (productList.get(i).quantity >= 1) {
                        while (!productCart.get(j).equals(productList.get(i).followingString()))
                            j++;
                        productCart.set(j, productList.get(i).toString());
                    } else
                        productCart.remove(createTasksList.getSelectedIndex());
                    createTasksList.setListData(productCart.toArray());
                    createTasksList.setVisible(true);
                    createTasksList.setSelectedIndex(j);
                    createAmountLabel.setText('£'+df2.format(totalPrice)+" before VAT/Discounts/Surcharges");

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
                        createDeadlineValue.setText("Add tasks to view deadline");
                        createAmountLabel.setText('£'+df2.format(0)+" before VAT/Discounts/Surcharges");
                        totalTime = 0;
                    } else {
                        totalTime -= Integer.parseInt(productList.get(i).duration);
                        int daysToAdd = ((int) (Math.ceil(totalTime/60)/12))+1;
                        LocalDateTime now = LocalDateTime.now().plusDays(daysToAdd);
                        String d = String.valueOf(now).substring(0,10)+" "+String.valueOf(now).substring(11,16);
                        createDeadlineValue.setText(d);

                        if (createIsUrgentCheckBox.isSelected())
                            calculateTime(createIsUrgentCheckBox, createFirstTierButton, createSecondTierButton, createThirdTierButton, createForthTierButton, createDeadlineValue);
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
                int result = JOptionPane.showConfirmDialog(mainPanel,
                        "You will lose your job creation progress, are you sure?",
                        "Warning",
                        JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    system.changeScreen("customers", mainPanel);
                    ((Customer) system.getCurrentForm()).switchCreate();
                }
            }
        });
        createIsUrgentCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createUrgencyPanel.setVisible(createIsUrgentCheckBox.isSelected());
                createUrgencyLabel.setVisible(createIsUrgentCheckBox.isSelected());

                calculateTime(createIsUrgentCheckBox, createFirstTierButton, createSecondTierButton, createThirdTierButton, createForthTierButton, createDeadlineValue);
                if (!createIsUrgentCheckBox.isSelected()) {
                    firstTier = false;
                    secondTier = false;
                    thirdTier = false;
                    forthTier = false;
                    createFirstTierButton.setEnabled(true);
                    createSecondTierButton.setEnabled(true);
                    createThirdTierButton.setEnabled(true);
                    createForthTierButton.setEnabled(true);

                }
            }
        });
        createFirstTierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateDeadline(createFirstTierButton, createDeadlineValue);
                firstTier = true;
                secondTier = false;
                thirdTier = false;
                forthTier = false;
                createFirstTierButton.setEnabled(false);
                createSecondTierButton.setEnabled(true);
                createThirdTierButton.setEnabled(true);
                createForthTierButton.setEnabled(true);
            }
        });
        createSecondTierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateDeadline(createSecondTierButton, createDeadlineValue);
                firstTier = false;
                secondTier = true;
                thirdTier = false;
                forthTier = false;
                createFirstTierButton.setEnabled(true);
                createSecondTierButton.setEnabled(false);
                createThirdTierButton.setEnabled(true);
                createForthTierButton.setEnabled(true);
            }
        });
        createThirdTierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateDeadline(createThirdTierButton, createDeadlineValue);
                firstTier = false;
                secondTier = false;
                thirdTier = true;
                forthTier = false;
                createFirstTierButton.setEnabled(true);
                createSecondTierButton.setEnabled(true);
                createThirdTierButton.setEnabled(false);
                createForthTierButton.setEnabled(true);
            }
        });
        createForthTierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateDeadline(createForthTierButton, createDeadlineValue);
                firstTier = false;
                secondTier = false;
                thirdTier = false;
                forthTier = true;
                createFirstTierButton.setEnabled(true);
                createSecondTierButton.setEnabled(true);
                createThirdTierButton.setEnabled(true);
                createForthTierButton.setEnabled(false);
            }
        });
        cretaeConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (createTasksList.getModel().getSize() > 0 && !createCustomerIDValue.getText().equals("Please select using 'Customer ID Lookup'")
                && !createDeadlineValue.getText().equals("Add tasks to view deadline")) {

                    if (createIsUrgentCheckBox.isSelected() && (!firstTier && !secondTier && !thirdTier && !forthTier)) {
                        JOptionPane.showMessageDialog(mainPanel, "Please select a discount tier");
                        return;
                    }

                    int urgent = 0;
                    if (createIsUrgentCheckBox.isSelected()) {
                        if (firstTier) urgent = 1;
                        else if (secondTier) urgent = 2;
                        else if (thirdTier) urgent = 3;
                        else if (forthTier) urgent = 4;
                    }

                    LocalDateTime deadline = LocalDateTime.of(
                            Integer.parseInt(createDeadlineValue.getText().substring(0,4)),      //year
                            Integer.parseInt(createDeadlineValue.getText().substring(5,7)),      //month
                            Integer.parseInt(createDeadlineValue.getText().substring(8,10)),     //day
                            Integer.parseInt(createDeadlineValue.getText().substring(11,13)),    //hour
                            Integer.parseInt(createDeadlineValue.getText().substring(14,16))     //minute
                    );

                    try {
                        if (DatabaseConnection.addJob(urgent, totalPrice, LocalDate.now(), deadline, "Created",
                                Integer.parseInt(createCustomerIDValue.getText()))) {
                            for (String p : productCart) {
                                String[] temp = p.split(",");
                                temp = temp[0].split(" - ");
                                int j = 0;
                                while (!productList.get(j).name.equals(temp[1]))
                                    j++;
                                for (int i = 0; i<Integer.parseInt(temp[0].substring(1)); i++) {
                                    int staffID = DatabaseConnection.getTechnician(productList.get(j).department);
                                    if (!DatabaseConnection.addTask(productList.get(j).ID, DatabaseConnection.getNextID("job"),
                                            productList.get(j).name, productList.get(j).department, productList.get(j).duration,
                                            productList.get(j).price, staffID,0))
                                        JOptionPane.showMessageDialog(mainPanel, "Couldn't insert task");
                                }
                            }
                            system.changeScreen("jobs", mainPanel);
                        } else JOptionPane.showMessageDialog(mainPanel, "Couldn't insert job");
                    } catch (SQLException exception) {
                        exception.printStackTrace();
                    }
                } else JOptionPane.showMessageDialog(mainPanel, "Please ensure you have tasks added and have chosen a customer ID");
            }
        });
        editConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (editTasksList.getModel().getSize() > 0) {
                    LocalDateTime deadline = LocalDateTime.of(
                            Integer.parseInt(editDeadlineValue.getText().substring(0,4)),      //year
                            Integer.parseInt(editDeadlineValue.getText().substring(5,7)),      //month
                            Integer.parseInt(editDeadlineValue.getText().substring(8,10)),     //day
                            Integer.parseInt(editDeadlineValue.getText().substring(11,13)),    //hour
                            Integer.parseInt(editDeadlineValue.getText().substring(14,16))     //minute
                    );
                    for (Product p : productList) {
                        boolean found = false;
                        for (String pc : productCart) {
                            String[] temp = pc.split(",");
                            temp = temp[0].split(" - ");

                            if (p.name.equals(temp[1])) {
                                found = true;
                                int quantity = Integer.parseInt(temp[0].substring(1));

                                if (p.initialQuantity < quantity) {
                                    for (int i=0; i<(quantity-p.initialQuantity); i++) {
                                        int staffID = DatabaseConnection.getTechnician(p.department);
                                        try {
                                            if (!DatabaseConnection.addTask(p.ID, Integer.parseInt(editJobIDValue.getText()),
                                                    p.name, p.department, p.duration,
                                                    p.price, staffID,0))
                                                JOptionPane.showMessageDialog(mainPanel, "Couldn't insert task");
                                        } catch (SQLException exception) { exception.printStackTrace(); }
                                    }
                                }

                                else if (p.initialQuantity > quantity) {
                                    System.out.println("here");
                                    for (int i=0; i<(p.initialQuantity-quantity); i++) {
                                        try {
                                            int taskID = DatabaseConnection.getTaskIDFromJobID(Integer.parseInt(editJobIDValue.getText()), p.ID);
                                            if (!DatabaseConnection.removeTask(taskID))
                                                JOptionPane.showMessageDialog(mainPanel, "Couldn't delete task");
                                        } catch (SQLException exception) { exception.printStackTrace(); }
                                    }
                                }
                            }
                        }

                        if (!found && p.initialQuantity > 0) {
                            for (int i=0; i<p.initialQuantity; i++) {
                                try {
                                    if (!DatabaseConnection.removeTasks(Integer.parseInt(editJobIDValue.getText()), p.ID))
                                        JOptionPane.showMessageDialog(mainPanel, "Couldn't delete task");
                                } catch (SQLException exception) { exception.printStackTrace(); }
                            }
                        }

                        try {
                            if (!DatabaseConnection.editJob(Integer.parseInt(editJobIDValue.getText()), Integer.parseInt(rowData[1]),
                                    totalPrice, deadline, "Created"))
                                JOptionPane.showMessageDialog(mainPanel, "Couldn't edit job");
                        } catch (SQLException exception) { exception.printStackTrace(); }
                    }
                } else {
                    for (Product p : productList) {
                        for (int i=0; i<p.initialQuantity; i++) {
                            try {
                                if (!DatabaseConnection.removeTasks(Integer.parseInt(editJobIDValue.getText()), p.ID))
                                    JOptionPane.showMessageDialog(mainPanel, "Couldn't delete task");
                            } catch (SQLException exception) { exception.printStackTrace(); }
                        }
                    }

                    if (!DatabaseConnection.checkForCompletedTasks(Integer.parseInt(editJobIDValue.getText()))) {
                        if (!DatabaseConnection.removeJob(Integer.parseInt(editJobIDValue.getText())))
                            JOptionPane.showMessageDialog(mainPanel, "Couldn't delete job");
                    } else {

                        LocalDate now = LocalDate.now();
                        LocalDate adjustment;
                        if (DatabaseConnection.isCustomerValuedCustomer(Integer.parseInt(rowData[7]))) {
                            adjustment = LocalDate.of(now.getYear(), now.getMonth(), 10);
                            adjustment = adjustment.plusMonths(1);
                        } else adjustment = now;
                        LocalDateTime deadline = LocalDateTime.of(adjustment.getYear(), adjustment.getMonth(), adjustment.getDayOfMonth(),
                                0, 0);

                        try {
                            if (!DatabaseConnection.editJob(Integer.parseInt(editJobIDValue.getText()), Integer.parseInt(rowData[1]),
                                    totalPrice, deadline, "Unpaid"))
                            JOptionPane.showMessageDialog(mainPanel, "Couldn't edit job");
                        } catch (SQLException exception) { exception.printStackTrace(); }
                    }

                } system.changeScreen("jobs", mainPanel);
            }
        });
        createCancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createPanel.setVisible(false);
                tablePanel.setVisible(true);
                buttonPanel.setVisible(true);
            }
        });

        editAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editTasksAddedLabel.setVisible(true);
                editTasksScrollPane.setVisible(true);
                editTasksList.setVisible(true);

                int i = 0;
                while (!productList.get(i).name.equals(String.valueOf(editTaskComboBox.getSelectedItem())))
                    i++;
                totalPrice += productList.get(i).price;
                productList.get(i).quantity++;
                if (productList.get(i).quantity > 1) {
                    int j =0;
                    while (!productCart.get(j).equals(productList.get(i).previousString()))
                        j++;
                    productCart.set(j, productList.get(i).toString());
                } else
                    productCart.add(productList.get(i).toString());
                editTasksList.setListData(productCart.toArray());

                editAmountLabel.setText('£'+df2.format(totalPrice)+" before VAT/Discounts/Surcharges");
                totalTime += Integer.parseInt(productList.get(i).duration);

                if (!rowData[1].equals("0")) {
                    LocalDateTime deadline = LocalDateTime.of(
                            Integer.parseInt(rowData[5].substring(0,4)),      //year
                            Integer.parseInt(rowData[5].substring(5,7)),      //month
                            Integer.parseInt(rowData[5].substring(8,10)),     //day
                            Integer.parseInt(rowData[5].substring(11,13)),    //hour
                            Integer.parseInt(rowData[5].substring(14,16))     //minute
                    );
                    String d = deadline.plusMinutes(totalTime).toString();
                    editDeadlineValue.setText(d.substring(0, 10)+" "+d.substring(11, 16));
                } else {
                    LocalDateTime deadline = LocalDateTime.of(
                            Integer.parseInt(rowData[5].substring(0,4)),      //year
                            Integer.parseInt(rowData[5].substring(5,7)),      //month
                            Integer.parseInt(rowData[5].substring(8,10)),     //day
                            Integer.parseInt(rowData[5].substring(11,13)),    //hour
                            Integer.parseInt(rowData[5].substring(14,16))     //minute
                    );
                    int daysToAdd = ((int) (Math.ceil(totalTime/60)/12))+1;
                    String d;
                    if (!deadline.plusDays(daysToAdd).isBefore(LocalDateTime.now()))
                        d = deadline.plusDays(daysToAdd).toString();
                    else d = deadline.toString();
                    editDeadlineValue.setText(d.substring(0, 10)+" "+d.substring(11, 16));
                }
            }
        });
        editRemoveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!editTasksList.isSelectionEmpty()) {
                    int i = 0;
                    while (!productList.get(i).toString().equals(String.valueOf(editTasksList.getModel().getElementAt(editTasksList.getSelectedIndex()))))
                        i++;
                    totalPrice -= productList.get(i).price;
                    productList.get(i).quantity--;
                    int j =0;
                    if (productList.get(i).quantity >= 1) {
                        while (!productCart.get(j).equals(productList.get(i).followingString()))
                            j++;
                        productCart.set(j, productList.get(i).toString());
                    } else
                        productCart.remove(editTasksList.getSelectedIndex());
                    editTasksList.setListData(productCart.toArray());
                    editTasksList.setVisible(true);
                    editTasksList.setSelectedIndex(j);
                    editAmountLabel.setText('£'+df2.format(totalPrice)+" before VAT/Discounts/Surcharges");
                    totalTime -= Integer.parseInt(productList.get(i).duration);

                    if (productCart.isEmpty()) {
                        editTasksAddedLabel.setVisible(false);
                        editTasksScrollPane.setVisible(false);
                        editTasksList.setVisible(false);
                        editDeadlineValue.setText("Add tasks to view deadline");
                        editAmountLabel.setText('£'+df2.format(0)+" before VAT/Discounts/Surcharges");
                    } else {
                        if (!rowData[1].equals("0")) {
                            LocalDateTime deadline = LocalDateTime.of(
                                    Integer.parseInt(rowData[5].substring(0,4)),      //year
                                    Integer.parseInt(rowData[5].substring(5,7)),      //month
                                    Integer.parseInt(rowData[5].substring(8,10)),     //day
                                    Integer.parseInt(rowData[5].substring(11,13)),    //hour
                                    Integer.parseInt(rowData[5].substring(14,16))     //minute
                            );

                            String d;
                            if (totalTime > -1)
                                d = deadline.plusMinutes(totalTime).toString();
                            else
                                d = deadline.minusMinutes(Math.abs(totalTime)).toString();
                            editDeadlineValue.setText(d.substring(0, 10)+" "+d.substring(11, 16));
                        } else {
                            LocalDateTime deadline = LocalDateTime.of(
                                    Integer.parseInt(rowData[5].substring(0,4)),      //year
                                    Integer.parseInt(rowData[5].substring(5,7)),      //month
                                    Integer.parseInt(rowData[5].substring(8,10)),     //day
                                    Integer.parseInt(rowData[5].substring(11,13)),    //hour
                                    Integer.parseInt(rowData[5].substring(14,16))     //minute
                            );
                            int daysToAdd = ((int) (Math.ceil(totalTime/60)/12))+1;
                            String d;
                            if (!deadline.plusDays(daysToAdd).isBefore(LocalDateTime.now()))
                                d = deadline.plusDays(daysToAdd).toString();
                            else d = deadline.toString();
                            editDeadlineValue.setText(d.substring(0, 10)+" "+d.substring(11, 16));
                        }
                    }
                }
            }
        });
        editCancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editPanel.setVisible(false);
                buttonPanel.setVisible(true);
                tablePanel.setVisible(true);
            }
        });
        showActiveTasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jobData = DatabaseConnection.showOnlyActiveJobs();
                    assert jobData != null;
                    for (String[] js : jobData) {
                        js[1] = "Tier "+js[1];
                        js[2] = '£' + js[2];
                        js[5] = js[5].substring(0,10)+" "+js[5].substring(11,16);
                    }
                } catch (Exception exp) { exp.printStackTrace(); }

                ApplicationWindow.displayTable(table, jobData, tableColumns);
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
            productList.add(new Product(Integer.parseInt(at[0]), 0, at[1], at[2], at[3], Double.parseDouble(at[4])));
            availableTaskNames[i] = at[1]; i++;
        }
        createTaskComboBox.setModel(new DefaultComboBoxModel<>(availableTaskNames));
        createTasksScrollPane.setBorder(null);
        createTasksScrollPane.getVerticalScrollBar().setBackground(new Color(76,84,118));
        createTasksScrollPane.getVerticalScrollBar().setForeground(new Color(124,134,175));

        createTasksAddedLabel.setVisible(false);
        createTasksScrollPane.setVisible(false);
        createRemoveButton.setVisible(false);
        totalPrice = 0;
        taskQuantity = 0;
        createAmountLabel.setText('£'+df2.format(totalPrice));
        createCustomerIDValue.setText("Please select using 'Customer ID Lookup'");
        createDeadlineValue.setText("Add tasks to view deadline");

        createIsUrgentLabel.setVisible(false);
        createIsUrgentCheckBox.setVisible(false);
        createIsUrgentCheckBox.setSelected(false);
        createUrgencyLabel.setVisible(false);
        createUrgencyPanel.setVisible(false);
    }

    public void resetEditPanel(int id) {
        rowData = DatabaseConnection.getRowBySingleID("job", id);
        taskData = DatabaseConnection.getTasksFromJobID(id);
        availableTaskData = DatabaseConnection.getData("availableTask");
        editJobIDValue.setText(String.valueOf(id));
        if (!productCart.isEmpty()) productCart.clear();
        if (!productList.isEmpty()) productList.clear();
        availableTaskNames = new String[availableTaskData.size()];
        int i = 0;
        for (String[] at : availableTaskData) {
            productList.add(new Product(Integer.parseInt(at[0]), 0, 0, at[1], at[2], at[3],
                    Double.parseDouble(at[4])));
            availableTaskNames[i] = at[1]; i++;
        }
        totalTime = 0;
        editTaskComboBox.setModel(new DefaultComboBoxModel<>(availableTaskNames));
        for (String[] td : taskData) {
            if (!td[9].equals("true")) {
                i = 0;
                while (!productList.get(i).name.equals(td[3]))
                    i++;
                productList.get(i).quantity++;
                productList.get(i).initialQuantity++;
                if (productList.get(i).quantity > 1) {
                    int j =0;
                    while (!productCart.get(j).equals(productList.get(i).previousString()))
                        j++;
                    productCart.set(j, productList.get(i).toString());
                } else
                    productCart.add(productList.get(i).toString());
            }
        }
        editTasksList.setListData(productCart.toArray());
        editCustomerIDValue.setText(rowData[7]);
        editTasksScrollPane.setBorder(null);
        editTasksList.setBorder(null);
        totalPrice = Double.parseDouble(rowData[2]);
        editAmountLabel.setText('£'+rowData[2]+" before VAT/Discounts");
        editDeadlineValue.setText(rowData[5].substring(0, 10)+" "+rowData[5].substring(11, 16));
    }

    public void calculateDeadline(JButton tier, JLabel value) {
        LocalDateTime deadline = LocalDateTime.now();
        deadline = deadline.plusHours(Integer.parseInt(tier.getText().substring(6)));
        String d = String.valueOf(deadline).substring(0,10)+" "+String.valueOf(deadline).substring(11,16);
        value.setText(d);
    }

    public void calculateTime(JCheckBox checkBox, JButton firstTier, JButton secondTier, JButton thirdTier, JButton forthTier, JLabel value) {
        if (checkBox.isSelected()) {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime deadline;
            String datetime;

            int hoursToAdd = (int) Math.ceil(totalTime/60)+1;
            deadline = now.plusHours(hoursToAdd);
            Duration d = Duration.between(now, deadline);
            firstTier.setText("Under "+d.toHours());

            hoursToAdd = (int) Math.ceil(totalTime/60)+3;
            deadline = now.plusHours(hoursToAdd);
            d = Duration.between(now, deadline);
            secondTier.setText("Under "+d.toHours());

            hoursToAdd = (int) Math.ceil(totalTime/60)+6;
            deadline = now.plusHours(hoursToAdd);
            d = Duration.between(now, deadline);
            thirdTier.setText("Under "+d.toHours());

            hoursToAdd = (int) Math.ceil(totalTime/60)+24;
            deadline = now.plusHours(hoursToAdd);
            d = Duration.between(now, deadline);
            forthTier.setText("Under "+d.toHours());
        } else {
            int daysToAdd = ((int) (Math.ceil(totalTime/60)/12))+1;
            LocalDateTime now = LocalDateTime.now().plusHours(daysToAdd);
            String d = String.valueOf(now).substring(0,10)+" "+String.valueOf(now).substring(11,16);
            value.setText(d);
        }
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

    public void switchToCreateLookup() {
        buttonPanel.setVisible(false);
        tablePanel.setVisible(false);
        customerLookupPanel.setVisible(true);
        resetCreatePanel();
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
