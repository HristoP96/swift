/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package citizenstoragemanagercli;

import AdressStorage.MySqlAddressStorage;
import CitizenStorage.MySqlCitizenStorage;
import DALException.DALException;
import EducationStorage.MySqlEducationStorage;
import address.Address;
import education.Education;
import education.EducationDegree;
import education.GradedEducation;
import education.HigherEducation;
import java.time.LocalDate;
import java.time.Month;
import personaldetails.Citizen;
import personaldetails.Gender;
import static MySqlDataStorage.MySqlDataStorage.*;
import education.PrimaryEducation;
import education.SecondaryEducation;
import insurance.SocialInsuranceRecord;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.swing.text.DateFormatter;

/**
 *
 * @author ickoto
 */
public class CitizenStorageManagerCLI {

    
    static final String path = "C:\\Users\\ickoto\\Desktop\\in_1000.txt";
    static DateTimeFormatter format = DateTimeFormatter.ofPattern("d.M.yyyy");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws DALException, IOException {
        
        
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private static void insertCitizens(String path) throws DALException{
        try {
            int counter = 0;

            String line = null;

            File project = new File(path);

            FileReader fr = new FileReader(project);

            BufferedReader buffer = new BufferedReader(fr);

            int N = Integer.parseInt(buffer.readLine());

            
            Citizen person = null;
           

            
            while ((line = buffer.readLine()) != null) {
                counter++;

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
        //System.out.println(split.length);
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
            //System.out.println(split.length);
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
                    person.addEducation(education);
                    break;
                case "S":
                    education = new SecondaryEducation(split[i + 1],
                            LocalDate.parse(split[i + 2], format),
                            LocalDate.parse(split[i + 3], format));
                    if (!(LocalDate.parse(split[i + 3], format).isAfter(LocalDate.now()))) {
                        ((GradedEducation) education).gotGraduated(Float.parseFloat(split[i + 4]));
                    }
                    person.addEducation(education);
                    break;
                case "M":
                    education = new HigherEducation(split[i + 1],
                            LocalDate.parse(split[i + 2], format),
                            LocalDate.parse(split[i + 3], format),
                            EducationDegree.Master);
                    if (!(LocalDate.parse(split[i + 3], format).isAfter(LocalDate.now()))) {
                        ((GradedEducation) education).gotGraduated(Float.parseFloat(split[i + 4]));
                    }
                    person.addEducation(education);
                    break;
                case "B":
                    education = new HigherEducation(split[i + 1],
                            LocalDate.parse(split[i + 2], format),
                            LocalDate.parse(split[i + 3], format),
                            EducationDegree.Bachelor);
                    if (!(LocalDate.parse(split[i + 3], format).isAfter(LocalDate.now()))) {
                        ((GradedEducation) education).gotGraduated(Float.parseFloat(split[i + 4]));
                    }

                    person.addEducation(education);
                    break;
                case "D":
                    education = new HigherEducation(split[i + 1],
                            LocalDate.parse(split[i + 2], format),
                            LocalDate.parse(split[i + 3], format),
                            EducationDegree.Doctorate);
                    if (!(LocalDate.parse(split[i + 3], format).isAfter(LocalDate.now()))) {
                        ((GradedEducation) education).gotGraduated(Float.parseFloat(split[i + 4]));
                    }
                    person.addEducation(education);
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
