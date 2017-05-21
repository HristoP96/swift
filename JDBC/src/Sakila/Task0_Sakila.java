
package Sakila;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;


public class Task0_Sakila {

    

    

    public static void main(String[] args) throws SQLException {

        Scanner sc = new Scanner(System.in);
        System.out.println(LocalDate.now());
        System.out.println("Set command:");
        String command = sc.next();
        String firstName;
        String lastName;
        short actorId;
        short filmID;
        switch (command) {
            case "AF":
                System.out.println("Set actor_id  film_id   timeStamp");
                actorId = sc.nextShort();
                filmID = sc.nextShort();
                
                InsertFilmActor.insert(actorId, filmID);
                
                break;
            case "DAF":
                System.out.println("Insert direct match in film: ");
                filmID = sc.nextShort();
                System.out.println("Actor's firstName , lastNmae");
                firstName = sc.next();
                lastName = sc.next();
                
                InsertFilmActor.insert(AddActor.addActor(firstName, lastName), filmID);
                break;
            case "ADDA":
                System.out.println("Set first_name  last_name  ");
                firstName = sc.next();
                lastName = sc.next();
                AddActor.addActor(firstName, lastName);
                
                break;
            case "RES":
                System.out.println("Results for: ");
                String a = sc.next();

                switch (a) {
                    case "actors":
                        ActorsInfo.getActorsInfo();
                        break;
                    case "AF":
                        ActorsFIlmInfo.getInfo();
                        break;
                }
                break;
       }
    }
}
