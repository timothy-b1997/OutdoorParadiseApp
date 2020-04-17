package Startup;

import Startup.Database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {

    private JPanel panel1;
    private JButton loginButton;
    private JTextField username;
    private JPasswordField password;

    public Login (String title) {
        super(title);
        this.setContentPane(panel1);
        CreateConnection(this);
    }

    public void CreateConnection(JFrame frame) {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Database(frame, "jdbc:sqlserver://localhost", username.getText(), String.valueOf(password.getPassword()));
                System.out.println("Username: " + username.getText() + " Password: " + String.valueOf(password.getPassword()));
            }
        });
    }

}
