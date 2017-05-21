package EducationStorage;

import DALException.DALException;
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
import java.util.ArrayList;
import java.util.List;

public class MySqlEducationStorage implements EducationStorage {

    private final String _dbConnectionString;
    private final String _dbUsername;
    private final String _dbPassword;
    private final String getStatement = "SELECT * FROM citizen_registrations.educations where id =?";
    private final String insertStatement = "INSERT INTO citizen_registrations.educations (`type_id`,`institution_name`,`enrollment_date`,`graduation_date`,`graduated`,`final_grad`)"
            + "VALUES (?, ?, ?, ?, ?, ?);";
    private final String removeStatement = "DELETE FROM citizen_registrations.educations where id =?";

    private final String selectEducations = "SELECT education_id FROM citizen_registrations.people_educations WHERE person_id =?;";

    private final String removeEducationMatches = "DELETE FROM citizen_registrations.people_educations WHERE person_id=?";

    private final String getEducationsStatement = "SELECT * FROM citizen_registrations.people_educations WHERE citizen_registrations.people_educations.person_id =?";

    public MySqlEducationStorage(String DB_CONN_STRING, String DB_USERNAME, String DB_PASSWORD) {
        this._dbConnectionString = DB_CONN_STRING;
        this._dbUsername = DB_USERNAME;
        this._dbPassword = DB_PASSWORD;
    }

    @Override
    public Education getEducation(int id) throws DALException {
        Education education;
        try (Connection conn = DriverManager.getConnection(_dbConnectionString, _dbUsername, _dbPassword);
                PreparedStatement pstmt = conn.prepareStatement(getStatement)) {
            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                rs.next();
                switch (rs.getInt("type_id")) {
                    case 1:
                        education = new PrimaryEducation(rs.getString("institution_name"),
                                rs.getDate("enrollment_date").toLocalDate(),
                                rs.getDate("graduation_date").toLocalDate());
                        if (rs.getBoolean("graduated")) {
                            education.gotGraduated();
                        }
                        return education;
                    case 2:
                        education = new SecondaryEducation(rs.getString("institution_name"),
                                rs.getDate("enrollment_date").toLocalDate(),
                                rs.getDate("graduation_date").toLocalDate());
                        Float check = rs.getFloat("final_grad");
                        if (!(check == null || (check < 2 || check > 6))) {
                            ((GradedEducation) education).gotGraduated(check);
                            return ((GradedEducation) education);
                        }
                        return education;

                    default:
                        education = new HigherEducation(rs.getString("institution_name"),
                                rs.getDate("enrollment_date").toLocalDate(),
                                rs.getDate("graduation_date").toLocalDate(),
                                EducationDegree.values()[rs.getInt("type_id") - 1]);
                        check = rs.getFloat("final_grad");
                        if (!(check == null)) {
                            ((GradedEducation) education).gotGraduated(check);
                            return ((GradedEducation) education);
                        }
                        return education;

                }

            }

        } catch (SQLException ex) {
            throw new DALException("SQL failed \n" + ex.getSQLState() + "\n" + ex.getMessage() + "\n" + ex.getErrorCode(), ex);

        }
    }

    @Override
    public int insertEducation(Education education) throws DALException {
        try (Connection conn = DriverManager.getConnection(_dbConnectionString, _dbUsername, _dbPassword);
                PreparedStatement pstmt = conn.prepareStatement(insertStatement)) {
            pstmt.setInt(1, education.getDegree().getValue());
            pstmt.setString(2, education.getInstitutionName());
            pstmt.setDate(3, Date.valueOf(education.getEnrollmentDate()));
            pstmt.setDate(4, Date.valueOf(education.getGraduationDate()));

            if (education instanceof GradedEducation && (education.isGraduated())) {
                pstmt.setBoolean(5, ((GradedEducation) education).isGraduated());
                pstmt.setDouble(6, ((GradedEducation) education).getFinalGrade());

            } else if (education.isGraduated()) {
                pstmt.setBoolean(5, education.isGraduated());
                pstmt.setNull(6, java.sql.Types.INTEGER);
            } else {
                pstmt.setNull(5, java.sql.Types.INTEGER);
                pstmt.setNull(6, java.sql.Types.INTEGER);
            }
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
    public void removeEducation(int id) throws DALException {
        try (Connection conn = DriverManager.getConnection(_dbConnectionString, _dbUsername, _dbPassword);
                PreparedStatement pstmt = conn.prepareStatement(removeStatement)) {
            pstmt.setInt(1, id);
            pstmt.execute();

        } catch (SQLException ex) {
            throw new DALException("SQL failed \n" + ex.getSQLState() + "\n" + ex.getMessage() + "\n" + ex.getErrorCode(), ex);

        }

    }

    @Override
    public void removeEducations(int id) throws DALException {
        try (Connection conn = DriverManager.getConnection(_dbConnectionString, _dbUsername, _dbPassword);
                PreparedStatement pstmt = conn.prepareStatement(selectEducations)) {
            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                removeEducationMatches(id);
                while (rs.next()) {
                    this.removeEducation(rs.getInt("education_id"));
                }
            }
        } catch (SQLException ex) {
            throw new DALException("SQL failed \n" + ex.getSQLState() + "%n" + ex.getMessage() + "%n" + ex.getErrorCode(), ex);

        }
    }

    @Override
    public void removeEducationMatches(int id) throws DALException {
        try (Connection conn = DriverManager.getConnection(_dbConnectionString, _dbUsername, _dbPassword);
                PreparedStatement pstmt = conn.prepareStatement(removeEducationMatches)) {
            pstmt.setInt(1, id);
            pstmt.execute();
        } catch (SQLException ex) {
            throw new DALException("SQL failed \n" + ex.getSQLState() + "%n" + ex.getMessage() + "%n" + ex.getErrorCode(), ex);

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
                    educations.add(this.getEducation(rs.getInt("education_id")));

                }
                return educations;
            }
        } catch (SQLException ex) {
            throw new DALException("SQL failed \n" + ex.getSQLState() + "\t" + ex.getMessage() + "\t" + ex.getErrorCode(), ex);

        }
    }

}
