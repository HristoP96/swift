/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBCExercises;

import java.sql.*;
import java.util.Scanner;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

/**
 *
 * @author ickoto
 */
public class Task0_Sakila {

    public static String addActor() {
        return "INSERT INTO sakila.actor(actor_id,first_name,last_name,last_update)"
                + "VALUES(?,?,?,?)";
    }

    public static String insertFilm_Actor() {
        return "INSERT INTO film_actor(actor_id,film_id,last_update)"
                + "VALUES(?,?,?)";
    }

    public static void main(String[] args) throws SQLException {

        Scanner sc = new Scanner(System.in);
        final String url = "jdbc:mysql://localhost:3306/sakila";
        final String userName = "root";
        System.out.println("Set pass:");

        final String password = sc.next();
        System.out.println("Set command:");
        String command = sc.next();

        switch (command) {
            case "ADDF_A":
                System.out.println("Set actor_id  film_id   timeStamp");
                try (Connection con = DriverManager.getConnection(url, userName, password);
                        PreparedStatement pstmt = con.prepareStatement(insertFilm_Actor())) {

                    pstmt.setShort(1, sc.nextShort());
                    pstmt.setShort(2, sc.nextShort());
                    Timestamp tmstmp = Timestamp.valueOf(LocalDateTime.now());
                    pstmt.setTimestamp(3, tmstmp);
                    pstmt.execute();
                    try (ResultSet rs = pstmt.executeQuery("SELECT * FROM sakila.film_actor WHERE actor_id >199")) {
                        while (rs.next()) {
                            System.out.printf("%d  %d  %s%n", rs.getShort("actor_id"),
                                    rs.getShort("film_id"),
                                    rs.getTimestamp("last_update"));
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
                break;
            case "ADDA":
                System.out.println("Set actor_id  first_name  last_name  timeStamp");
                try (Connection con = DriverManager.getConnection(url, userName, password);
                        PreparedStatement pstmt = con.prepareStatement(addActor())) {

                    pstmt.setShort(1, sc.nextShort());
                    pstmt.setString(2, sc.next());
                    pstmt.setString(3, sc.next());
                    Timestamp tmstmp = Timestamp.valueOf(LocalDateTime.now());
                    pstmt.setTimestamp(4, tmstmp);
                    pstmt.execute();
                    try (ResultSet rs = pstmt.executeQuery("SELECT * FROM sakila.actor WHERE actor_id >199")) {
                        while (rs.next()) {
                            System.out.printf("%d  %s  %s %s%n", rs.getShort("actor_id"),
                                    rs.getString("first_name"),
                                    rs.getString("last_name"),
                                    rs.getTimestamp("last_update"));
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
                break;
            case "RES":
                System.out.println("Results for: ");
                String a = sc.next();

                switch (a) {
                    case "actor":
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
                        break;
                    case "actor_film":
                        try (Connection con = DriverManager.getConnection(url, userName, password);
                                Statement stmt = con.createStatement();
                                ResultSet rs = stmt.executeQuery("SELECT * FROM sakila.film_actor WHERE actor_id >199")) {

                            while (rs.next()) {
                                System.out.printf("%d  %d  %s%n", rs.getShort("actor_id"),
                                        rs.getShort("film_id"),
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
                        break;
                }
                break;
        }
    }
}
