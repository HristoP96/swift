package CredentialsManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author ickoto
 */
public class Task2_CredentialsManager {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Credential person = null;
        String name = "";
        String newPass = "";
        String pass = "";

        Map<String, Credential> database = new HashMap<>();

        String command;
        do {
            command = sc.next();

            switch (command) {
                case "END":
                    break;
                case "ENROLL":
                    name = sc.next().trim();
                    pass = sc.next().trim();
                    database.put(name, new Credential(name, pass));
                    System.out.println("ENROLL" + " success ");
                    break;
                case "CHPASS":
                    name = sc.next().trim();
                    pass = sc.next().trim();
                    newPass = sc.next().trim();
                    person = database.get(name);
                    if (person == null) {
                        System.out.println("CHPASS fail");
                    } else if (person.changePass(newPass, pass)) {
                        System.out.println("CHPASS success");
                    } else {
                        System.out.println("CHPASS fail");

                    }
                    break;
                case "AUTH":
                    name = sc.next().trim();
                    pass = sc.next().trim();
                    person = database.get(name);
                    if (person == null) {
                        System.out.println("AUTH fail");
                    } else if (person.checkPass(pass)) {
                        System.out.println("AUTH success");
                    } else {
                        System.out.println("AUTH fail");
                    }

                    break;
            }

        } while (!command.equals("END"));

    }

}
