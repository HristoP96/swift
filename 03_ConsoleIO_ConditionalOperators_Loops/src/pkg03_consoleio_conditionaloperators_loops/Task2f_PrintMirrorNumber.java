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
public class Task2f_PrintMirrorNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int number = sc.nextInt();
        String result = "";
        int changeNumber =0;
         
        
        
        
        while(number!=0)
        {
            changeNumber = number%10;
            result +=changeNumber;
            number/=10;
            
        }
        
        
        
        
        
        
        System.out.println(result);
    }
    
}
