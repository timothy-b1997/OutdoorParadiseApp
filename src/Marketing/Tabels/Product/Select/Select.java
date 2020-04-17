package Marketing.Tabels.Product.Select;

import CRUD.Menu;
import Marketing.Tabels.Product.Product;

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

    private Class productClass = Product.class;

    public Select(String title, String query) {
        super(title);
        JTable myTable = new JTable(Product.getDataModel(query));
        myTable.setPreferredScrollableViewportSize(new Dimension(600, 100));
        scrollPane.setViewportView(myTable);
        this.setContentPane(panel);
        this.setSize(1000, 800);
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
                new Menu("CRUD Menu", productClass);
            }
        });
    }
}
