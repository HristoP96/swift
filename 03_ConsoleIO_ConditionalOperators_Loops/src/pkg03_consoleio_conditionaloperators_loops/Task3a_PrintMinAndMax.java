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
public class Task3a_PrintMinAndMax {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int numbers  = sc.nextInt();
        
       int number;
       int i ;
       int min=0 ;
       int max=0;
       for(i = 0;i<numbers;i++)
       {
           number = sc.nextInt();
           
           if(i==0)
           {
                min = number;
                max = number;
               
           }else if(min>number)
                   {
                      min  = number; 
                   }
           else if(max<number)
           {
               max = number;
           }
       }
       
        System.out.println(min +" "+ max);
    }
    
}
