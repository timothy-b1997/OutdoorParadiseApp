package Marketing.Tabels.Campaign.Select;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExecuteCommand extends JFrame {
    private JTextArea query;
    private JPanel panel;
    private JButton executeButton;
    private JButton terugButton;
    private JFrame mainFrame;

    public ExecuteCommand(JFrame mainFrame, String title) {
        super(title);
        this.mainFrame = mainFrame;
        this.setContentPane(panel);
        this.setSize(400, 400);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initialize(this);
    }

    private void initialize(JFrame frame) {
        ExecuteListener(frame);
        TerugListener(frame);
    }

    private boolean CheckQuery(String query) {
        if (query.toUpperCase().startsWith("SELECT") &&
            query.toUpperCase().contains("FROM CAMPAIGN")) {
            return true;
        }
        System.out.println("De query hoort niet bij de juiste tabel of is geen select statement!");
        return false;
    }

    private void ExecuteListener(JFrame frame) {
        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(CheckQuery(query.getText())) {
                    frame.dispose();
                    mainFrame.dispose();
                    new Select("Campaign", query.getText());
                }
            }
        });
    }

    private void TerugListener(JFrame frame) {
        terugButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }
}
