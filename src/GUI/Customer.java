package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Customer extends JFrame{
    private JPanel panel;
    private JPanel sidePanel;
    private JButton customerButton;
    private JPanel contentPanel;
    private JButton signOutButton;
    private JLabel bannerLabel;
    private JTable table1;
    private JButton createButton;
    private JButton editButton;
    private JButton deleteButton;
    private JScrollPane scrollBar;
    private ImageIcon bannerIcon;

    private String [] ColumnNames = {
            "ID",
            "First Name",
            "Surname",
            "Contact Number",
            "Address",
            "Agreed Discount",
            "Discount Rate"
    };

    private Object [][] data = {
            {"01","John", "Jones","07558804711","1 Alley Way, N2 1NA","Fixed", "20"},
            {"02","Bob", "Marley","07888804444", "5 Bookers, SW11 KWE","Variable", "5,0,3,0,0,0,1"},
            {"03","Game", "Stop","07656186388","74A Snooker, W1 2BA","Flexible","0,1,2"},
            {"04","Doge", "Coin","07563656556","4a Shareholder, NBA 2K","None","None"}
    };

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
        bannerIcon = new ImageIcon("data/banners/login.png");
        bannerLabel.setIcon(bannerIcon);

/*        usernameField.setBorder(null);
        passwordField.setBorder(null);*/

        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button Clicked");
                JOptionPane.showMessageDialog(sidePanel, "I am efte");
            }
        });

        createTable();
    }



    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    private void createTable(){
        table1.setModel(new DefaultTableModel(data,ColumnNames));
        TableColumnModel columns = table1.getColumnModel();
        columns.getColumn(2).setMinWidth(100);



        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        columns.getColumn(0).setCellRenderer(centerRenderer);
        columns.getColumn(3).setCellRenderer(centerRenderer);
        columns.getColumn(5).setCellRenderer(centerRenderer);
        columns.getColumn(6).setCellRenderer(centerRenderer);

        columns.getColumn(0).setMinWidth(50);
        columns.getColumn(1).setMinWidth(50);
        columns.getColumn(2).setMinWidth(50);

    }
}
