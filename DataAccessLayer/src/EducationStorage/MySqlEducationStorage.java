/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.time.LocalDate;

/**
 *
 * @author ickoto
 */
public class MySqlEducationStorage implements EducationStorage {

    private final String _dbConnectionString;
    private final String _dbUsername;
    private final String _dbPassword;
    private final String getStatement = "SELECT * FROM citizen_registrations.educations where id =?";
    private final String insertStatement = "INSERT INTO citizen_registrations.educations (`type_id`,`institution_name`,`enrollment_date`,`graduation_date`,`graduated`,`final_grad`)"
            + "VALUES (?, ?, ?, ?, ?, ?);";
    private final String removeStatement = "DELETE FROM citizen_registrations.educations where id =?";

    public MySqlEducationStorage(String _dbConnectionString, String _dbUsername, String _dbPassword) {
        this._dbConnectionString = _dbConnectionString;
        this._dbUsername = _dbUsername;
        this._dbPassword = _dbPassword;
    }

    @Override
    public Education getEducation(int id) throws DALException {
        try (Connection conn = DriverManager.getConnection(_dbConnectionString, _dbUsername, _dbPassword);
                PreparedStatement pstmt = conn.prepareStatement(getStatement)) {
            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                rs.next();
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
            
            if ((education instanceof GradedEducation )&& education.isGraduated()) {
                pstmt.setBoolean(5,education.isGraduated());
                pstmt.setDouble(6, ((GradedEducation) education).getFinalGrade());
            }else{
                pstmt.setNull(5, java.sql.Types.INTEGER);
                pstmt.setNull(6, java.sql.Types.INTEGER);
            }
                
            
            pstmt.execute();
            try (ResultSet rs = pstmt.executeQuery("SELECT LAST_INSERT_ID()")) {
                rs.next();
                return rs.getInt(1);
            }

        } catch (SQLException ex) {
            throw new DALException("SQL failed \n" + ex.getSQLState() + "%n" + ex.getMessage() + "%n" + ex.getErrorCode(), ex);

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

}
