package Marketing.Tabels.Campaign.Update;

import CRUD.Menu;
import Marketing.Tabels.Campaign.Campaign;
import Startup.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Update extends JFrame {

    private JPanel panel;
    private JTable table1;
    private JLabel text;
    private JScrollPane scrollPane;
    private JButton terug;
    private JPanel secondPanel;
    private JTextField input_product_id;
    private JTextField input_promotion_id;
    private JTextField input_discount;
    private JButton updateButton;

    private Class campaignClass = Campaign.class;
    private JTable Table;

    private String query = "SELECT * FROM Campaign";

    public Update(String title) {
        super(title);

        Table = new JTable(Campaign.getDataModel(query));
        Table.setPreferredScrollableViewportSize(new Dimension(400, 100));
        Table.setRowSelectionInterval(0, 0);
        scrollPane.setViewportView(Table);

        input_product_id.setText((String) Table.getValueAt(0, 0));
        input_promotion_id.setText((String) Table.getValueAt(0, 1));
        input_discount.setText((String) Table.getValueAt(0, 2));

        this.setContentPane(panel);
        this.setSize(600, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initialize(this);
    }

    private void initialize(JFrame frame) {
        Mouse();
        UpdateListener();
        TerugListener(frame);
    }

    private void Mouse() {
        Table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = Table.getSelectedRow();
                input_product_id.setText((String) Table.getValueAt(row, 0));
                input_promotion_id.setText((String) Table.getValueAt(row, 1));
                input_discount.setText((String) Table.getValueAt(row, 2));
            }
        });
    }

    private void UpdateListener() {
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String update_query = "UPDATE CAMPAIGN \n" +
                            "SET discount = " + Float.parseFloat(input_discount.getText()) + "\n" +
                            "WHERE product_id = " + Integer.parseInt(input_product_id.getText()) + " " +
                            "AND promotion_id = " + Integer.parseInt(input_promotion_id.getText()) + "";
                    Database.updateQuery(update_query);
                } catch (SQLException err) {
                    {
                        System.err.println("Error connecting to the database");
                        err.printStackTrace(System.err);
                    }
                }
                JTable newTable = new JTable(Campaign.getDataModel(query));
                newTable.setPreferredScrollableViewportSize(new Dimension(400, 100));
                newTable.setRowSelectionInterval(Table.getSelectedRow(), Table.getSelectedRow());
                scrollPane.setViewportView(newTable);

                Table = newTable;
                Mouse();
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
