/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Task1_School;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author ickoto
 */
public class Task1_School extends MySqlSchoolData {

    public static void main(String[] args) throws SQLException {

        Scanner sc = new Scanner(System.in);
        final String DBMS_CONN_STRING = "jdbc:mysql://localhost:3306/school";
        setUrl(DBMS_CONN_STRING);
        final String DBMS_USERNAME = "root";
        setUserName(DBMS_USERNAME);
        System.out.println("Set password: ");
        final String DBMS_PASSWORD = sc.next();
        setPass(DBMS_PASSWORD);
        System.out.println(url);
        System.out.println(userName);
        System.out.println(password);
        System.out.println("Set command:");
        String command = sc.next();

        switch (command) {
            case "ADDT":
                System.out.println("Insert teacher_charakteristics");
                System.out.println("ID:");
                int t_id = sc.nextInt();
                sc.nextLine();
                System.out.println("Name:");
                String t_name = sc.nextLine();
                System.out.println("Email:");
                String email = sc.next();
                System.out.println("Salary:");
                double salary = sc.nextDouble();
                insertTeacher(t_id, t_name, email, salary);
                break;
            case "ADDS":
                System.out.println("Insert student_charakteristics:");
                System.out.println("ID:");
                int s_id = sc.nextInt();
                sc.nextLine();
                System.out.println("Name:");
                String s_name = sc.nextLine();
                Date en_date = Date.valueOf(LocalDate.now());
                insertStudent(s_id, s_name, en_date);
                break;
            case "GETT":
                System.out.println("Teacher_id");
                int tid = sc.nextInt();
                getTeacher(tid);
                break;
            case "GETS":
                System.out.println("Student_id");
                int std = sc.nextInt();
                getStudent(std);
                break;
            case "TRS":
                System.out.println("Min salary:");
                double min = sc.nextDouble();
                System.out.println("Max salary:");
                double max = sc.nextDouble();
                getTeachers(min, max);
                break;
            case "STDS":
                System.out.println("Students that are from:");
                Date date = Date.valueOf(sc.next());
                getStudents(date);
                break;
            case "GRAF":
                System.out.println("Teacher ID:");
                int id = sc.nextInt();
                getDisciplinesByTeacherId(id);
                break;
            case "PROG":
                System.out.println("Discipline name:");
                String name = sc.next();
                getTeachersByDisciplineName(name);
                break;
                
        }

    }

}
