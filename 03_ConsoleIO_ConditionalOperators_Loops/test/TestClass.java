/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg03_consoleio_conditionaloperators_loops;

import java.util.Scanner;

/**
 *
 * @author ickoto
 */
public class TestClass {
    
    
    public static void main(String[] args) {
    
      
        Scanner sc = new Scanner(System.in);
        int n;
        int i = 0, j = 0;
        
        System.out.print("Submit a value for n: ");
        n = sc.nextInt();
        for (; j < n;) {
            if ((i == 0) || (j == 0) || (j == n-1)) 
                System.out.print("* ");
            else
                System.out.print("  ");
            i += 1;
            if (i == n-1) {
                System.out.println("*");
               // System.out.println();
                i = 0;
                j+=1;
            }
            
        }
    

}
}
