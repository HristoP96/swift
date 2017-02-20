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
public class Task3c_PrintOnlyEvenNumbers {
    public static void main(String[] args) {
        Scanner sc =  new Scanner (System.in);
        
        int numbers = sc.nextInt();
        
        int i;
        String even = "";
        
        for (i = 0 ; i<numbers;i++)
        {
            int number = sc.nextInt();
            if ( number %2==0)
            {
                even += number +" " ;
            }
        }
        System.out.println(even);
        
    }
    
}
