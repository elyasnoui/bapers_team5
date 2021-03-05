package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Customer extends JFrame{
    private JPanel panel;
    private JPanel sidePanel;
    private JButton customerButton;
    private JPanel contentPanel;

    public Customer() {
        //add(panel);
        //setTitle("My Title");
        //setSize(1280,720);

/*        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button Clicked");
                JOptionPane.showMessageDialog(sidePanel, "I am efte");
            }
        });*/


        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button Clicked");
                JOptionPane.showMessageDialog(sidePanel, "I am efte");
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
}
