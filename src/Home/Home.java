package Home;

import Marketing.Marketing;
import Startup.Database;
import Startup.OutdoorParadiseApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class Home extends JFrame {

    private JButton logoutButton;
    private JPanel panel;
    private JComboBox<String> rollen;
    private JButton openButton;

    public Home(String title, ArrayList<String> items) {
        super(title);
        this.setContentPane(panel);
        this.setSize(400, 400);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        for (int i = 0; i < items.size(); i++) {
            rollen.addItem(items.get(i));
        }
        initializeListeners(this);

    }

    private void initializeListeners(JFrame frame) {
        OpenListener(frame);
        LogoutListener(frame);
    }

    private void OpenListener(JFrame frame) {
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openRol(frame, Objects.requireNonNull(rollen.getSelectedItem(), "Selecteer alstublieft een rol"));
            }
        });
    }

    private void openRol(JFrame frame, Object o) {
        frame.dispose();
        switch((String) o) {
            case "marketing":
                new Marketing("Marketing");
                break;
            case "HRM":
                System.out.println("Hier komt HRM rol");
                break;
            case "Sales":
                System.out.println("Hier komt Sales rol");
                break;
            default:
                System.out.println("Onbekend rol: " + o);
                break;
        }
    }

    private void LogoutListener(JFrame frame) {
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Database.CloseConnection(frame);
                new OutdoorParadiseApp();
            }
        });
    }
}
