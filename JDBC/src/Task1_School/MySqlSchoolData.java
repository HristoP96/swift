/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Task1_School;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ickoto
 */
public class MySqlSchoolData {

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

    static void insertTeacher(int teacher_id, String teacher_name, String teacher_email, double teacher_salary) throws SQLException {

       

        try (Connection con = DriverManager.getConnection(url, userName, password);
                PreparedStatement pstmt = con.prepareStatement("INSERT INTO teachers(id,name,email,salary)"
                        + "VALUES(?,?,?,?)")) {
            pstmt.setInt(1, teacher_id);
            pstmt.setString(2, teacher_name);
            pstmt.setString(3, teacher_email);
            pstmt.setDouble(4, teacher_salary);
            pstmt.execute();
           

        } catch (SQLException ex) {
            while (ex != null) {
                System.out.println(ex.getSQLState());
                System.out.println(ex.getMessage());
                System.out.println(ex.getErrorCode());
                ex = ex.getNextException();
            }

        }
    }

    static void insertStudent(int student_id, String student_name, Date student_enrollment_date) throws SQLException {
        

        try (Connection con = DriverManager.getConnection(url, userName, password);
                PreparedStatement pstmt = con.prepareStatement("INSERT INTO students(id,name,enrollment_date)"
                        + "VALUES(?,?,?)")) {

            pstmt.setInt(1, student_id);
            pstmt.setString(2, student_name);
            pstmt.setDate(3, student_enrollment_date);
            pstmt.execute();
            

        } catch (SQLException ex) {
            while (ex != null) {
                System.out.println(ex.getSQLState());
                System.out.println(ex.getMessage());
                System.out.println(ex.getErrorCode());
                ex = ex.getNextException();
            }

        }

    }

    static void getTeacher(int teacher_id) {
        
        try (Connection con = DriverManager.getConnection(url, userName, password);
                PreparedStatement pstmt = con.prepareStatement("Select * from teachers where id=?")) {
            pstmt.setInt(1, teacher_id);
            pstmt.execute();

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    System.out.printf("%d %s %s", rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"));
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

    static void getStudent(int student_id) {
       
        try (Connection con = DriverManager.getConnection(url, userName, password);
                PreparedStatement pstmt = con.prepareStatement("Select * from students where id=?")) {
            pstmt.setInt(1, student_id);
            pstmt.execute();

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    System.out.printf("%d %s %s", rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDate("enrollment_date"));
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

    static void getTeachers(double min, double max) {
        
        try (Connection con = DriverManager.getConnection(url, userName, password);
                PreparedStatement pstmt = con.prepareStatement("Select * from teachers where salary>=? and salary<=?")) {
            pstmt.setDouble(1, min);
            pstmt.setDouble(2, max);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    System.out.printf("%d: %s - %f", rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("salary"));
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

    static void getStudents(Date boundary) {
        
        try (Connection con = DriverManager.getConnection(url, userName, password);
                PreparedStatement pstmt = con.prepareStatement("Select * from students where enrollment_date>=?")) {
            pstmt.setDate(1, boundary);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    System.out.printf("%d: %s -%s", rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDate("enrollment_date"));
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

    static void getDisciplinesByTeacherId(int teacher_id) {
        
        try (Connection con = DriverManager.getConnection(url, userName, password);
                PreparedStatement pstmt = con.prepareStatement("Select disciplines.name from school.disciplines"
                        + " JOIN  school.disciplines_taught"
                        + " ON disciplines_taught.disciplineID=disciplines.id"
                        + " WHERE teacherID=?")) {
            pstmt.setInt(1, teacher_id);
            pstmt.execute();
            System.out.println("HEY");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    System.out.printf("%s", rs.getString("name"));
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

    static void getTeachersByDisciplineName(String discipline_name) {
       
        try (Connection con = DriverManager.getConnection(url, userName, password);
                PreparedStatement pstmt = con.prepareStatement("Select school.teachers.name"
                        + " from school.disciplines_taught"
                        + " JOIN  school.teachers"
                        + " ON disciplines_taught.teacherID=teachers.id"
                        + " JOIN school.disciplines"
                        + " ON disciplines_taught.disciplineID=disciplines.id"
                        + " WHERE disciplines.name=?")) {
            pstmt.setString(1, discipline_name);
            pstmt.execute();
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    System.out.printf("%s", rs.getString("name"));
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
