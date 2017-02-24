/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg4_arrays_strings_memory_sourcecontrol;
import java.util.Scanner;

/**
 *
 * @author ickoto
 */
public class Task3a_PrintLargestNumber {
    public static void main(String[] args) {
        
        Scanner sc  =new Scanner(System.in);
        
        int  max=0 ;
        
        
        int n =  sc.nextInt();
        String result = " ";
        
        for(int i = 0 ;i<n;i++)
        {
            int number =  sc.nextInt();
            
            if(i==0)
            {
                max = number;
            }else if(max<number)
            {
                max = number;
                
                
                
            }
            result += max + " "; 
            
            System.out.println(result);
              
        }
      
        
        
    }
    
}
