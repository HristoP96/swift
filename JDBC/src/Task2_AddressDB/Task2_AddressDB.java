/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Task2_AddressDB;

import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author ickoto
 */
public class Task2_AddressDB extends MySqlAddressData {

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        final String DBMS_CONN_STRING = "jdbc:mysql://localhost:3306/addressdb";
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
            case "GETAD":
                System.out.println("Address ID:");
                int add_id = sc.nextInt();
                getFullAddress(add_id);
                break;
            case "ADDAD":
                System.out.println("NewID:");
                int newID = sc.nextInt();
                System.out.println("Street:");
                int str_id = sc.nextInt();
                System.out.println("Number");
                int number = sc.nextInt();
                System.out.println("Apartment");
                int apart = sc.nextInt();

                addAddress(newID, str_id, number, apart);
                break;
            case "GETADS":
                sc.nextLine();
                System.out.println("Municipality Name:");
                String m_name = sc.nextLine();

                getAddresses(m_name);
                break;

        }
    }
}
