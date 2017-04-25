/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CitizenStorage;

import AdressStorage.MySqlAddressStorage;
import DALException.DALException;
import address.Address;
import education.Education;
import education.EducationDegree;
import education.GradedEducation;
import education.HigherEducation;
import education.PrimaryEducation;
import education.SecondaryEducation;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import personaldetails.Citizen;
import personaldetails.Gender;
import MySqlDataStorage.MySqlDataStorage.*;
import static MySqlDataStorage.MySqlDataStorage.*;
import insurance.SocialInsuranceRecord;
import java.time.LocalDateTime;
import java.util.Stack;

/**
 *
 * @author ickoto
 */
public class MySqlCitizenStorage implements CitizenStorage {

    private final String _dbConnectionString;
    private final String _dbUsername;
    private final String _dbPassword;

    private final String getPerson = "SELECT * FROM citizen_registrations.people\n"
            + "JOIN citizen_registrations.addresses\n"
            + "ON addresses.id = citizen_registrations.people.addressID\n"
            + "WHERE citizen_registrations.people.id =?";

    private final String insertEducationMatches = "INSERT INTO citizen_registrations.people_educations(`person_id`,`education_id`)"
            + "VALUES(?,?);";

    private final String getEducationsStatement = "SELECT * FROM citizen_registrations.people_educations\n"
            + "INNER JOIN citizen_registrations.educations\n"
            + "ON citizen_registrations.educations.id = citizen_registrations.people_educations.education_id\n"
            + "WHERE citizen_registrations.people_educations.person_id =?";

    private static String getInsurancesStatement = "SELECT * FROM citizen_registrations.people_insurances\n"
            + "INNER JOIN citizen_registrations.socialinsurances\n"
            + "ON citizen_registrations.socialinsurances.id = citizen_registrations.people_insurances.insurance_id\n"
            + "WHERE citizen_registrations.people_insurances.person_id =?";

    private final String insertPerson = "INSERT INTO citizen_registrations.people (`first_name`,`middle_name`,`last_name`,`gender_id`,`height`,`dateOfBirth`,`addressID`)"
            + "VALUES (?, ?, ?, ?, ?, ?,?);";

    private final String selectAddress = "SELECT people.addressID FROM citizen_registrations.people WHERE people.id=?;";

    private final String removeCitizen = "DELETE FROM citizen_registrations.people WHERE id =?";

    private final String removeEducationMatches = "DELETE FROM citizen_registrations.people_educations WHERE person_id=?";

    private final String selectEducations = "SELECT education_id FROM citizen_registrations.people_educations WHERE person_id =?;";

    private final String removeInsuranceMatches = "DELETE FROM citizen_registrations.people_insurances WHERE person_id=?";

    private final String selectInsurances = "SELECT insurance_id FROM  citizen_registrations.people_insurances  WHERE person_id=?";
    
    private final String insertInsuranceMatches = "INSERT INTO citizen_registrations.people_insurances(`person_id`,`insurance_id`)"+
            "VALUES(?,?);";

    public MySqlCitizenStorage(String dbConnectionString, String dbUsername, String dbPassword) {
        _dbConnectionString = dbConnectionString;
        _dbUsername = dbUsername;
        _dbPassword = dbPassword;
    }

    @Override
    public Citizen getCitizen(int id) throws DALException {
        Citizen citizen = null;
        Education education = null;
        SocialInsuranceRecord insurance = null;
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

                citizen.setAddress(convertToAddress(rs));

            }
            try (PreparedStatement stmt = conn.prepareStatement(getEducationsStatement)) {
                stmt.setInt(1, id);

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        education = converToEducation(rs);

                        if (education instanceof GradedEducation && rs.getBoolean("graduated") == true) {

                            ((GradedEducation) education).gotGraduated(rs.getFloat("final_grad"));

                        }
                        citizen.addEducation(education);

                    }
                }
            }
            try (PreparedStatement stmt = conn.prepareStatement(getInsurancesStatement)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        insurance = convertToInsurance(rs);
                        citizen.addSocialInsuranceRecord(insurance);
                    }
                }
            }

            return citizen;

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

                if (!(citizen.getEducations().isEmpty())) {
                    for (Education education : citizen.getEducations()) {
                        try (PreparedStatement stmt = conn.prepareStatement((insertEducationMatches))) {
                            stmt.setInt(1, rs.getInt(1));
                            stmt.setInt(2, educationStorage.insertEducation(education));
                            stmt.execute();
                        }

                    }
                }
                if (!(citizen.getSocialInsuranceRecords().isEmpty())) {
                    for (SocialInsuranceRecord insurance : citizen.getSocialInsuranceRecords()) {
                        try(PreparedStatement stmt = conn.prepareStatement(insertInsuranceMatches)){
                            stmt.setInt(1, rs.getInt(1));
                            stmt.setInt(2, insuranceStorage.insertSocialInsurance(insurance));
                        }
                    }
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

    private static Address convertToAddress(final ResultSet rs) throws SQLException {

        int floor = rs.getInt("floor");
        int apart = rs.getInt("apartmentNo");
        if (!(floor == 0 && apart == 0)) {
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

    private static Education converToEducation(final ResultSet rs) throws SQLException {
        switch (rs.getInt("type_id")) {
            case 1:
                return new PrimaryEducation(rs.getString("institution_name"),
                        rs.getDate("enrollment_date").toLocalDate(),
                        rs.getDate("graduation_date").toLocalDate());

            case 2:
                return new SecondaryEducation(rs.getString("institution_name"),
                        rs.getDate("enrollment_date").toLocalDate(),
                        rs.getDate("graduation_date").toLocalDate());
            default:
                return new HigherEducation(rs.getString("institution_name"),
                        rs.getDate("enrollment_date").toLocalDate(),
                        rs.getDate("graduation_date").toLocalDate(),
                        EducationDegree.values()[rs.getInt("type_id") - 1]);

        }
    }

    private static SocialInsuranceRecord convertToInsurance(final ResultSet rs) throws SQLException {
        return new SocialInsuranceRecord(rs.getInt("year"),
                rs.getInt("month"),
                rs.getDouble("amount"));
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

    private void removeInsuranceMatches(int id) throws SQLException {
        try (Connection conn = DriverManager.getConnection(_dbConnectionString, _dbUsername, _dbPassword);
                PreparedStatement pstmt = conn.prepareStatement(removeInsuranceMatches)) {
            pstmt.setInt(1, id);
            pstmt.execute();

        }
    }

    private void removeInsurances(int id) throws SQLException {
        try (Connection conn = DriverManager.getConnection(_dbConnectionString, _dbUsername, _dbPassword);
                PreparedStatement pstmt = conn.prepareStatement(selectInsurances)) {
            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                removeInsuranceMatches(id);
                while (rs.next()) {
                    insuranceStorage.removeSocialInsurance(rs.getInt("insurance_id"));
                }
            }

        }
    }
}
