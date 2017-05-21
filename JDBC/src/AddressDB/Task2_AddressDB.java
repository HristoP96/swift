
package AddressDB;

import java.sql.SQLException;
import java.util.Scanner;

public class Task2_AddressDB  {

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        final String DBMS_CONN_STRING = "jdbc:mysql://localhost:3306/addressdb";
        final String DBMS_USERNAME = "root";
        final String DBMS_PASSWORD = "ickotomf56";
        
        MySqlAddressData storage = new MySqlAddressData(DBMS_CONN_STRING, DBMS_USERNAME, DBMS_PASSWORD);
        
         System.out.println("Set command:");
        String command = sc.next();

        switch (command) {
            case "GETAD":
                System.out.println("Address ID:");
                int add_id = sc.nextInt();
                storage.getFullAddress(add_id);
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

                storage.addAddress(newID, str_id, number, apart);
                break;
            case "GETADS":
                sc.nextLine();
                System.out.println("Municipality Name:");
                String m_name = sc.nextLine();

                storage.getAddresses(m_name);
                break;

        }
    }
}
