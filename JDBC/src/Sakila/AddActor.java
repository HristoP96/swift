package Sakila;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class AddActor {

    static final String url = "jdbc:mysql://localhost:3306/sakila";
    static final String userName = "root";
    static final String password = "ickotomf56";

    private static String add() {
        return "INSERT INTO sakila.actor(first_name, last_name, last_update)"
                + "VALUES(?,?,?)";
    }

    public static short addActor(String first_name, String last_name) {
        short id = 0;
        try (Connection con = DriverManager.getConnection(url, userName, password);
                PreparedStatement pstmt = con.prepareStatement(add())) {

            pstmt.setString(1, first_name);
            pstmt.setString(2, last_name);
            pstmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.execute();

            try (ResultSet rs = pstmt.executeQuery("SELECT LAST_INSERT_ID();")) {
                rs.next();
                id = rs.getShort(1);
                System.out.println(id);

            }

        } catch (SQLException ex) {
            while (ex != null) {
                System.out.println(ex.getSQLState());
                System.out.println(ex.getMessage());
                System.out.println(ex.getErrorCode());
                ex = ex.getNextException();
            }

        }

        return id;

    }
}
