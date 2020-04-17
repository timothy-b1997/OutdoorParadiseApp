package Marketing.Tabels.Campaign.Insert;

import CRUD.Menu;
import Marketing.Tabels.Campaign.Campaign;
import Startup.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Insert extends JFrame {

    private JPanel panel;
    private JTable table1;
    private JLabel text;
    private JScrollPane scrollPane;
    private JButton terug;
    private JPanel secondPanel;
    private JTextField input_product_id;
    private JTextField input_promotion_id;
    private JTextField input_discount;
    private JButton InsertButton;

    private Class campaignClass = Campaign.class;
    private JTable Table;

    private String query = "SELECT * FROM Campaign";

    public Insert(String title) {
        super(title);

        Table = new JTable(Campaign.getDataModel(query));
        Table.setPreferredScrollableViewportSize(new Dimension(400, 100));
        scrollPane.setViewportView(Table);

        this.setContentPane(panel);
        this.setSize(600, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initialize(this);
    }

    private void initialize(JFrame frame) {
        InsertListener();
        TerugListener(frame);
    }

    private void InsertListener() {
        InsertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String insert_query = "EXEC NewCampaign " + Integer.parseInt(input_product_id.getText()) + ", " + Integer.parseInt(input_promotion_id.getText()) + ", " +  Float.parseFloat(input_discount.getText());
                    Database.executeQuery(insert_query);
                } catch (SQLException err) {
                    {
                        System.err.println("Error connecting to the database");
                        err.printStackTrace(System.err);
                    }
                }
                Table = new JTable(Campaign.getDataModel(query));
                Table.setPreferredScrollableViewportSize(new Dimension(400, 100));
                scrollPane.setViewportView(Table);
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
