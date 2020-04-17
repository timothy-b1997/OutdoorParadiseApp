package Marketing.Tabels.Promotion.Update;

import CRUD.Menu;
import Marketing.Tabels.Campaign.Campaign;
import Marketing.Tabels.Promotion.Promotion;
import Startup.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Update extends JFrame {

    private JPanel panel;
    private JTable table1;
    private JLabel text;
    private JScrollPane scrollPane;
    private JButton terug;
    private JPanel secondPanel;
    private JTextField input_promotion_id;
    private JTextField input_date_start;
    private JTextField input_date_end;
    private JButton updateButton;
    private JTextField input_description;

    private Class promotionClass = Promotion.class;
    private JTable Table;

    private String query = "SELECT * FROM Promotion";

    public Update(String title) {
        super(title);

        Table = new JTable(Promotion.getDataModel(query));
        Table.setPreferredScrollableViewportSize(new Dimension(400, 100));
        Table.setRowSelectionInterval(0, 0);
        scrollPane.setViewportView(Table);

        input_promotion_id.setText((String) Table.getValueAt(0, 0));
        input_date_start.setText((String) Table.getValueAt(0, 1));
        input_date_end.setText((String) Table.getValueAt(0, 2));
        input_description.setText((String) Table.getValueAt(0, 3));

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
                input_promotion_id.setText((String) Table.getValueAt(row, 0));
                input_date_start.setText((String) Table.getValueAt(row, 1));
                input_date_end.setText((String) Table.getValueAt(row, 2));
                input_description.setText((String) Table.getValueAt(row, 3));
            }
        });
    }

    private void UpdateListener() {
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Date date_start = Promotion.StringToDate(input_date_start.getText());
                    Date date_end = Promotion.StringToDate(input_date_end.getText());

                    String update_query = "UPDATE PROMOTION \n" +
                            "SET date_start = '" + new SimpleDateFormat("yyyy-MM-dd").format(date_start) + "', " +
                            "date_end = '" + new SimpleDateFormat("yyyy-MM-dd").format(date_end) + "', " +
                            "description = '" + input_description.getText() + "' \n" +
                            "WHERE promotion_id = " + Integer.parseInt(input_promotion_id.getText());
                    Database.updateQuery(update_query);
                } catch (SQLException  | ParseException err) {
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
                new Menu("CRUD Menu", promotionClass);
            }
        });
    }
}
