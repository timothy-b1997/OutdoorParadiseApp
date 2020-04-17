package Marketing.Tabels.Product;

import Marketing.Tabels.Campaign.Campaign;
import Marketing.Tabels.Product.Select.Select;

import javax.swing.table.TableModel;

public class Product {

    public Product(String actie) {
        switch(actie) {
            case "SELECT":
                new Select("Product", "SELECT * FROM Product");
                break;
        }
    }

    public static TableModel getDataModel(String query) {
        return Campaign.getDataModel(query);
    }

}
