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
public class Task1f_ReadNNumbersOnNewLines {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        
        int broi = sc.nextInt();
        int i = 0;
        int number;
        String  draft="";
        
        
        for(i=0;i<broi;i++)
        {
            
            number = sc.nextInt();
            
            draft+= number + " " ;
            
                    
          
             
            
            
             
            
            
            
        }
       
       System.out.println(draft);
       
        
        
        
        
    }
    
}
