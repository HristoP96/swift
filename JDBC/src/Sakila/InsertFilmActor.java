package Sakila;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class InsertFilmActor {

    static final String url = "jdbc:mysql://localhost:3306/sakila";
    static final String userName = "root";
    static final String password = "ickotomf56";

    private static String insertFilm_Actor() {
        return "INSERT INTO film_actor(actor_id,film_id,last_update)"
                + "VALUES(?,?,?)";
    }

    public static void insert(short actorId, short filmId) {
        try (Connection con = DriverManager.getConnection(url, userName, password);
                PreparedStatement pstmt = con.prepareStatement(insertFilm_Actor())) {

            pstmt.setShort(1, actorId);
            pstmt.setShort(2, filmId);
            pstmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.execute();

            try (ResultSet rs = pstmt.executeQuery("SELECT * FROM sakila.film_actor "
                    + "join sakila.film "
                    + "on sakila.film_actor.film_id = sakila.film.film_id "
                    + "join sakila.actor "
                    + "on sakila.film_actor.actor_id = sakila.actor.actor_id "
                    + "WHERE sakila.film_actor.last_update>'2017-05-01 20:00:00';")) {
                while (rs.next()) {
                    System.out.printf("%s %s stars in the film %s.[%s]%n", rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("title"),
                            rs.getString("last_update"));
                }
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
