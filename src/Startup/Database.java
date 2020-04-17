package Startup;

import Home.Home;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class Database {

    private String jdbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static Connection con;
    private static Statement statement;

    public Database(JFrame frame, String url, String username, String password) {
        try {
            Class.forName(jdbcDriver).newInstance();
            System.out.println("JDBC driver loaded");
            Connect(frame, url, username, password);
        } catch (Exception err) {
            System.err.println("Error loading JDBC driver");
            err.printStackTrace(System.err);
        }
    }

    private void Connect(JFrame frame, String url, String username, String password) {
        try {
            con = DriverManager.getConnection(url, username, password);
            statement = con.createStatement();
            System.out.println("Connected to the database");

            frame.dispose();
            new Home("Home", getRollen(username));
        } catch (SQLException err) {
            {
                System.err.println("Error connecting to the database");
                err.printStackTrace(System.err);
            }
        }
    }

    public static void CloseConnection(JFrame frame) {
        con = null;
        statement = null;
        rollen.clear();
        frame.dispose();
    }

    private static ArrayList<String> rollen = new ArrayList<>();

    private ArrayList<String> getRollen(String username) {
        try {
            executeQuery("" +
                    "SELECT DP1.name AS DatabaseRoleName   \n" +
                    " FROM sys.database_role_members AS DRM  \n" +
                    " RIGHT OUTER JOIN sys.database_principals AS DP1  \n" +
                    "   ON DRM.role_principal_id = DP1.principal_id  \n" +
                    " LEFT OUTER JOIN sys.database_principals AS DP2  \n" +
                    "   ON DRM.member_principal_id = DP2.principal_id  \n" +
                    "WHERE DP1.type = 'R' AND DP2.name = '" + username + "'\n" +
                    "ORDER BY DP1.name;  ");
            for (Object o : rows) {
                rollen.add((String) o);
            }
        } catch (SQLException err) {
            {
                System.err.println("Error connecting to the database");
                err.printStackTrace(System.err);
            }
        }
        return rollen;
    }

    public static ArrayList<String> getRollen() {
        return rollen;
    }

    private static ArrayList<String> columns = new ArrayList<>();
    private static ArrayList<Object> rows = new ArrayList<>();

    public static void updateQuery(String query) throws SQLException {
        statement.executeUpdate(query);
    }

    public static void executeQuery(String query) throws SQLException {
        ResultSet resultSet = statement.executeQuery(query);
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int totalColumns = rsmd.getColumnCount();

        if (!columns.isEmpty()) columns.clear();
        if (!rows.isEmpty()) rows.clear();
        while (resultSet.next()) {
            for (int i = 1; i <= totalColumns; i++) {
                if (columns.size() < totalColumns) {
                    columns.add(rsmd.getColumnName(i));
                }
                if (i > 1) System.out.print(",  ");
                String columnValue = resultSet.getString(i);
                System.out.print(columnValue + " " + rsmd.getColumnName(i));
                rows.add(resultSet.getString(i));
            }
            System.out.println("");
        }
    }

    public static Object getData(int row, int col) {
        int index = col + (row * getTotalColumns());
        return rows.get(index);
    }

    /**
     * SELECT * FROM TABLE;
     */
    public static void getData() {
        int index = 0;
        while(index < rows.size()) {
            for (int j = 0; j < columns.size(); j++) {
                System.out.println("Column name: " + j + " row: " + rows.get(index));
                index++;
            }
        }
    }

    public static int getTotalRows() {
        return rows.size() / getTotalColumns();
    }

    public static int getTotalColumns() {
        return columns.size();
    }

    public static String getColumnNames(int column) {
        return columns.get(column);
    }
}
