package CitizenStorage;

import AdressStorage.MySqlAddressStorage;
import DALException.DALException;
import EducationStorage.MySqlEducationStorage;
import education.Education;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import personaldetails.Citizen;
import personaldetails.Gender;
import SocialInsuranceStorage.MySqlSocialInsuranceStorage;
import insurance.SocialInsuranceRecord;
import java.util.ArrayList;

public class MySqlCitizenStorage implements CitizenStorage {

    private final String _dbConnectionString;
    private final String _dbUsername;
    private final String _dbPassword;
    
    private final String getPerson = "SELECT * FROM citizen_registrations.people WHERE citizen_registrations.people.id =?";

    private final String insertEducationMatches = "INSERT INTO citizen_registrations.people_educations(`person_id`,`education_id`)"
            + "VALUES(?,?);";

    private final String insertPerson = "INSERT INTO citizen_registrations.people (`first_name`,`middle_name`,`last_name`,`gender_id`,`height`,`dateOfBirth`,`addressID`)"
            + "VALUES (?, ?, ?, ?, ?, ?,?);";

    private final String selectAddress = "SELECT people.addressID FROM citizen_registrations.people WHERE people.id=?;";

    private final String removeCitizen = "DELETE FROM citizen_registrations.people WHERE id =?";


    private final MySqlAddressStorage _addressStorage;
    private final MySqlEducationStorage _educationStorage;
    private final MySqlSocialInsuranceStorage _insuranceStorage;

    public MySqlCitizenStorage(String DB_CONN_STRING, String DB_USERNAME, String DB_PASSWORD) {
        this._dbConnectionString = DB_CONN_STRING;
        this._dbUsername = DB_USERNAME;
        this._dbPassword = DB_PASSWORD;
        _addressStorage = new MySqlAddressStorage(DB_CONN_STRING, DB_USERNAME, DB_PASSWORD);
        _educationStorage = new MySqlEducationStorage(DB_CONN_STRING, DB_USERNAME, DB_PASSWORD);
        _insuranceStorage = new MySqlSocialInsuranceStorage(DB_CONN_STRING, DB_USERNAME, DB_PASSWORD);

    }

    @Override
    public Citizen getCitizen(int id) throws DALException {
        Citizen citizen = null;
        List<SocialInsuranceRecord> insurances = new ArrayList<>();
        insurances = _insuranceStorage.getInsurances(id);
        List<Education> educations = new ArrayList<>();
        educations = _educationStorage.getEducations(id);
        try (Connection conn = DriverManager.getConnection(_dbConnectionString, _dbUsername, _dbPassword);
                PreparedStatement pstmt = conn.prepareStatement(getPerson)) {
            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                rs.next();

                citizen = new Citizen(rs.getString("first_name"),
                        rs.getString("middle_name"),
                        rs.getString("last_name"),
                        Gender.values()[rs.getInt("gender_id")],
                        rs.getInt("height"),
                        rs.getDate("dateOfBirth").toLocalDate());

                citizen.setAddress(_addressStorage.getAddress(rs.getInt("addressID")));
                for (Education education : educations) {

                    citizen.addEducation(education);
                }
                for (SocialInsuranceRecord insurance : insurances) {
                    citizen.addSocialInsuranceRecord(insurance);

                }

                return citizen;

            }
        } catch (SQLException ex) {
            throw new DALException("SQL failed \n" + ex.getSQLState() + "\t" + ex.getMessage() + "\t" + ex.getErrorCode(), ex);

        }
    }

   

    

    @Override
    public int insertCitizen(Citizen citizen) throws DALException {

        try (Connection conn = DriverManager.getConnection(_dbConnectionString, _dbUsername, _dbPassword);
                PreparedStatement pstmt = conn.prepareStatement(insertPerson)) {

            pstmt.setString(1, citizen.getFirstName());
            pstmt.setString(2, citizen.getMiddleName());
            pstmt.setString(3, citizen.getLastName());
            pstmt.setInt(4, citizen.getGender().ordinal());
            pstmt.setInt(5, citizen.getHeight());
            pstmt.setDate(6, Date.valueOf(citizen.getDateOfBirth()));
            pstmt.setInt(7, _addressStorage.insertAddress(citizen.getAddress()));
            pstmt.execute();
            try (ResultSet rs = pstmt.executeQuery("SELECT LAST_INSERT_ID()")) {
                rs.next();
                citizen.setID(rs.getInt(1));
                if (!(citizen.getEducations().isEmpty())) {

                    try (PreparedStatement stmt = conn.prepareStatement((insertEducationMatches))) {
                        for (Education education : citizen.getEducations()) {
                            stmt.setInt(1, rs.getInt(1));

                            stmt.setInt(2, _educationStorage.insertEducation(education));

                            stmt.execute();
                        }

                    }
                }
                if (!(citizen.getSocialInsuranceRecords().isEmpty())) {
                    _insuranceStorage.insertInsurances(citizen);
                }
                return rs.getInt(1);
            }

        } catch (SQLException ex) {
            throw new DALException("SQL failed \n" + ex.getSQLState() + "\n" + ex.getMessage() + "\n" + ex.getErrorCode(), ex);

        }
    }

    @Override
    public void removeCitizen(int id) throws DALException {
        try (Connection conn = DriverManager.getConnection(_dbConnectionString, _dbUsername, _dbPassword);
                PreparedStatement pstmt = conn.prepareStatement(removeCitizen)) {
            pstmt.setInt(1, id);

            _educationStorage.removeEducations(id);
            _insuranceStorage.removeInsurances(id);
            int AdressID;

            try (PreparedStatement stmt = conn.prepareStatement(selectAddress)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    rs.next();
                    AdressID = rs.getInt("addressID");
                }

            }
            pstmt.execute();
            _addressStorage.removeAdress(AdressID);

        } catch (SQLException ex) {
            throw new DALException("SQL failed \n" + ex.getSQLState() + "%n" + ex.getMessage() + "%n" + ex.getErrorCode(), ex);

        }
    }

   

    

}
