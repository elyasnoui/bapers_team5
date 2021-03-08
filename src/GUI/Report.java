package GUI;

import javax.swing.*;
import java.awt.*;

public class Report {
    private JPanel mainPanel;
    private JPanel sidePanel;
    private JPanel contentPanel;
    private JLabel bannerLabel;
    private JButton deleteButton;
    private JButton editButton;
    private JButton createButton;
    private JPanel buttonPanel;
    private ImageIcon bannerIcon;

    public Report() {
        bannerIcon = new ImageIcon("data/banners/report.png");
        bannerLabel.setIcon(bannerIcon);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }
}
