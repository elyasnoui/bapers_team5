package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

import System.*;

public class Login extends Form {
    private Bapers system;
    private JPanel mainPanel;
    private JPanel sidePanel;
    private JPanel contentPanel;
    private JLabel bannerLabel;
    private JButton sideLoginButton;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private ImageIcon bannerIcon;

    public Login(Bapers system) {
        this.system = system;

        bannerIcon = new ImageIcon("data/banners/login.png");
        bannerLabel.setIcon(bannerIcon);

        usernameField.setBorder(null);
        passwordField.setBorder(null);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signIn();
            }
        });
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    signIn();
            }
        });

        loginButton.addMouseListener(ApplicationWindow.highlightListener);
    }

    public void signIn() {
        DatabaseConnection db = null;
        try {
            if (DatabaseConnection.VerifyLogInCredentials(usernameField.getText(), passwordField.getPassword()))
                system.filterUser();
            else JOptionPane.showMessageDialog(mainPanel, "Invalid Credentials");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }
}
