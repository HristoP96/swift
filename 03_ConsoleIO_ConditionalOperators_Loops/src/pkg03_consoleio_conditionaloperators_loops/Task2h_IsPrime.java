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
public class Task2h_IsPrime {
    public static void main(String[] args) {
        Scanner sc =  new Scanner(System.in);
        
        int number =  sc.nextInt();
        boolean result = true;
        
        int i ;
        for( i = 2;i <number ; i++)//слагам първото число да е 2 , защото 2ката е най-малкото просто число след 1 
        {
            if(number%i==0)
            {
                
                 result =  false;
                break;
            }
            else result =  true;
            
            
            //Другият начин е ако числото има само два делителя тогва то е просто.
            //Отброяваме с брояч всички предходни числа,които като се разделят на зададеното и имат остатък 0.
            //При стойност 2 на брояча числото е просто.
            
            
             
                
            
                 
        }
        System.out.println(result);
        
     
    }
}
