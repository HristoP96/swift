/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author ickoto
 */
public class MySqlAddressStorage implements AddressStorage {

    static List<Adress> adresses = new LinkedList<Adress>();

    protected static String url = "";

    protected static void setUrl(String urlSet) {
        url = urlSet;
    }
    protected static String userName = "";

    protected static void setUserName(String name) {
        userName = name;
    }
    protected static String password = "";

    protected static void setPass(String pass) {
        password = pass;
    }

    @Override
    public Adress getAddress(int id) throws DALException {
        Adress get = null;
        try (Connection con = DriverManager.getConnection(url, userName, password);
                PreparedStatement pstmt = con.prepareStatement("SELECT * from  citizen_registrations.addresses where id=?")) {
            pstmt.setInt(1, id);
            pstmt.execute();
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    get = new Adress(rs.getString("country"),
                            rs.getString("city"),
                            rs.getString("municipality"),
                            rs.getString("postal_code"),
                            rs.getString("street"),
                            rs.getString("number"),
                            rs.getInt("floor"),
                            rs.getInt("apartmentNo"));

                }

            } catch (NullPointerException ex) {
                throw new DALException("Wrong adressID", ex);
            }

        } catch (SQLException ex) {
            while (ex != null) {
                System.out.println(ex.getSQLState());
                System.out.println(ex.getMessage());
                System.out.println(ex.getErrorCode());
                ex = ex.getNextException();
            }
        }

        return get;
    }

    @Override
    public int insertAddress(Adress address) throws DALException {

        String sqlStatement = "INSERT INTO citizen_registrations.addresses"
                + "(country,city,municipality,postal_code,street,number,floor,apartmentNo)"
                + "VALUES(?,?,?,?,?,?,?,?)";
        try (Connection con = DriverManager.getConnection(url, userName, password);
                PreparedStatement pstmt = con.prepareStatement(sqlStatement, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, address.getCountry());
            pstmt.setString(2, address.getCity());
            pstmt.setString(3, address.getMunicipality());
            pstmt.setString(4, address.getPostalCode());
            pstmt.setString(5, address.getStreet());
            pstmt.setString(6, address.getNumber());
            pstmt.setInt(7, address.getFloor());
            pstmt.setInt(8, address.getApartmentNo());
            pstmt.execute();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                while (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException ex) {
            while (ex != null) {
                System.out.println(ex.getSQLState());
                System.out.println(ex.getMessage());
                System.out.println(ex.getErrorCode());
                ex = ex.getNextException();
            }
        } catch (IllegalArgumentException ex) {
            throw new DALException("Different type required", ex);
        }

        return 0;
    }

    public static void main(String[] args) {
        final String DBMS_CONN_STRING = "jdbc:mysql://localhost:3306/citizen_registrations";
        setUrl(DBMS_CONN_STRING);
        final String DBMS_USERNAME = "root";
        setUserName(DBMS_USERNAME);
        final String DBMS_PASSWORD = "ickotomf56";
        setPass(DBMS_PASSWORD);

        try (Connection con = DriverManager.getConnection(url, userName, password);
                Statement pstmt = con.createStatement();
                ResultSet rs = pstmt.executeQuery("SELECT * FROM citizen_registrations.addresses;")) {
            while (rs.next()) {
                Adress ad = new Adress(rs.getString("country"),
                        rs.getString("city"),
                        rs.getString("municipality"),
                        rs.getString("postal_code"),
                        rs.getString("street"),
                        rs.getString("number"),
                        rs.getInt("floor"),
                        rs.getInt("apartmentNo"));

                if (adresses.isEmpty()) {
                    adresses.add(ad);
                } else {
                   

                        if (adresses.contains(ad)) {
                            System.out.println("Element is already inserted");
                            break;
                        } else {
                            adresses.add(ad);
                        }
                    
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
        System.out.println(adresses.isEmpty());
        for (Adress element : adresses) {
            System.out.println(element.toString());
            System.out.println();
        }

    }
}
