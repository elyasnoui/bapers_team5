import javax.swing.*;
import GUI.*;

public class Bapers {
    String Elyas() {
        return "Elyas";
    }

    public static void main(String[] args) {
        System.out.println("Team 5");

        JFrame frame = new JFrame("App");
        frame.setContentPane(new Login().getPanel1());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(300,200);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    int asad() {
        return(1);
    }

    int sunil() {
        return(2);
    }

    int efte() {
        return(5);
    }
}
