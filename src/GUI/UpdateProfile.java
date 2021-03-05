package GUI;

import javax.swing.*;

public class UpdateProfile {
    private JPanel mainPanel;
    private JPanel contentPanel;
    private JPanel sidePanel;
    private JLabel bannerLabel;
    private JTable table1;
    private ImageIcon bannerIcon;

    public UpdateProfile() {
        bannerIcon = new ImageIcon("data/banners/test2.png");
        bannerLabel.setIcon(bannerIcon);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }
}
