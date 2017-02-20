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
public class Task4a_PrintSquare {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int star = sc.nextInt();
        int j = 1;

        for (int i = 0; i < star; i++) {
            System.out.print("* ");
            

        }
        System.out.println();
        do{
         for (int i = 0; i < star; i++)
         {
             if(i==0 || i==star-1)
             {
                 System.out.print("* ");
             }else System.out.print("  ");
         }
         System.out.println();
         j++;
        }while(j <= star -2);
        
        

        for (int i = 0; i < star; i++) {

            System.out.print("* ");
            
        }
        System.out.println();
       

    }

}
