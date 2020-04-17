package CRUD;

import Marketing.Marketing;
import Startup.Database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class Menu extends JFrame {

    private JButton terugButton;
    private JPanel mainPanel;
    private JButton selectButton;
    private JButton insertButton;
    private JButton updateButton;
    private JButton deleteButton;
    private String table;
    private Class<?> className;

    public Menu(String title, Class className) {
        super(title);
        this.setContentPane(mainPanel);
        this.setSize(400, 400);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.table = className.getSimpleName();
        this.className = className;
        initialize(this);
    }

    private boolean checkPermission(String table, String permission) {
        for (int i = 0; i < Database.getTotalRows(); i++) {
            for (int j = 0; j < Database.getTotalColumns(); j++) {
                if (Database.getData(i, j).equals(table) && Database.getData(i, j + 1).equals(permission)) {
                    System.out.println("Found match: Column name: " + j + " Value: " + Database.getData(i, j + 1));
                    return true;
                }
            }
        }
        JOptionPane.showMessageDialog(this,
                "Je hebt geen toegang tot deze actie!",
                "Toegangsfout",
                JOptionPane.ERROR_MESSAGE);
        return false;
    }

    private void checkGrants(String table, String grant) {
        String query = "SELECT T.TABLE_NAME AS [OBJECT_NAME], P.PERMISSION_NAME \n" +
                "    FROM INFORMATION_SCHEMA.TABLES T\n" +
                "    CROSS APPLY fn_my_permissions(T.TABLE_SCHEMA + '.' + T.TABLE_NAME, 'OBJECT') P\n" +
                "    WHERE P.subentity_name = ''\n" +
                "UNION\n" +
                "SELECT R.ROUTINE_NAME AS [OBJECT_NAME], P.PERMISSION_NAME\n" +
                "    FROM INFORMATION_SCHEMA.ROUTINES R\n" +
                "    CROSS APPLY fn_my_permissions(R.ROUTINE_SCHEMA + '.' + R.ROUTINE_NAME, 'OBJECT') P\n" +
                "ORDER BY [OBJECT_NAME], P.PERMISSION_NAME";
        try {
            Database.executeQuery(query);
            if(checkPermission(table, grant)) {
                Constructor constructor = className.getConstructor(String.class);
                this.dispose();
                constructor.newInstance(grant);
            }
        } catch (SQLException err) {
            {
                System.err.println("Error connecting to the database");
                err.printStackTrace(System.err);
            }
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void initialize (JFrame frame) {
        SelectListener(frame);
        InsertListener(frame);
        UpdateListener(frame);
        DeleteListener(frame);
        TerugListener(frame);
    }

    private void SelectListener(JFrame frame) {
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGrants(table, "SELECT");
            }
        });
    }

    private void InsertListener(JFrame frame) {
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGrants(table, "INSERT");
            }
        });
    }

    private void UpdateListener(JFrame frame) {
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGrants(table, "UPDATE");
            }
        });
    }

    private void DeleteListener(JFrame frame) {
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGrants(table, "DELETE");
            }
        });
    }

    private void TerugListener(JFrame frame) {
        terugButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Marketing("Marketing");
            }
        });
    }

}
