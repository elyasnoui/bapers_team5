package System;

import javax.swing.*;
import GUI.*;

import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDate;

public class Bapers {
    private ApplicationWindow applicationWindow = new ApplicationWindow("Bapers Team 5");
    private Login login;
    private Staff staff;
    private Customer customer;
    private UpdateProfile UpdateProfile;
    private Job Job;
    public String currentPanel = "Login";  // This is 'Login' by default.

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

        //login = new Login();
        //applicationWindow.add(login.getMainPanel(), BorderLayout.CENTER);

  //      staff = new Staff();
  //      applicationWindow.add(staff.getMainPanel());

 //       customer = new Customer();
//        applicationWindow.add(customer.getPanel());

      UpdateProfile = new UpdateProfile();
        applicationWindow.add(UpdateProfile.getMainPanel());

//        Job = new Job();
//        applicationWindow.add(Job.getMainPanel());

        applicationWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        applicationWindow.pack();
        applicationWindow.setVisible(true);
        applicationWindow.setSize(1280, 720);
        applicationWindow.setResizable(false);
        applicationWindow.setFocusable(true);
        applicationWindow.setFocusTraversalKeysEnabled(false);
        applicationWindow.setIconImage(null);

        /* debug
        try {
            //boolean db_cust = DatabaseConnection.addCustomer("test", "test", "test", "test", "test");
            //boolean db_valuedCust = DatabaseConnection.addValuedCustomer(13, "Fixed Discount", "0%");
            //boolean db_staff = DatabaseConnection.addStaff("test", "test", "test", "test", "test",
            //        "test", 0, "test", "test", "test", "test");
            boolean db_job = DatabaseConnection.addJob((byte) 0, 1.00, LocalDate.now(), LocalDate.of(2021, 10, 9),
                    LocalDate.of(2021, 10, 10), "confirmed", 13);
            System.out.println(db_job);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
         */

    }

    public static void main(String[] args) {
        System.out.println("Team 5");

        new Bapers();
    }
}
