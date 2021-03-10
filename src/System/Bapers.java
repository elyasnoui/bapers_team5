package System;

import javax.swing.*;
import GUI.*;

public class Bapers {
    private ApplicationWindow applicationWindow = new ApplicationWindow("Bapers Team 5");

    // TODO: Take out declaration when it no longer needs testing
    private Login login = new Login(this);
    private Staff staff = new Staff(this);
    private Customer customer = new Customer(this);
    private UpdateProfile updateProfile = new UpdateProfile(this);
    private Job job = new Job(this);
    private Report report = new Report(this);
    private Task task = new Task(this);
    private Payment payment = new Payment(this);

    public Bapers() {
        /*
        TODO: IF YOU WANT TO TEST YOUR PANEL, COMMENT OUT THE OTHER PANELS AND LEAVE YOURS.

        For example, Asad would do this to view his Staff panel:

        // login = new Login();
        // applicationWindow.add(login.getPanel());

        staff = new Staff();
        applicationWindow.add(staff.getPanel());

        customer = new Customer();
        applicationWindow.add(customer.getPanel());

        Also make sure you create getters and setters inside your GUI class for the panels, otherwise you
        wont be able to add it to the applicationWindow.

        For now, the system's size will be 1280x720, we'll work towards making it resizeable towards the end of
        the project.

        Before pushing, make sure you have Login uncommented and whatever you was working on commented
        so we can have the program default to the login screen.
         */

        applicationWindow.add(login.getMainPanel());

        //applicationWindow.add(report.getMainPanel());

        //applicationWindow.add(staff.getMainPanel());

        //applicationWindow.add(customer.getPanel());

        //applicationWindow.add(updateProfile.getMainPanel());

        //applicationWindow.add(job.getMainPanel());

        //applicationWindow.add(task.getMainPanel());

        //applicationWindow.add(payment.getMainPanel());

        applicationWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        applicationWindow.pack();
        applicationWindow.setVisible(true);
        applicationWindow.setSize(1290, 720);
        applicationWindow.setResizable(false);
        applicationWindow.setFocusable(true);
        applicationWindow.setFocusTraversalKeysEnabled(false);
        applicationWindow.setIconImage(null);

        /*
        try {
            //boolean db_customer = DatabaseConnection.addCustomer("test2", "test2", "test2",
            //        "test2", "test2");
            //boolean db_valuedCustomer = DatabaseConnection.addValuedCustomer(13, "Fixed Discount", "0%");
            //boolean db_staff = DatabaseConnection.addStaff("test", "test", "test", "test", "test",
            //        "test", 0, "test", "test", "test", "test");
            //boolean db_job = DatabaseConnection.addJob(0, 1.00, LocalDate.now(), LocalDate.of(2021, 10, 9),
            //        LocalDate.of(2021, 10, 10), "confirmed", 13);
            //boolean db_task = DatabaseConnection.addTask(1, 9, "test1", "test2",
            //        "test3", 1.00, 0, 12);
            //boolean db_payment = DatabaseConnection.addPayment(9, 1.00, 1, 0.00,
            //        "card", 13, 12);
            //boolean db_card = DatabaseConnection.addCard(9, "test", "test2", 1092);
            //boolean db_cash = DatabaseConnection.addCash(9, 1.00, 0.00);
            //boolean db_report = DatabaseConnection.addReport("test", "test2", LocalDate.now(),
            //        LocalDate.of(2021, 10, 4));
            //boolean db_performanceReport = DatabaseConnection.addPerformanceReport(4, 1);
            //boolean db_summaryReport = DatabaseConnection.addSummaryReport(4, 1);
            //boolean db_jobReport = DatabaseConnection.addJobReport(4, 1);
            //boolean db_staffPerformanceReport = DatabaseConnection.addStaffPerformanceReport(4, 12, 1, 9);
            //boolean db_taskSummaryReport = DatabaseConnection.addTaskSummaryReport(4, 1, 9);
            //boolean db_jobJobReport = DatabaseConnection.addJobJobReport(4, 9, 1, 12);

            //boolean db_removeCustomer = DatabaseConnection.removeCustomer(15);

            boolean db_editCustomer = DatabaseConnection.editCustomer(13, "test_a", "test_a",
                    "test_a", "test_a", "test_a");

            System.out.println(db_editCustomer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
         */
    }

    public void updateUserDetails() {
        customer.setUsername(ApplicationWindow.username);
        customer.setRole(ApplicationWindow.role);

        staff.setUsername(ApplicationWindow.username);
        staff.setRole(ApplicationWindow.role);

        job.setUsername(ApplicationWindow.username);
        job.setRole(ApplicationWindow.role);

        task.setUsername(ApplicationWindow.username);
        task.setRole(ApplicationWindow.role);

        payment.setUsername(ApplicationWindow.username);
        payment.setRole(ApplicationWindow.role);

        report.setUsername(ApplicationWindow.username);
        report.setRole(ApplicationWindow.role);

        updateProfile.setUsername(ApplicationWindow.username);
        updateProfile.setRole(ApplicationWindow.role);
    }

    public void changeScreen(final String destination, final JPanel panel) {
        applicationWindow.remove(panel);
        switch (destination) {
            case "logout":
                login = new Login(this);
                applicationWindow.add(login.getMainPanel());
                break;
            case "jobs":
                job = new Job(this);
                applicationWindow.add(job.getMainPanel());
                break;
            case "customers":
                customer = new Customer(this);
                applicationWindow.add(customer.getPanel());
                break;
            case "payments":
                payment = new Payment(this);
                applicationWindow.add(payment.getMainPanel());
                break;
            case "staff":
                staff = new Staff(this);
                applicationWindow.add(staff.getMainPanel());
                break;
            case "tasks":
                task = new Task(this);
                applicationWindow.add(task.getMainPanel());
                break;
            case "reports":
                report = new Report(this);
                applicationWindow.add(report.getMainPanel());
                break;
            case "database":
                //database = new Database(this);
                //applicationWindow.add()
                break;
            default:
                changeScreen("logout", panel);
                break;
        }
        updateUserDetails();
        applicationWindow.setVisible(true);
    }

    public static void main(String[] args) {
        System.out.println("Team 5");

        new Bapers();
    }
}
