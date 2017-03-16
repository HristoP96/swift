package Task2_CredentialsManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Scanner;

/**
 *
 * @author ickoto
 */
public class Task2_CredentialsManager {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Credentials person = null;
        String name = "";
        String newPass = "";
        String pass = "";

        String command;
        do {
            command = sc.next();
            switch (command) {
                case "END":
                    break;
                case "ENROLL":
                    person = new Credentials(sc.next().trim());
                    pass = sc.next().trim();
                    person.setPass(pass);
                    System.out.println("ENROLL" + " success ");
                    break;
                case "CHPASS":
                    name = sc.next().trim();
                    if (name.equals(person.name) == false) {
                        break;
                    }
                    pass = sc.next().trim();
                    if (pass.equals(person.password) == false) {
                        break;
                    }
                    newPass = sc.next().trim();
                    if (person.changePass(newPass)) {
                        System.out.println("CHPASS " + "success");
                    } else {
                        System.out.println("CHPASS " + "fail");
                    }

                    break;
                case "AUTH":

                    name = sc.next().trim();
                    if (name.equals(person.name) == false) {
                        break;
                    }
                    pass = sc.next().trim();

                    if (person.password.equals(pass)) {
                        System.out.println("AUTH " + "success");
                    } else {
                        System.out.println("AUTH " + "fail");
                    }

                    break;

            }

        } while (command.equals("END") != true);

    }

}
