package SocialInsuranceStorage;

import DALException.DALException;
import insurance.SocialInsuranceRecord;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import personaldetails.Citizen;

public class MySqlSocialInsuranceStorage implements SocialInsuranceStorage {

    private final String _dbConnectionString;
    private final String _dbUsername;
    private final String _dbPassword;
    private final String getSocialInsurance = "SELECT * FROM citizen_registrations.socialinsurances where id = ?";
    private final String addSocialInsurance = "INSERT INTO citizen_registrations.socialinsurances(`year`,`month`,`amount`,`person_id`)"
            + "VALUES(?,?,?,?);";
    private final String removeInsurance = "DELETE FROM citizen_registrations.socialinsurances where id =?";

    public MySqlSocialInsuranceStorage(String DB_CONN_STRING, String DB_USERNAME, String DB_PASSWORD) {
        this._dbConnectionString = DB_CONN_STRING;
        this._dbUsername = DB_USERNAME;
        this._dbPassword = DB_PASSWORD;
    }

    @Override
    public SocialInsuranceRecord getSocialInsurance(int id) throws DALException {
        try (Connection conn = DriverManager.getConnection(_dbConnectionString, _dbUsername, _dbPassword);
                PreparedStatement pstmt = conn.prepareStatement(getSocialInsurance)) {
            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                rs.next();
                return new SocialInsuranceRecord(rs.getInt("year"),
                        rs.getInt("month"),
                        rs.getDouble("amount"));
            }

        } catch (SQLException ex) {
            throw new DALException("SQL failed \n" + ex.getSQLState() + "\n" + ex.getMessage() + "\n" + ex.getErrorCode(), ex);

        }
    }

    @Override
    public int insertSocialInsurance(SocialInsuranceRecord socialInsurance, int id) throws DALException {

        try (Connection conn = DriverManager.getConnection(_dbConnectionString, _dbUsername, _dbPassword);
                PreparedStatement pstmt = conn.prepareStatement(addSocialInsurance)) {
            pstmt.setInt(1, socialInsurance.getYear());
            pstmt.setInt(2, socialInsurance.getMonth());
            pstmt.setDouble(3, socialInsurance.getAmount());
            pstmt.setInt(4, id);
            pstmt.execute();
            try (ResultSet rs = pstmt.executeQuery("SELECT LAST_INSERT_ID()")) {
                rs.next();

                return rs.getInt(1);
            }

        } catch (SQLException ex) {
            throw new DALException("SQL failed \n" + ex.getSQLState() + "\n" + ex.getMessage() + "\n" + ex.getErrorCode(), ex);

        }
    }

    @Override
    public void removeSocialInsurance(int id) throws DALException {
        try (Connection conn = DriverManager.getConnection(_dbConnectionString, _dbUsername, _dbPassword);
                PreparedStatement pstmt = conn.prepareStatement(removeInsurance)) {
            pstmt.setInt(1, id);
            pstmt.execute();

            System.out.println("Social insurances were deleted");
        } catch (SQLException ex) {
            throw new DALException("SQL failed \n" + ex.getSQLState() + "\n" + ex.getMessage() + "\n" + ex.getErrorCode(), ex);

        }
    }

    @Override
    public void insertInsurances(Citizen person) throws DALException {
        try (Connection conn = DriverManager.getConnection(_dbConnectionString, _dbUsername, _dbPassword);
                PreparedStatement pstmt = conn.prepareStatement(addSocialInsurance)) {
            for (SocialInsuranceRecord insurance : person.getSocialInsuranceRecords()) {

                pstmt.setInt(1, insurance.getYear());
                pstmt.setInt(2, insurance.getMonth());
                pstmt.setDouble(3, insurance.getAmount());
                pstmt.setInt(4, person.getID());
                pstmt.execute();

            }

        } catch (SQLException ex) {
            throw new DALException("SQL failed \n" + ex.getSQLState() + "\n" + ex.getMessage() + "\n" + ex.getErrorCode(), ex);

        }
    }
}
