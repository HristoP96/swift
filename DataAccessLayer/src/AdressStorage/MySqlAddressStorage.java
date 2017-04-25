/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdressStorage;

import DALException.DALException;
import address.Address;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ickoto
 */
public class MySqlAddressStorage implements AddressStorage {

    private final String _dbConnectionString;
    private final String _dbUsername;
    private final String _dbPassword;
    private String getStatement = "SELECT * FROM citizen_registrations.addresses where id =?";
    private String insertStatement = "INSERT INTO citizen_registrations.addresses (`country`,`city`,`municipality`,`postal_code`,`street`,`number`,`floor`,`apartmentNo`) "
            + "VALUES (?, ?, ?, ?, ?, ?,?,?);";
    private String removeStatement = "DELETE FROM citizen_registrations.addresses where id =?";

    public MySqlAddressStorage(String dbConnectionString, String dbUsername, String dbPassword) {
        _dbConnectionString = dbConnectionString;
        _dbUsername = dbUsername;
        _dbPassword = dbPassword;
    }

    @Override
    public Address getAddress(int id) throws DALException {
        try (Connection conn = DriverManager.getConnection(_dbConnectionString, _dbUsername, _dbPassword);
                PreparedStatement pstmt = conn.prepareStatement(getStatement)) {
            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                rs.next();
                Integer floor = rs.getInt("floor");
                Integer apart = rs.getInt("apartmentNo");
                if (!(floor==null && apart==null)) {
                    return new Address(rs.getString("country"),
                            rs.getString("city"),
                            rs.getString("municipality"),
                            rs.getString("postal_code"),
                            rs.getString("street"),
                            rs.getString("number"),
                            rs.getInt("floor"),
                            rs.getInt("apartmentNo"));
                }
                return new Address(rs.getString("country"),
                        rs.getString("city"),
                        rs.getString("municipality"),
                        rs.getString("postal_code"),
                        rs.getString("street"),
                        rs.getString("number"));

            }
        } catch (SQLException ex) {
            throw new DALException(ex.getSQLState() + "%n" + ex.getMessage() + "%n" + ex.getErrorCode(), ex);

        }

    }

    @Override
    public int insertAddress(Address address) throws DALException {
        try (Connection conn = DriverManager.getConnection(_dbConnectionString, _dbUsername, _dbPassword);
                PreparedStatement pstmt = conn.prepareStatement(insertStatement)) {

          pstmt.setString(1, address.getCountry());
          pstmt.setString(2, address.getCity());
          pstmt.setString(3, address.getMunicipality());
          pstmt.setString(4,address.getPostalCode());
          pstmt.setString(5, address.getStreet());
          pstmt.setString(6, address.getNumber());
          if(!(address.getFloor()==null && address.getApartmentNo()==null)){
          pstmt.setInt(7, address.getFloor());
          pstmt.setInt(8, address.getApartmentNo());
          }else{
          pstmt.setNull(7,java.sql.Types.INTEGER);
          pstmt.setNull(8,java.sql.Types.INTEGER);
          }

          pstmt.execute();

            try (ResultSet rs = pstmt.executeQuery("SELECT LAST_INSERT_ID();")) {
                rs.next();
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            throw new DALException(ex.getSQLState() + "%n" + ex.getMessage() + "%n" + ex.getErrorCode(), ex);

        }

    }

    @Override
    public void removeAdress(int id) throws DALException {
         try (Connection conn = DriverManager.getConnection(_dbConnectionString, _dbUsername, _dbPassword);
                PreparedStatement pstmt = conn.prepareStatement(removeStatement)){
             pstmt.setInt(1, id);
             pstmt.execute();
            
                     System.out.printf("The Adress with ID-%d was removed\n",id);
                 
             
             
         }catch (SQLException ex) {
            throw new DALException(ex.getSQLState() + "%n" + ex.getMessage() + "%n" + ex.getErrorCode(), ex);

        }
    }

}
