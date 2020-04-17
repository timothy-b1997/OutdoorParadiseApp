package Marketing.Tabels.Promotion;

import Marketing.Tabels.Campaign.Campaign;
import Marketing.Tabels.Promotion.Insert.Insert;
import Marketing.Tabels.Promotion.Select.Select;
import Marketing.Tabels.Promotion.Update.Update;

import javax.swing.table.TableModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Promotion {

    public Promotion (String actie) {
        switch(actie) {
            case "SELECT":
                new Select("Promotion", "SELECT * FROM Promotion");
                break;
            case "INSERT":
                new Insert("Promotion");
                break;
            case "UPDATE":
                new Update("Promotion");
                break;
        }
    }

    public static TableModel getDataModel(String query) {
        return Campaign.getDataModel(query);
    }

    public static Date StringToDate(String dob) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(dob);
        return date;
    }
}
