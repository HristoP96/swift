/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Task3_BankManagement;

import Task2_CredentialsManager.Credentials;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author ickoto
 */
public class trash {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //Credentials person  =  new Credentials(sc.next().trim());
        Bank dsk = new Bank();
        int cnt = 0;
        String command;
        do {
            command = sc.nextLine();
            String[] split = command.split(" ");

            switch (split[0].trim()) {
                case "OPEN":

                    if (cnt < 5) {
                        dsk.accs[cnt] = dsk.openAccount(split[3].trim(), split[4].trim(), split[1].trim(), split[2].trim());
                        cnt++;
                        System.out.println(split[0] + " " + "success");
                    } else if (cnt >= 5 && (Arrays.asList(dsk.accs).contains(null) == false)) {
                        System.out.println(split[0] + " fail");
                    }
                    if (cnt >= 5 && Arrays.asList(dsk.accs).contains(null)) {
                        for (int i = 0; i < dsk.accs.length; i++) {
                            if (dsk.accs[i] == null) {
                                dsk.accs[i] = dsk.openAccount(split[3].trim(), split[4].trim(), split[1].trim(), split[2].trim());
                                System.out.println(split[0] + " " + "success");
                            }
                        }
                    }

                    break;
                case "CLOSE":

                    if (dsk.closeAccount(split[1].trim(), split[2].trim())) {
                        System.out.println(split[0] + " success");
                        break;
                    } else {
                        System.out.println(split[0] + " fail");

                        break;
                    }
                case "DEPOSIT":
                    if (dsk.deposit(split[1].trim(), Double.parseDouble(split[2].trim()))) {
                        System.out.println(split[0] + " succes");
                    } else {
                        System.out.println(split[0] + " fail");
                    }
                    break;
                case "WITHDRAW":
                    if (dsk.withdraw(split[1].trim(), split[2].trim(), Double.parseDouble(split[3].trim()))) {
                        System.out.println(split[0] + " succes");
                    } else {
                        System.out.println(split[0] + " fail");
                    }
                    break;
                case "TRANSFER":
                    if (dsk.transfer(split[1].trim(), split[2].trim(), Double.parseDouble(split[3].trim()), split[4].trim())) {
                        System.out.println(split[0] + " success");
                    } else {
                        System.out.println(split[0] + " fail");
                    }
                    break;
                case "END":
                    for (int i = 0; i < 5; i++) {

                        System.out.println(dsk.accs[i].getName() + "," + dsk.accs[i].getGovId() + "," + dsk.accs[i].getBalance());
                    }
                    System.out.println(Bank.getAssets());
                    break;
            }

        } while (command.equals("END") == false);

    }
}
