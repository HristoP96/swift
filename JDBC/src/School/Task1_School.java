package School;

import java.util.Scanner;
import java.sql.Date;
import java.time.LocalDate;

public class Task1_School  {

    public static void main(String[] args)  {

        Scanner sc = new Scanner(System.in);
        final String DBMS_CONN_STRING = "jdbc:mysql://localhost:3306/school";
        final String DBMS_USERNAME = "root";
        final String DBMS_PASSWORD = "ickotomf56";
        
        MySqlSchoolData dataBase = new MySqlSchoolData(DBMS_CONN_STRING, DBMS_USERNAME, DBMS_PASSWORD);
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
                dataBase.insertTeacher(t_id, t_name, email, salary);
                break;
            case "ADDS":
                System.out.println("Insert student_charakteristics:");
                System.out.println("ID:");
                int s_id = sc.nextInt();
                sc.nextLine();
                System.out.println("Name:");
                String s_name = sc.nextLine();
                Date en_date = Date.valueOf(LocalDate.now());
                dataBase.insertStudent(s_id, s_name, en_date);
                break;
            case "GETT":
                System.out.println("Teacher_id");
                int tid = sc.nextInt();
                dataBase.getTeacher(tid);
                break;
            case "GETS":
                System.out.println("Student_id");
                int std = sc.nextInt();
                dataBase.getStudent(std);
                break;
            case "TRS":
                System.out.println("Min salary:");
                double min = sc.nextDouble();
                System.out.println("Max salary:");
                double max = sc.nextDouble();
                dataBase.getTeachers(min, max);
                break;
            case "STDS":
                System.out.println("Students that are from:");
                Date date = Date.valueOf(sc.next());
                dataBase.getStudents(date);
                break;
            case "GRAF":
                System.out.println("Teacher ID:");
                int id = sc.nextInt();
                dataBase.getDisciplinesByTeacherId(id);
                break;
            case "PROG":
                System.out.println("Discipline name:");
                String name = sc.next();
                dataBase.getTeachersByDisciplineName(name);
                break;
                
        }

    }

}
