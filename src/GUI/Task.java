package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Task {


    private JButton CreateButton;
    private JButton editButton;
    private JButton deleteButton;
    private JTable taskTable;
    private JPanel mainPanel;
    private JButton createButton;
    private JLabel bannerLabel;
    private ImageIcon bannerIcon;


    private String[] ColumnNames = {
            "ID",
            "Description",
            "Department",
            "Duration (MIN)",
            "PRICE (£)",
    };

    private Object[][] data = {
            {"01", "Use of large Copy Camera", "Copy Room", "120", "19.00"}
    };


    public Task() {
        bannerIcon = new ImageIcon("data/banners/staff.png");
        bannerLabel.setIcon(bannerIcon);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Create Staff");
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Delete Staff");
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Edit Staff");
            }
        });



        createTable();

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }
    private void createTable(){
        taskTable.setModel(new DefaultTableModel(data,ColumnNames));
        TableColumnModel columns = taskTable.getColumnModel();
        columns.getColumn(4).setMinWidth(100);
    }


}
