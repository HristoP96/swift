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
public class Task5a_StringWoVowels {
    public static void main(String[] args) {
        Scanner sc  = new  Scanner (System.in);
        
        String stc = "Peter loves dogs";
        
        for (int i = 0; i<stc.length();i++)
        {
            
            if((stc.charAt(i)!='a') && (stc.charAt(i)!='e' )&& (stc.charAt(i)!= 'o')){
                System.out.print(stc.charAt(i));
            }
        }
        
        
        
    }
    
}
