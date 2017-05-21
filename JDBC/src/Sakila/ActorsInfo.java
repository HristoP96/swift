package Sakila;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ActorsInfo {

    static final String url = "jdbc:mysql://localhost:3306/sakila";
    static final String userName = "root";
    static final String password = "ickotomf56";

    public static void getActorsInfo() {
        try (Connection con = DriverManager.getConnection(url, userName, password);
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM sakila.actor WHERE actor_id >199")) {

            while (rs.next()) {
                System.out.printf("%d  %s  %s %s%n", rs.getShort("actor_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getTimestamp("last_update"));
            }

        } catch (SQLException ex) {
            while (ex != null) {
                System.out.println(ex.getSQLState());
                System.out.println(ex.getMessage());
                System.out.println(ex.getErrorCode());
                ex = ex.getNextException();

            }
        }

    }
}
