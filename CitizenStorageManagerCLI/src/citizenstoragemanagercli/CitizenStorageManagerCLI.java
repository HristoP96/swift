/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package citizenstoragemanagercli;

import CitizenStorage.MySqlCitizenStorage;
import DALException.DALException;
import address.Address;
import education.Education;
import education.EducationDegree;
import education.GradedEducation;
import education.HigherEducation;
import java.time.LocalDate;
import personaldetails.Citizen;
import personaldetails.Gender;
import education.PrimaryEducation;
import education.SecondaryEducation;
import insurance.SocialInsuranceRecord;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class CitizenStorageManagerCLI {

    static final String DB_CONN_STRING = "jdbc:mysql://localhost:3306/citizen_registrations";
    static final String DB_USERNAME = "root";
    static final String DB_PASSWORD = "ickotomf56";

    public static MySqlCitizenStorage citizenStorage = new MySqlCitizenStorage(DB_CONN_STRING, DB_USERNAME, DB_PASSWORD);

    static final String PATH = "C:\\Users\\ickoto\\Desktop\\in_1000.txt";
    static DateTimeFormatter format = DateTimeFormatter.ofPattern("d.M.yyyy");
    static final String DISABLE_FOREIGN_KEYS = "SET FOREIGN_KEY_CHECKS = 0;";
    static String SELECT_DB_TABLE = "TRUNCATE TABLE ?";
    static final String ENABLE_FOREIGN_KEYS = "SET FOREIGN_KEY_CHECKS = 1;";
    static final String AI_RESET = "ALTER TABLE ? AUTO_INCREMENT =1";
    
    private static final String addresses = "addresses";
    private static final String educations = "educations";
    private static final String insurances = "socialinsurances";
    private static final String peopleEducations = "people_educations";
    private static final String people = "people";

    public static void main(String[] args) throws DALException, IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Citizen Store Managment");
        System.out.println();
        System.out.println("Choose action: INSERT or DELETE");
        String command = sc.next();

        switch (command) {
            case "INSERT":
                insertCitizens(PATH);
                break;
            case "DELETE":
                System.out.println("Which table do you want to delete");
                String table = sc.next();
                switch (table) {
                    case addresses:
                        deleteTable(addresses);
                        reset_AI(addresses);
                        deleteTable(peopleEducations);
                        break;
                    case educations:
                        deleteTable(educations);
                        reset_AI(educations);
                        deleteTable(peopleEducations);
                        break;
                    case "socialinsurances":
                        System.out.println();
                        deleteTable(insurances);
                        reset_AI(insurances);
                        break;
                    case people:
                        deleteTable(people);
                        break;
                    case "all":
                        deleteAllTables();
                        break;
                    case "people_educations":
                        deleteTable(peopleEducations);
                        break;

                    default:
                        throw new IllegalArgumentException(""
                                + "You have to choose:\n"
                                + "addresses\n"
                                + "educations\n"
                                + "people\n"
                                + "socialinsurances\n"
                                + "people_educations\n"
                                + "or all\n");

                }
                break;
            default:
                throw new IllegalArgumentException("You have to choose DELETE or INSERT");

        }

    }

    private static String truncateTable(String tableName) {
        return "TRUNCATE TABLE " + tableName;
    }

    private static void deleteTable(String tableName) throws DALException {
        try (Connection conn = DriverManager.getConnection(DB_CONN_STRING, DB_USERNAME, DB_PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(DISABLE_FOREIGN_KEYS);) {
            stmt.execute();
            System.out.println("Foreign_keys for [" + tableName + "] were disabled ");

            try (PreparedStatement pstmt = conn.prepareStatement(truncateTable(tableName))) {
                pstmt.execute();
                System.out.println("Table [" + tableName + "] was truncated");
            }

            try (ResultSet rs = stmt.executeQuery(ENABLE_FOREIGN_KEYS)) {
                System.out.println("Foreign_keys for [" + tableName + "] were enabled");
            }

        } catch (SQLException ex) {
            throw new DALException(ex.getSQLState() + "\n" + ex.getMessage() + "\n" + ex.getErrorCode(), ex);

        }

    }

    private static String resetAI(String tableName) {
        return "ALTER TABLE " + tableName + " AUTO_INCREMENT =1";
    }

    private static void reset_AI(String tableName) throws DALException {
        try (Connection conn = DriverManager.getConnection(DB_CONN_STRING, DB_USERNAME, DB_PASSWORD);
                PreparedStatement pstmt = conn.prepareStatement(resetAI(tableName));) {
            pstmt.execute();
            System.out.println("Foreign_Keys were reset  for table " + tableName);
        } catch (SQLException ex) {
            throw new DALException(ex.getSQLState() + "\n" + ex.getMessage() + "\n" + ex.getErrorCode(), ex);

        }
    }

    private static void deleteAllTables() throws DALException {
        deleteTable(people);
        reset_AI(people);
        deleteTable(addresses);
        reset_AI(addresses);
        deleteTable(educations);
        reset_AI(educations);
        deleteTable(insurances);
        reset_AI(insurances);
        deleteTable(peopleEducations);

    }

    private static void insertCitizens(String path) throws DALException {
        try {
            int counter = 0;

            String line = null;

            File project = new File(path);

            FileReader fr = new FileReader(project);

            BufferedReader buffer = new BufferedReader(fr);

            int N = Integer.parseInt(buffer.readLine());

            Citizen person = null;

            while ((line = buffer.readLine()) != null) {
                ++counter;

                if (counter % 2 == 0) {
                    for (SocialInsuranceRecord socialInsurance : convertLineToInsuranceList(line)) {
                        person.addSocialInsuranceRecord(socialInsurance);
                    }
                    citizenStorage.insertCitizen(person);
                    System.out.println("Citizen has been inserted");

                } else {

                    person = convertLineToCitizen(line);

                }

            }

            buffer.close();

            System.out.println();

        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file");

        }

    }

    private static Citizen convertLineToCitizen(String line) throws IllegalArgumentException {
        Citizen person;
        Education education;
        String firstName = null;
        String middleName = null;
        String lastName = null;
        Gender gender = null;
        Address address = null;
        LocalDate date = null;

        int height = 0;
        String[] split = line.split(";");
        for (int i = 0; i < split.length - 1; i++) {
            firstName = split[0];
            middleName = split[1];
            lastName = split[2];
            if (split[3].equals("M")) {
                gender = Gender.Male;
            } else {
                gender = Gender.Female;
            }

            date = LocalDate.parse(split[4], format);

            height = Integer.parseInt(split[5]);
            if ((split.length == 12) || split[12].isEmpty()) {
                address = new Address(
                        split[6],
                        split[7],
                        split[8],
                        split[9],
                        split[10],
                        split[11]);

            } else {
                address = new Address(
                        split[6],
                        split[7],
                        split[8],
                        split[9],
                        split[10],
                        split[11],
                        Integer.parseInt(split[12]),
                        Integer.parseInt(split[13]));

            }
        }

        person = new Citizen(firstName, middleName, lastName, gender, height, date);
        person.setAddress(address);

        for (int i = 13; i < split.length - 1; i++) {
            switch (split[i]) {
                case "P":
                    education = new PrimaryEducation(split[i + 1],
                            LocalDate.parse(split[i + 2], format),
                            LocalDate.parse(split[i + 3], format));
                    if (!(education.getGraduationDate().isAfter(LocalDate.now()))) {
                        education.gotGraduated();

                    }
                    person.addEducation(education);
                    break;
                case "S":
                    education = new SecondaryEducation(split[i + 1],
                            LocalDate.parse(split[i + 2], format),
                            LocalDate.parse(split[i + 3], format));
                    if (!(education.getGraduationDate().isAfter(LocalDate.now()))) {

                        ((GradedEducation) education).gotGraduated(Float.parseFloat(split[i + 4]));

                    }
                    person.addEducation(((GradedEducation) education));

                    break;
                case "M":
                    education = new HigherEducation(split[i + 1],
                            LocalDate.parse(split[i + 2], format),
                            LocalDate.parse(split[i + 3], format),
                            EducationDegree.Master);
                    if (!(LocalDate.parse(split[i + 3], format).isAfter(LocalDate.now()))) {
                        ((GradedEducation) education).gotGraduated(Float.parseFloat(split[i + 4]));
                    }
                    person.addEducation(((GradedEducation) education));
                    break;
                case "B":
                    education = new HigherEducation(split[i + 1],
                            LocalDate.parse(split[i + 2], format),
                            LocalDate.parse(split[i + 3], format),
                            EducationDegree.Bachelor);
                    if (!(LocalDate.parse(split[i + 3], format).isAfter(LocalDate.now()))) {
                        ((GradedEducation) education).gotGraduated(Float.parseFloat(split[i + 4]));
                    }

                    person.addEducation(((GradedEducation) education));
                    break;
                case "D":
                    education = new HigherEducation(split[i + 1],
                            LocalDate.parse(split[i + 2], format),
                            LocalDate.parse(split[i + 3], format),
                            EducationDegree.Doctorate);
                    if (!(LocalDate.parse(split[i + 3], format).isAfter(LocalDate.now()))) {
                        ((GradedEducation) education).gotGraduated(Float.parseFloat(split[i + 4]));
                    }
                    person.addEducation(((GradedEducation) education));
                    break;

            }

        }

        return person;
    }

    private static List<SocialInsuranceRecord> convertLineToInsuranceList(String line) throws IllegalArgumentException {
        List<SocialInsuranceRecord> insurances = new LinkedList<>();
        String[] split = line.split(";");
        for (int i = 0; i < split.length - 1; i++) {
            insurances.add(new SocialInsuranceRecord(
                    Integer.parseInt(split[i]),
                    Integer.parseInt(split[++i]),
                    Double.parseDouble(split[++i])));
        }
        return insurances;

    }

}
