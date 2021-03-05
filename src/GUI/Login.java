package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import System.*;

public class Login {
    private JPanel mainPanel;
    private JPanel sidePanel;
    private JPanel contentPanel;
    private JLabel bannerLabel;
    private JButton sideLoginButton;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private ImageIcon bannerIcon;

    public Login() {
        bannerIcon = new ImageIcon("data/banners/login.png");
        bannerLabel.setIcon(bannerIcon);

        usernameField.setBorder(null);
        passwordField.setBorder(null);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DatabaseConnection db = null;
                try {
                    db = new DatabaseConnection();
                    if (db.VerifyLogInCredentials(usernameField.getText(), passwordField.getPassword()))
                        JOptionPane.showMessageDialog(mainPanel, "Logged in");
                    else JOptionPane.showMessageDialog(mainPanel, "Invalid Credentials");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
