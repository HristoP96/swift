/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Task2_CredentialsManager;

import java.util.Scanner;

/**
 *
 * @author ickoto
 */
public class trash {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String newPass = "";

        Credentials a = new Credentials(sc.next().trim());
        String pass = sc.next();
        a.setPass(pass);
        if (a.checkOldPass(pass) == true) {
            System.out.println("success");
        } else {
            System.out.println("fail");
        }
        a.checkOldPass(pass);
        System.out.println(a.password + " " + a.name + " " + a.oldp + a.checkOldPass(pass));
        do {
            newPass = sc.next().trim();           
            a.changePass(newPass);
           
            System.out.println( a.password);

        } while ("END".equals(newPass)==false);
        String name = sc.nextLine();
        Integer.parseInt(name);
    }

}
