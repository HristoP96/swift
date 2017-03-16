/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Task3_BankManagement;

import java.util.Scanner;

/**
 *
 * @author ickoto
 */
public class Task3_BankManagement {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
       int n = 5;
        String command;
        Account[] accs;
        Bank bg = new Bank( );
        bg.accs = new Account[n];
        //do {
            command = sc.nextLine();

            String[] split = command.split(" ");

            switch (split[0]) {
                case "OPEN":
                    for (int i = 0; i < n; i++) {
                        if (j >= bg.accs.length) {
                            System.out.println(split[0] + " fail");

                        } else {
                            bg.accs[j] = bg.openAccount(split[1].trim(), split[2].trim(), split[3].trim(), split[4].trim());
                            System.out.println(bg.accs[j].getName() + " " + bg.accs[j].govId + " " + bg.accs[j].userName + " " + bg.accs[j].person.password);
                            j++;
                            break;
                        }
                    }

                    break;
                case "END":
                    break;
                case "CLOSE":
                    for (int i = 0; i < n; i++) {

                        if ((bg.accs[i].getUsername().equals(split[1].trim())) && (bg.accs[i].hasAccess(split[2].trim()))) {
                            if (bg.closeAccount(split[1].trim(), split[2].trim())) {
                                System.out.println(split[0] + " success");
                                break;
                            } else {
                                System.out.println(split[0] + " fail");
                            }
                        }
                    }

            }

            break;
       // } while (command.equals(
                "END") == false);

        System.out.println(bg.accs.length);
        for (int i = 0;
                i < bg.accs.length;
                i++) {
            System.out.println(bg.accs[i].getName() + " " + bg.accs[i].govId + " " + bg.accs[i].userName + " " + bg.accs[i].person.password);

        }

    }

}
