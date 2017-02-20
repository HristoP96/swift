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
public class Task3b_PrintSumOfN {
    public static void main(String[] args) {
        Scanner sc  =  new Scanner (System.in);
        int numbers =  sc.nextInt();
        int sum = 0;
        int i ;
        
        for(i = 0;i < numbers;i++)
        {
            int number  = sc.nextInt();
            
            sum+=number;
        }
        System.out.println(sum);
        
    }
    
}
