/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg03_consoleio_conditionaloperators_loops;


import java.util.Scanner;
 
public class Task2c_PrintMonth {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

       int number ;
       do {
               
       number = sc.nextInt();
       
       
       switch(number){
           
           case 1:
               System.out.println("January");
               break;
           case 2:
               System.out.println("February");
               break;
           case 3:
               System.out.println("March");
               break;
           case 4:
               System.out.println("April");
               break;
           case 5:
               System.out.println("May");
               break;
           case 6:
               System.out.println("June");
               break;
           case 7:
               System.out.println("July");
               break;
           case 8:
               System.out.println("August");
               break;
           case 9:
               System.out.println("September");
               break;
           case 10:
               System.out.println("Ocotber");
               break;
           case 11:
               System.out.println("November");
               break;
           case 12:
               System.out.println("December");
               break;
           default:
               System.out.println("Error");
               break;
       }
       
       
           
       }while(number!=0);
        
    }
    
}
