package Marketing.Tabels.Promotion.Insert;

import CRUD.Menu;
import Marketing.Tabels.Promotion.Promotion;
import Startup.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Insert extends JFrame {

    private JPanel panel;
    private JTable table1;
    private JLabel text;
    private JScrollPane scrollPane;
    private JButton terug;
    private JPanel secondPanel;
    private JTextField input_promotion_id;
    private JTextField input_date_start;
    private JTextField input_date_end;
    private JTextField input_description;
    private JButton InsertButton;

    private Class promotionClass = Promotion.class;
    private JTable Table;

    private String query = "SELECT * FROM Promotion";

    public Insert(String title) {
        super(title);

        Table = new JTable(Promotion.getDataModel(query));
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
                    Date date_start = Promotion.StringToDate(input_date_start.getText());
                    Date date_end = Promotion.StringToDate(input_date_end.getText());

                    String insert_query = "EXEC NewPromotion " + Integer.parseInt(input_promotion_id.getText()) + ", '" + new SimpleDateFormat("yyyy-MM-dd").format(date_start) + "', '" + new SimpleDateFormat("yyyy-MM-dd").format(date_end) +"', '" +  input_description.getText() + "'";
                    Database.executeQuery(insert_query);
                } catch (SQLException | ParseException err) {
                    {
                        System.err.println("Error connecting to the database");
                        err.printStackTrace(System.err);
                    }
                }
                Table = new JTable(Promotion.getDataModel(query));
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
                new Menu("CRUD Menu", promotionClass);
            }
        });
    }
}
