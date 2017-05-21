package Sakila;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ActorsFIlmInfo {

    static final String url = "jdbc:mysql://localhost:3306/sakila";
    static final String userName = "root";
    static final String password = "ickotomf56";

    public static void getInfo() {
        try (Connection con = DriverManager.getConnection(url, userName, password);
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM sakila.film_actor "
                        + "join sakila.film "
                        + "on sakila.film_actor.film_id = sakila.film.film_id "
                        + "join sakila.actor "
                        + "on sakila.film_actor.actor_id = sakila.actor.actor_id "
                        + "where sakila.actor.actor_id>190;")) {

            while (rs.next()) {
                System.out.printf("%s %s  %s  %s%n", rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("title"),
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
