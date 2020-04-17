package Marketing.Tabels.Campaign;

import Marketing.Tabels.Campaign.Insert.Insert;
import Marketing.Tabels.Campaign.Select.Select;
import Marketing.Tabels.Campaign.Update.Update;
import Startup.Database;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.sql.SQLException;

public class Campaign {

    public Campaign (String actie) {
        switch(actie) {
            case "SELECT":
                new Select("Campaign", "SELECT * FROM Campaign");
                break;
            case "INSERT":
                new Insert("Campaign");
                break;
            case "UPDATE":
                new Update("Campaign");
                break;
        }
    }

    public static TableModel getDataModel(String query) {
        TableModel dataModel = null;
        try {
            Database.executeQuery(query);
            dataModel = new
                    AbstractTableModel() {
                        public int getColumnCount() {
                            return Database.getTotalColumns();
                        }

                        public String getColumnName(int columnIndex) {
                            return Database.getColumnNames(columnIndex);
                        }

                        public int getRowCount() {
                            return Database.getTotalRows();
                        }

                        public Object getValueAt(int row, int col) {
                            return Database.getData(row, col);
                        }
                    };
        } catch (SQLException err) {
            {
                System.err.println("Error connecting to the database");
                err.printStackTrace(System.err);
            }
        }
        return dataModel;
    }
}
