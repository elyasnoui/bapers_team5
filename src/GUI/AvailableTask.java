package GUI;

import System.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.List;

public class AvailableTask {
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
    private JLabel bannerLabel;
    private ImageIcon bannerIcon;
    private JPanel buttonPanel;
    private JButton deleteButton;
    private JButton editButton;
    private JButton createButton;
    private JScrollPane tablePanel;
    private JTable table;
    private JPanel mainPanel;
    private JButton backButton;
    private JPanel createPanel;
    private JComboBox createDepartmentComboBox;
    private JButton createConfirmButton;
    private JButton createCancelButton;
    private JTextField createDescriptionTextField;
    private JTextField createTimeRequiredTextField;
    private JTextField createPriceTextField;
    private JPanel editPanel;
    private JTextField editDescriptionTextField;
    private JComboBox editDepartmentComboBox;
    private JTextField editTimeRequiredTextField;
    private JTextField editPriceTextField;
    private JButton editConfirmButton;
    private JButton editCancelButton;
    private JLabel editIDLabel;
    private List<String[]> availableTaskData;

    private final String descriptionRegex = "[A-Z][A-Za-z0-9 .,()-]{5,255}?";
    private final String timeRequiredRegex = "[0-9]{1,3}?";
    private final String priceRegex = "[0-9]{1,3}([.][0-9]{2})?";
    private final LineBorder borderError = new LineBorder(Color.RED, 1);

    private final String[] tableColumns = {
            "ID",
            "Description",
            "Department",
            "Time Taken",
            "Price"
    };
    private final String[] departments = {
            "Copy room",
            "Development area",
            "Finishing room",
            "Packing",
    };

    public AvailableTask(Bapers system) {
        this.system = system;

        try {
            availableTaskData = DatabaseConnection.getData("availableTask");
            assert availableTaskData != null;
            for (String[] ats : availableTaskData) {
                ats[3] = ats[3]+" min";
                ats[4] = '£'+ats[4];
            }
        } catch (Exception e) { e.printStackTrace(); }

        bannerIcon = new ImageIcon("data/banners/tasks.png");
        bannerLabel.setIcon(bannerIcon);

        addMouseListeners();
        resetCreatePanel();

        ApplicationWindow.displayTable(table, availableTaskData, tableColumns);

        // Side panel listeners
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { system.changeScreen("logout", mainPanel); }
        });
        jobsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                system.changeScreen("jobs", mainPanel);
            }
        });
        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                system.changeScreen("customers", mainPanel);
            }
        });
        paymentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                system.changeScreen("payments", mainPanel);
            }
        });
        staffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                system.changeScreen("staff", mainPanel);
            }
        });
        tasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                system.changeScreen("tasks", mainPanel);
            }
        });
        reportsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                system.changeScreen("reports", mainPanel);
            }
        });
        databaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                system.changeScreen("database", mainPanel);
            }
        });

        // Top button panel listeners
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeMouseListener();
                system.changeScreen("tasks", mainPanel);
            }
        });
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (editPanel.isVisible()) editPanel.setVisible(false);
                else { tablePanel.setVisible(false);
                    buttonPanel.setVisible(false); }

                createPanel.setVisible(true);
                resetCreatePanel();
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectionModel().getSelectedItemsCount() == 1) {
                    if (createPanel.isVisible()) createPanel.setVisible(false);
                    else { tablePanel.setVisible(false);
                        buttonPanel.setVisible(false); }

                    editPanel.setVisible(true);
                    resetEditPanel(table.getSelectedRow());
                } else if (table.getSelectionModel().getSelectedItemsCount() == 0)
                    JOptionPane.showMessageDialog(mainPanel, "Please select a record");
                else JOptionPane.showMessageDialog(mainPanel, "Please select only 1 record");
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!table.getSelectionModel().isSelectionEmpty()) {
                    for (int id : table.getSelectedRows()) {
                        int ID = Integer.parseInt(availableTaskData.get(id)[0]);
                        DatabaseConnection.removeAvailableTask(ID);
                        system.changeScreen("availableTask", mainPanel);
                    }
                }
            }
        });

        // Create panel listeners
        createDescriptionTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (!createDescriptionTextField.getText().matches(descriptionRegex)) {
                    createDescriptionTextField.setBorder(borderError);
                    createDescriptionTextField.setToolTipText("Must start with capital letter (5-255 characters)");
                } else {
                    createDescriptionTextField.setBorder(null);
                    createDescriptionTextField.setToolTipText(null);
                }
            }
        });
        createTimeRequiredTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (!createTimeRequiredTextField.getText().matches(timeRequiredRegex)) {
                    createTimeRequiredTextField.setBorder(borderError);
                    createTimeRequiredTextField.setToolTipText("E.g. 70, 5, 150 (Max 999)");
                } else {
                    createTimeRequiredTextField.setBorder(null);
                    createTimeRequiredTextField.setToolTipText(null);
                }
            }
        });
        createPriceTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (!createPriceTextField.getText().matches(priceRegex)) {
                    createPriceTextField.setBorder(borderError);
                    createPriceTextField.setToolTipText("E.g. 5.99, 12.50, 10 (Max £999.99)");
                } else {
                    createPriceTextField.setBorder(null);
                    createPriceTextField.setToolTipText(null);
                }
            }
        });
        createConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validatePanel(createDescriptionTextField, createTimeRequiredTextField, createPriceTextField)) {
                    String department = (String) createDepartmentComboBox.getItemAt(createDepartmentComboBox.getSelectedIndex());
                    try { if (DatabaseConnection.addAvailableTask(createDescriptionTextField.getText(), department,
                                createTimeRequiredTextField.getText(), Double.parseDouble(createPriceTextField.getText())))
                            system.changeScreen("availableTask", mainPanel);
                        else JOptionPane.showMessageDialog(mainPanel, "Couldn't add task");
                    } catch (SQLException exception) { exception.printStackTrace(); }
                }
            }
        });
        createCancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createPanel.setVisible(false);
                buttonPanel.setVisible(true);
                tablePanel.setVisible(true);
                resetCreatePanel();
            }
        });

        // Edit panel listeners
        editDescriptionTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (!editDescriptionTextField.getText().matches(descriptionRegex)) {
                    editDescriptionTextField.setBorder(borderError);
                    editDescriptionTextField.setToolTipText("Must start with capital letter (5-255 characters)");
                } else {
                    editDescriptionTextField.setBorder(null);
                    editDescriptionTextField.setToolTipText(null);
                }
            }
        });
        editTimeRequiredTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (!editTimeRequiredTextField.getText().matches(timeRequiredRegex)) {
                    editTimeRequiredTextField.setBorder(borderError);
                    editTimeRequiredTextField.setToolTipText("E.g. 70, 5, 150 (Max 999)");
                } else {
                    editTimeRequiredTextField.setBorder(null);
                    editTimeRequiredTextField.setToolTipText(null);
                }
            }
        });
        editPriceTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (!editPriceTextField.getText().matches(priceRegex)) {
                    editPriceTextField.setBorder(borderError);
                    editPriceTextField.setToolTipText("E.g. 5.99, 12.50, 10 (Max £999.99)");
                } else {
                    editPriceTextField.setBorder(null);
                    editPriceTextField.setToolTipText(null);
                }
            }
        });
        editConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validatePanel(editDescriptionTextField, editTimeRequiredTextField, editPriceTextField)) {
                    String department = (String) editDepartmentComboBox.getItemAt(editDepartmentComboBox.getSelectedIndex());
                    try { if (DatabaseConnection.editAvailableTask(Integer.parseInt(editIDLabel.getText()),
                            editDescriptionTextField.getText(), department, editTimeRequiredTextField.getText(),
                            Double.parseDouble(editPriceTextField.getText())))
                        system.changeScreen("availableTask", mainPanel);
                    else JOptionPane.showMessageDialog(mainPanel, "Couldn't edit task");
                    } catch (SQLException exception) { exception.printStackTrace(); }
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
    }

    private boolean validatePanel(JTextField descriptionTextField, JTextField timeRequiredTextField, JTextField priceTextField) {
        if (!descriptionTextField.getText().matches(descriptionRegex)) {
            descriptionTextField.setBorder(borderError);
            descriptionTextField.setToolTipText("Must start with capital letter (5-255 characters)");
        } else {
            descriptionTextField.setBorder(null);
            descriptionTextField.setToolTipText(null);
        }

        if (!timeRequiredTextField.getText().matches(timeRequiredRegex)) {
            timeRequiredTextField.setBorder(borderError);
            timeRequiredTextField.setToolTipText("E.g. 70, 5, 150 (Max 999)");
        } else {
            timeRequiredTextField.setBorder(null);
            timeRequiredTextField.setToolTipText(null);
        }

        if (!priceTextField.getText().matches(priceRegex)) {
            priceTextField.setBorder(borderError);
            priceTextField.setToolTipText("E.g. 5.99, 12.50, 10 (Max £999.99)");
        } else {
            priceTextField.setBorder(null);
            priceTextField.setToolTipText(null);
        }

        return !(descriptionTextField.getBorder() == borderError || timeRequiredTextField.getBorder() == borderError ||
                priceTextField.getBorder() == borderError);
    }

    private void resetCreatePanel() {
        createDepartmentComboBox.setModel(new DefaultComboBoxModel<>(departments));

        createDescriptionTextField.setBorder(null);
        createDescriptionTextField.setText("");
        createDescriptionTextField.setToolTipText(null);

        createTimeRequiredTextField.setBorder(null);
        createTimeRequiredTextField.setText("");
        createTimeRequiredTextField.setToolTipText(null);

        createPriceTextField.setBorder(null);
        createPriceTextField.setText("");
        createPriceTextField.setToolTipText(null);
    }

    private void resetEditPanel(int id) {
        editDepartmentComboBox.setModel(new DefaultComboBoxModel<>(departments));

        editIDLabel.setText(availableTaskData.get(id)[0]);

        editDescriptionTextField.setBorder(null);
        editDescriptionTextField.setText(availableTaskData.get(id)[1]);
        editDescriptionTextField.setToolTipText("");

        switch (availableTaskData.get(id)[2]) {
            case "Development area":
                editDepartmentComboBox.setSelectedIndex(1);
                break;
            case "Finishing room":
                editDepartmentComboBox.setSelectedIndex(2);
                break;
            case "Packing":
                editDepartmentComboBox.setSelectedIndex(3);
                break;
            default:
                editDepartmentComboBox.setSelectedIndex(0);
                break;
        }

        String timeRequired = availableTaskData.get(id)[3].substring(0, availableTaskData.get(id)[3].length()-4);
        editTimeRequiredTextField.setBorder(null);
        editTimeRequiredTextField.setText(timeRequired);
        editDescriptionTextField.setToolTipText("");

        String price = availableTaskData.get(id)[4].substring(1);
        editPriceTextField.setBorder(null);
        editPriceTextField.setText(price);
        editPriceTextField.setToolTipText("");
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
        backButton.addMouseListener(ApplicationWindow.mouseListener);
        createConfirmButton.addMouseListener(ApplicationWindow.mouseListener);
        createCancelButton.addMouseListener(ApplicationWindow.mouseListener);
        editConfirmButton.addMouseListener(ApplicationWindow.mouseListener);
        editCancelButton.addMouseListener(ApplicationWindow.mouseListener);
    }

    private void removeMouseListener() {
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
        backButton.removeMouseListener(ApplicationWindow.mouseListener);
        createConfirmButton.removeMouseListener(ApplicationWindow.mouseListener);
        createCancelButton.removeMouseListener(ApplicationWindow.mouseListener);
        editConfirmButton.removeMouseListener(ApplicationWindow.mouseListener);
        editCancelButton.removeMouseListener(ApplicationWindow.mouseListener);
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
