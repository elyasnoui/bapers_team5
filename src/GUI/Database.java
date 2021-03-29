package GUI;

import System.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Database  {
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
    private ImageIcon bannerIcon;
    private JLabel bannerLabel;
    private JPanel buttonPanel;
    private JButton deleteButton;
    private JButton backupButton;
    private JButton pathButton;
    private JPanel mainPanel;
    private JLabel jLabel1;
    private JTextField searchText;
    private JTextField searchText2;
    private JButton restoreButton;
    private JLabel jLabel2;
    private JButton browseButton2;

    private List<String[]> databaseData;
    private final String[] tableColumns = {
            "ID",
            "First Name",
            "Last Name",
            "Contact Number",
            "Address",
            "Email",
            "NI",
            "Work Hours",
            "Username",
            "Password",
            "Role",
            "Privileges"
    };

    String path =null;
    String filename;

    public Database(Bapers system) {
        this.system = system;

        try {
            //databaseData = DatabaseConnection.getData("task");
        }
        catch (Exception e) { e.printStackTrace(); }

        bannerIcon = new ImageIcon("data/banners/database.png");
        bannerLabel.setIcon(bannerIcon);

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

        logoutButton.addMouseListener(ApplicationWindow.highlightListener);
        jobsButton.addMouseListener(ApplicationWindow.highlightListener);
        customerButton.addMouseListener(ApplicationWindow.highlightListener);
        paymentsButton.addMouseListener(ApplicationWindow.highlightListener);
        staffButton.addMouseListener(ApplicationWindow.highlightListener);
        tasksButton.addMouseListener(ApplicationWindow.highlightListener);
        reportsButton.addMouseListener(ApplicationWindow.highlightListener);
        databaseButton.addMouseListener(ApplicationWindow.highlightListener);
        pathButton.addMouseListener(ApplicationWindow.highlightListener);
        backupButton.addMouseListener(ApplicationWindow.highlightListener);
        restoreButton.addMouseListener(ApplicationWindow.highlightListener);
        browseButton2.addMouseListener(ApplicationWindow.highlightListener);

        //ApplicationWindow.displayTable(table, taskData, tableColumns);



        pathButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.showOpenDialog(fc.getParent());
                String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

                try {
                    File f = fc.getSelectedFile();
                    path = f.getAbsolutePath();
                    path = path.replace('\\','/');
                    filename = path + "_" + date + ".sql";
                    searchText.setText(filename);
                }catch (Exception e1){
                    e1.printStackTrace();
                }

            }
        });

        backupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            Process p;
            try{
                Runtime runtime = Runtime.getRuntime();
                p=runtime.exec("C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysqldump.exe -P3306 -h 34.105.223.156 -u root -pcityproject5 bapers_db -r" + filename);

                int processComplete = p.waitFor();
                if(processComplete==0){
                    jLabel1.setText("Backup Created Successfully");
                }else {
                     jLabel1.setText("Can't create backup");
                }
            } catch (Exception e1) {
              e1.printStackTrace();
            }
            }
        });

        restoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            String user = "root";
            String pass = "cityproject5";
            String[] restoreCmd = new String[] {"C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysqldump.exe","--user="+user,"--password="+pass,"-e","source "+filename};

            Process process;
            try{
                process = Runtime.getRuntime().exec(restoreCmd);
                int procCom = process.waitFor();
                if(procCom == 0){
                    jLabel2.setText("Restore successful");
                } else {
                    jLabel2.setText("Restore unsuccessful");
                }
            }catch (Exception e1){
                e1.printStackTrace();
                }
            }
        });
        browseButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    JFileChooser fc = new JFileChooser();
                    fc.showOpenDialog(fc.getParent());
                    try{
                        File f = fc.getSelectedFile();
                        path  = f.getAbsolutePath();
                        path = path.replace('\\','/');
                        searchText2.setText(path);
                    }catch (Exception e1){

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
