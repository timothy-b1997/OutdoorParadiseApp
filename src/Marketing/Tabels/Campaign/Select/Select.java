package Marketing.Tabels.Campaign.Select;

import CRUD.Menu;
import Marketing.Tabels.Campaign.Campaign;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Select extends JFrame {

    private JPanel panel;
    private JScrollPane scrollPane;
    private JTextField textField1;
    private JButton button1;
    private JButton terug;
    private JPanel inputPanel;
    private JLabel text;
    private JButton executeSQLCommandButton;
    private JTable table1;

    private Class campaignClass = Campaign.class;

    public Select(String title, String query) {
        super(title);
        JTable myTable = new JTable(Campaign.getDataModel(query));
        myTable.setPreferredScrollableViewportSize(new Dimension(400, 100));
        scrollPane.setViewportView(myTable);
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

    private void ExecuteListener(JFrame frame) {
        executeSQLCommandButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ExecuteCommand(frame, "Execute");
            }
        });
    }

    private void TerugListener(JFrame frame) {
        terug.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Menu("CRUD Menu", campaignClass);
            }
        });
    }
}
