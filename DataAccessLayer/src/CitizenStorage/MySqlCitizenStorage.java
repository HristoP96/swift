/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CitizenStorage;

import DALException.DALException;
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
import static MySqlDataStorage.MySqlDataStorage.*;
import insurance.SocialInsuranceRecord;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author ickoto
 */
public class MySqlCitizenStorage implements CitizenStorage {

    private final String _dbConnectionString;
    private final String _dbUsername;
    private final String _dbPassword;
    //08S01
    private final String getPerson = "SELECT * FROM citizen_registrations.people WHERE citizen_registrations.people.id =?";

    private final String insertEducationMatches = "INSERT INTO citizen_registrations.people_educations(`person_id`,`education_id`)"
            + "VALUES(?,?);";

    private final String getEducationsStatement = "SELECT * FROM citizen_registrations.people_educations WHERE citizen_registrations.people_educations.person_id =?";

    private final String getInsurancesStatement = "SELECT * FROM citizen_registrations.socialinsurances WHERE citizen_registrations.socialinsurances.person_id =?";

    private final String insertPerson = "INSERT INTO citizen_registrations.people (`first_name`,`middle_name`,`last_name`,`gender_id`,`height`,`dateOfBirth`,`addressID`)"
            + "VALUES (?, ?, ?, ?, ?, ?,?);";

    private final String selectAddress = "SELECT people.addressID FROM citizen_registrations.people WHERE people.id=?;";

    private final String removeCitizen = "DELETE FROM citizen_registrations.people WHERE id =?";

    private final String removeEducationMatches = "DELETE FROM citizen_registrations.people_educations WHERE person_id=?";

    private final String selectEducations = "SELECT education_id FROM citizen_registrations.people_educations WHERE person_id =?;";

    private final String selectInsurances = "SELECT id FROM  citizen_registrations.socialinsurances WHERE person_id=?";

    public MySqlCitizenStorage(String dbConnectionString, String dbUsername, String dbPassword) {
        _dbConnectionString = dbConnectionString;
        _dbUsername = dbUsername;
        _dbPassword = dbPassword;
    }

    @Override
    public Citizen getCitizen(int id) throws DALException {
        Citizen citizen = null;
        List<SocialInsuranceRecord> insurances = new ArrayList<>();
        insurances = getInsurances(id);
        List<Education> educations = new ArrayList<>();
        educations = getEducations(id);
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

                citizen.setAddress(addressStorage.getAddress(rs.getInt("addressID")));
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
    public List<Education> getEducations(int id) throws DALException {
        List<Education> educations = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(_dbConnectionString, _dbUsername, _dbPassword);
                PreparedStatement pstmt = conn.prepareStatement(getEducationsStatement)) {
            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    educations.add(educationStorage.getEducation(rs.getInt("education_id")));

                }
                return educations;
            }
        } catch (SQLException ex) {
            throw new DALException("SQL failed \n" + ex.getSQLState() + "\t" + ex.getMessage() + "\t" + ex.getErrorCode(), ex);

        }
    }

    @Override
    public List<SocialInsuranceRecord> getInsurances(int id) throws DALException {
        List<SocialInsuranceRecord> insurances = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(_dbConnectionString, _dbUsername, _dbPassword);
                PreparedStatement stmt = conn.prepareStatement(getInsurancesStatement)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    insurances.add(insuranceStorage.getSocialInsurance(rs.getInt("id")));
                }
                return insurances;
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
            pstmt.setInt(7, addressStorage.insertAddress(citizen.getAddress()));
            pstmt.execute();
            try (ResultSet rs = pstmt.executeQuery("SELECT LAST_INSERT_ID()")) {
                rs.next();
                citizen.setID(rs.getInt(1));
                if (!(citizen.getEducations().isEmpty())) {

                    try (PreparedStatement stmt = conn.prepareStatement((insertEducationMatches))) {
                        for (Education education : citizen.getEducations()) {
                            stmt.setInt(1, rs.getInt(1));
                            stmt.setInt(2, educationStorage.insertEducation(education));
                            stmt.execute();
                        }

                    }
                }
                if(!(citizen.getSocialInsuranceRecords().isEmpty())){
                insuranceStorage.insertInsurances(citizen);
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

            removeEducations(id);
            removeInsurances(id);
            int AdressID;

            try (PreparedStatement stmt = conn.prepareStatement(selectAddress)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    rs.next();
                    AdressID = rs.getInt("addressID");
                }

            }
            pstmt.execute();
            addressStorage.removeAdress(AdressID);
            System.out.printf("The Citizen with ID-%d was removed", id);

        } catch (SQLException ex) {
            throw new DALException("SQL failed \n" + ex.getSQLState() + "%n" + ex.getMessage() + "%n" + ex.getErrorCode(), ex);

        }
    }

    private void removeEducationMatches(int id) throws SQLException {
        try (Connection conn = DriverManager.getConnection(_dbConnectionString, _dbUsername, _dbPassword);
                PreparedStatement pstmt = conn.prepareStatement(removeEducationMatches)) {
            pstmt.setInt(1, id);
            pstmt.execute();
        }

    }

    private void removeEducations(int id) throws SQLException {
        try (Connection conn = DriverManager.getConnection(_dbConnectionString, _dbUsername, _dbPassword);
                PreparedStatement pstmt = conn.prepareStatement(selectEducations)) {
            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                removeEducationMatches(id);
                while (rs.next()) {
                    educationStorage.removeEducation(rs.getInt("education_id"));
                }
            }
        }
    }

    private void removeInsurances(int id) throws SQLException {
        try (Connection conn = DriverManager.getConnection(_dbConnectionString, _dbUsername, _dbPassword);
                PreparedStatement pstmt = conn.prepareStatement(selectInsurances)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    insuranceStorage.removeSocialInsurance(rs.getInt("id"));
                }
            }

        }
    }

}
