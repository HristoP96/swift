/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg03_consoleio_conditionaloperators_loops;

import java.util.Scanner;
public class Task1d_IsEvenOrOdd {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int number = sc.nextInt();
        
            if (number%2==0)
            {
                System.out.println("Even");
            }
            else System.out.println("Odd");
    }
    
}
