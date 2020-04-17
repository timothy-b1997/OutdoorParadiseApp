package Marketing;

import CRUD.Menu;
import Marketing.Tabels.Campaign.Campaign;
import Marketing.Tabels.Campaign.Select.Select;
import Home.Home;
import Marketing.Tabels.Product.Product;
import Marketing.Tabels.Promotion.Promotion;
import Startup.Database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Marketing extends JFrame {

    private JPanel panel;
    private JButton campaignButton;
    private JButton promotionButton;
    private JButton terugButton;
    private JButton productButton;

    public Marketing(String title) {
        super(title);
        this.setContentPane(panel);
        this.setSize(400, 400);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initialize(this);
    }

    public void initialize(JFrame frame) {
        Campaign(frame);
        Promotion(frame);
        Product(frame);
        Terug(frame);
    }

    public void Campaign(JFrame frame) {
        Class campaignClass = Campaign.class;
        campaignButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Menu("CRUD Menu", campaignClass);
            }
        });
    }

    public void Promotion(JFrame frame) {
        Class promotionClass = Promotion.class;
        promotionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Menu("CRUD Menu", promotionClass);
            }
        });
    }

    public void Product(JFrame frame) {
        Class productClass = Product.class;
        productButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Menu("CRUD Menu", productClass);
            }
        });
    }

    public void Terug(JFrame frame) {
        terugButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Home("Home", Database.getRollen());
            }
        });
    }
}
