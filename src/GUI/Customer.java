package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Customer extends JFrame{
    private JButton submit;
    private JPanel panel;

    public Customer() {
        //add(panel);
        //setTitle("My Title");
        //setSize(1280,720);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button Clicked");
                JOptionPane.showMessageDialog(rootPane, "I am efte");
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
