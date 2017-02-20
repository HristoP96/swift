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
public class Task4b_PrintZFigure {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int star =  sc.nextInt();
        
        for(int i = 0;i<star;i++)
        {
            System.out.print("* ");
        }
        System.out.println();
       for(int i =1 ; i<=star-2;i++)
       {
           for(int j = 1 ; j <star - i;j++){
               System.out.print("  ");
           }
           System.out.println("* ");
       }
        
        
        
        
        for(int i = 0;i<star;i++)
        {
            System.out.print("* ");
        }
        System.out.println();
    }
    
}
