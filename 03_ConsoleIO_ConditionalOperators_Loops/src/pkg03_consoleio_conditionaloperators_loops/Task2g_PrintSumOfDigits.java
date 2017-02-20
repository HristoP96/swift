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
public class Task2g_PrintSumOfDigits {
    public static void main(String[] args) {
        Scanner sc =  new Scanner(System.in);
        
        
        int number = sc.nextInt();
        
        
        int sum=0;
        int sumant;
        
        while(number !=0)
        {
            sumant = number%10;
            sum+=sumant;
            number /=10;
        }
        
        System.out.println(sum);




    }
    
}
