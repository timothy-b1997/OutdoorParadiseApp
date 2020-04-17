package Startup;

import javax.swing.*;

public class OutdoorParadiseApp {

    public OutdoorParadiseApp() {
        JFrame frame = new Login("Login");
        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main (String[] args) {
        new OutdoorParadiseApp();
    }
}
