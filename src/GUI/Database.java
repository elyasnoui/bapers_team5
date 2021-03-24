package GUI;

import System.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Database extends Component {
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
    private JButton browse2;
    private JLabel jLabel2;

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

        logoutButton.addMouseListener(ApplicationWindow.mouseListener);
        jobsButton.addMouseListener(ApplicationWindow.mouseListener);
        customerButton.addMouseListener(ApplicationWindow.mouseListener);
        paymentsButton.addMouseListener(ApplicationWindow.mouseListener);
        staffButton.addMouseListener(ApplicationWindow.mouseListener);
        tasksButton.addMouseListener(ApplicationWindow.mouseListener);
        reportsButton.addMouseListener(ApplicationWindow.mouseListener);
        databaseButton.addMouseListener(ApplicationWindow.mouseListener);
        pathButton.addMouseListener(ApplicationWindow.mouseListener);
        backupButton.addMouseListener(ApplicationWindow.mouseListener);
        //deleteButton.addMouseListener(ApplicationWindow.mouseListener);

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
                p=runtime.exec("C:/Program Files/TablePlus/cmd/dump_ms_8.0.exe -u root -pcityproject5 --add-drop-database B-bapers_db -r" + path);

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


        searchText2.addActionListener(new ActionListener() {
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
        browse2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            String user = "root";
            String pass = "cityproject5";
            String[] restoreCmd = new String[] {"C:\\Program Files\\TablePlus\\cmd\\restore_ms_8.0.exe","--user="+user,"--password="+pass,"-e","source "+path};

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
