/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg03_consoleio_conditionaloperators_loops;
import java.util.Scanner;
public class Task1b_ReadLine {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
          String introduce = sc.nextLine();
        System.out.printf("Your input was: %s%n",introduce);
    }
    
}
