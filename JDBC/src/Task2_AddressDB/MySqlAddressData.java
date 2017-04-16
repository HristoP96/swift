/*
 * To change this license header, choose License Headps in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Task2_AddressDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ickoto
 */
public class MySqlAddressData {
    
    protected static String url = "";
    
    static void setUrl(String urlSet) {
        url = urlSet;
    }
    protected static String userName = "";
    
    static void setUserName(String name) {
        userName = name;
    }
    protected static String password = "";
    
    static void setPass(String pass) {
        password = pass;
    }
    
    static void getFullAddress(int id) throws SQLException {
        
        
        try (Connection con = DriverManager.getConnection(url, userName, password);
                PreparedStatement pstmt = con.prepareStatement("SELECT addressdb.addresses.id,\n"
                        + "streets.street_name,\n"
                        + "addressdb.addresses.apartmentNo,\n"
                        + "addressdb.municipalities.municipality_name,\n"
                        + "addressdb.populated_areas.area_name,\n"
                        + "addressdb.regions.region_name,\n"
                        + "addressdb.countries.country_name\n"
                        + "FROM addressdb.addresses\n"
                        + "INNER Join addressdb.streets\n"
                        + "ON addressdb.addresses.street_id=addressdb.streets.id\n"
                        + "INNER JOIN addressdb.municipalities\n"
                        + "ON addressdb.municipalities.id = addressdb.streets.municipality_id\n"
                        + "inner join addressdb.populated_areas\n"
                        + "ON addressdb.populated_areas.id=addressdb.municipalities.populated_area_id\n"
                        + "INNER JOiN addressdb.regions\n"
                        + "ON addressdb.regions.id=addressdb.populated_areas.region_id\n"
                        + "INNER JOIN addressdb.countries\n"
                        + "ON addressdb.countries.id=addressdb.regions.country_id\n"
                        + "where addressdb.addresses.id = ?;")) {
            pstmt.setInt(1, id);
            pstmt.execute();
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    System.out.printf("%d: %s  Ap_Number: %d%n Municipality: %s Area:%s%n Region: %s Country: %s%n", rs.getInt("id"),
                            rs.getString("street_name"),
                            rs.getInt("apartmentNo"),
                            rs.getString("municipality_name"),
                            rs.getString("area_name"),
                            rs.getString("region_name"),
                            rs.getString("country_name"));
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
    
    static void addAddress(int id, int street_id, int number, int apartment) {
       
        
        try (Connection con = DriverManager.getConnection(url, userName, password);
                PreparedStatement pstmt = con.prepareStatement("insert into addresses(id,street_id,number,apartmentNo)"
                        + "VALUES (?,?,?,?)")) {
            pstmt.setInt(1, id);
            pstmt.setInt(2, street_id);
            pstmt.setInt(3, number);
            pstmt.setInt(4, apartment);
            pstmt.execute();
            System.out.println();
            getFullAddress(id);
            
        } catch (SQLException ex) {
            while (ex != null) {
                System.out.println(ex.getSQLState());
                System.out.println(ex.getMessage());
                System.out.println(ex.getErrorCode());
                ex = ex.getNextException();
            }
            
        }
    }
    
    static void getAddresses(String municipalityName) {
        
        
        try (Connection con = DriverManager.getConnection(url, userName, password);
                PreparedStatement pstmt = con.prepareStatement("SELECT addressdb.addresses.id,\n"
                        + "addressdb.addresses.street_id,\n"
                        + "addressdb.addresses.number,\n"
                        + "addressdb.addresses.apartmentNo \n"
                        + "FROM addressdb.addresses\n"
                        + "\n"
                        + "INNER join addressdb.streets\n"
                        + "ON addressdb.addresses.street_id = addressdb.streets.id\n"
                        + "INner join addressdb.municipalities\n"
                        + "On addressdb.streets.municipality_id=addressdb.municipalities.id\n"
                        + "where addressdb.municipalities.municipality_name =?")) {
            
            pstmt.setString(1, municipalityName);
            pstmt.execute();
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    System.out.printf("%d : %d :%d:%d", rs.getInt("id"),
                            rs.getInt("street_id"),
                            rs.getInt("number"),
                            rs.getInt("apartmentNo"));
                    System.out.println();
                    getFullAddress(rs.getInt("id"));
                    System.out.println();
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
