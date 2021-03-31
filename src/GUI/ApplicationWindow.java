package GUI;

import System.DatabaseConnection;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ApplicationWindow extends JFrame {
    public static String username;
    public static String role;
    private List<String[]> jobData;

    public static final String companyRegex = "[a-zA-Z0-9 )(,-]{0,35}?";
    public static final String nameRegex = "[A-Z]{1}[a-zA-z-]{1,34}";
    public static final String contactNumberRegex = "[0][1-9][0-9]{9}";
    public static final String addressLineRegex = "[A-Za-z0-9 ]{1,35}?";
    public static final String cityRegex = "[A-Za-z ]{1,20}?";
    public static final String postcodeRegex = "(([A-Z][0-9]{1,2})|(([A-Z][A-HJ-Y][0-9]{1,2})|(([A-Z][0-9][A-Z])|([A-Z][A-HJ-Y][0-9]?[A-Z])))) [0-9][A-Z]{2}";
    public static final String emailRegex = "([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})";
    public static final String discountRegex = "[0-9]?$|^100";
    public static final String usernameRegex = "[a-zA-Z0-9]{5,15}?";
    public static final String passwordRegex = "(?=.*[a-z])(?=.*[A-Z])[A-Za-z\\d_@$!%*?&]{6,15}";
    public static final LineBorder borderError = new LineBorder(Color.RED, 1);

    public ApplicationWindow(String title) {
        super(title);

        ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
        exec.scheduleAtFixedRate(deadlineChecker, 0, 15, TimeUnit.MINUTES);
    }

    Runnable deadlineChecker = new Runnable() {
        public void run() {
            try {
                jobData = DatabaseConnection.getData("job");
                assert jobData != null;
                for (String[] js : jobData) {
                    switch (js[6]) {
                        case "Created":
                            // Job completion alert (office/shift)
                            LocalDateTime now = LocalDateTime.now();
                            LocalDateTime deadline = LocalDateTime.of(
                                    Integer.parseInt(js[5].substring(0,4)),      //year
                                    Integer.parseInt(js[5].substring(5,7)),      //month
                                    Integer.parseInt(js[5].substring(8,10)),     //day
                                    Integer.parseInt(js[5].substring(11,13)),    //hour
                                    Integer.parseInt(js[5].substring(14,16))     //minute
                            );

                            //if (deadline.isAfter(now)) System.out.println("Technician Alert");

                            break;
                        case "Unpaid":
                            // Payment for job alert (office/shift)

                            break;
                    }
                }
            } catch (Exception e) { e.printStackTrace(); }
        }
    };

    public static void displayTable(final JTable table, final List<String[]> records, final String[] tableColumns) {
        table.setModel(new DefaultTableModel(records.toArray(new Object[][] {}), tableColumns));
        table.setDefaultEditor(Object.class, null);
        TableColumnModel columns = table.getColumnModel();
        //columns.getColumn(2).setMinWidth(100);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        //columns.getColumn(0).setCellRenderer(centerRenderer);
        //columns.getColumn(3).setCellRenderer(centerRenderer);
        //columns.getColumn(5).setCellRenderer(centerRenderer);
        //columns.getColumn(6).setCellRenderer(centerRenderer);

        //columns.getColumn(0).setMinWidth(30);
        //columns.getColumn(1).setMinWidth(30);
        //columns.getColumn(2).setMinWidth(30);

        //columns.getColumn(0).setPreferredWidth(10);
    }

    public static String[] checkPrivileges() {


        return null;
    }

    public static MouseListener highlightListener = new MouseAdapter()
    {
        public void mouseEntered(java.awt.event.MouseEvent evt)
        {
            Component c = evt.getComponent();

            if (c.getBackground().equals(new Color(124, 134, 175))) {
                c.setBackground(new Color(176, 191, 241));
                return;
            }

            c.setBackground(new Color(124, 134, 175));
        }

        public void mouseExited(java.awt.event.MouseEvent evt)
        {
            Component c = evt.getComponent();

            if (c.getBackground().equals(new Color(176, 191, 241))) {
                c.setBackground(new Color(124, 134, 175));
                return;
            }

            c.setBackground(new Color(76, 84, 118));
        }
    };

    public static KeyAdapter regexListener = new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getComponent() instanceof JTextField) {
                boolean valid = false;
                switch (e.getComponent().getName()) {
                    case "company":
                        if (((JTextField) e.getComponent()).getText().matches(companyRegex)) {
                            ((JTextField) e.getComponent()).setToolTipText("Please enter a valid name, e.g. 'John'");
                            valid = true;
                        } else valid = false;
                        break;
                    case "name":
                        if (((JTextField) e.getComponent()).getText().matches(nameRegex)) {
                            ((JTextField) e.getComponent()).setToolTipText("Please enter a valid name, e.g. 'John'");
                            valid = true;
                        } else valid = false;
                        break;
                    case "contactNumber":
                        if (((JTextField) e.getComponent()).getText().matches(contactNumberRegex)) {
                            ((JTextField) e.getComponent()).setToolTipText("Please enter a valid UK number (11 digits)");
                            valid = true;
                        } else valid = false;
                        break;
                    case "addressLine": case "addressLineOpt":
                        if (((JTextField) e.getComponent()).getText().matches(addressLineRegex)) {
                            ((JTextField) e.getComponent()).setToolTipText("Please enter only letters and numbers");
                            valid = true;
                        } else valid = false;
                        break;
                    case "city":
                        if (((JTextField) e.getComponent()).getText().matches(cityRegex)) {
                            ((JTextField) e.getComponent()).setToolTipText("Please enter only letters");
                            valid = true;
                        } else valid = false;
                        break;
                    case "postcode":
                        if (((JTextField) e.getComponent()).getText().matches(postcodeRegex)) {
                            ((JTextField) e.getComponent()).setToolTipText("Please enter a valid UK postcode");
                            valid = true;
                        } else valid = false;
                        break;
                    case "email":
                        if (((JTextField) e.getComponent()).getText().matches(emailRegex)) {
                            ((JTextField) e.getComponent()).setToolTipText("Please enter in email format");
                            valid = true;
                        } else valid = false;
                        break;
                    case "discount":
                        if (((JTextField) e.getComponent()).getText().matches(discountRegex)) {
                            ((JTextField) e.getComponent()).setToolTipText("Please enter only digits (0,100)");
                            valid = true;
                        } else valid = false;
                        break;
                } if (valid || ((e.getComponent().getName().equals("addressLineOpt") || e.getComponent().getName().equals("company"))
                        && ((JTextField) e.getComponent()).getText().isEmpty())) {
                    ((JTextField) e.getComponent()).setBorder(null);
                    ((JTextField) e.getComponent()).setToolTipText(null);
                } else ((JTextField) e.getComponent()).setBorder(borderError);
            }
        }
    };
}
