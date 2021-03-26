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
    private JComboBox departmentComboBox;
    private JButton popupCreateButton;
    private JButton popupCancelButton;
    private JTextField descriptionTextField;
    private JTextField timeRequiredTextField;
    private JTextField priceTextField;
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
        descriptionTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (!descriptionTextField.getText().matches(descriptionRegex)) {
                    descriptionTextField.setBorder(borderError);
                    descriptionTextField.setToolTipText("Must start with capital letter (5-255 characters)");
                } else {
                    descriptionTextField.setBorder(null);
                    descriptionTextField.setToolTipText(null);
                }
            }
        });
        timeRequiredTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (!timeRequiredTextField.getText().matches(timeRequiredRegex)) {
                    timeRequiredTextField.setBorder(borderError);
                    timeRequiredTextField.setToolTipText("E.g. 70, 5, 150 (Max 999)");
                } else {
                    timeRequiredTextField.setBorder(null);
                    timeRequiredTextField.setToolTipText(null);
                }
            }
        });
        priceTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (!priceTextField.getText().matches(priceRegex)) {
                    priceTextField.setBorder(borderError);
                    priceTextField.setToolTipText("E.g. 5.99, 12.50, 10 (Max £999.99)");
                } else {
                    priceTextField.setBorder(null);
                    priceTextField.setToolTipText(null);
                }
            }
        });
        popupCreateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateCreatePanel()) {
                    String department = (String) departmentComboBox.getItemAt(departmentComboBox.getSelectedIndex());
                    try { if (DatabaseConnection.addAvailableTask(descriptionTextField.getText(), department,
                                timeRequiredTextField.getText(), Double.parseDouble(priceTextField.getText())))
                            system.changeScreen("availableTask", mainPanel);
                        else JOptionPane.showMessageDialog(mainPanel, "Couldn't add task");
                    } catch (SQLException exception) { exception.printStackTrace(); }
                }
            }
        });
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tablePanel.setVisible(false);
                buttonPanel.setVisible(false);
                createPanel.setVisible(true);
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
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeMouseListener();
                system.changeScreen("tasks", mainPanel);
            }
        });
    }

    private boolean validateCreatePanel() {
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
        descriptionTextField.setBorder(null);
        timeRequiredTextField.setBorder(null);
        priceTextField.setBorder(null);

        departmentComboBox.setModel(new DefaultComboBoxModel<>(departments));
        descriptionTextField.setBorder(null);
        descriptionTextField.setText("");
        descriptionTextField.setToolTipText(null);
        timeRequiredTextField.setBorder(null);
        timeRequiredTextField.setText("");
        timeRequiredTextField.setToolTipText(null);
        priceTextField.setBorder(null);
        priceTextField.setText("");
        priceTextField.setToolTipText(null);
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
