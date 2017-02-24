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
public class Task4a_IsPalindrome {
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        String reverse = "";
        
        
        String name  =  sc.nextLine();
        
        for(int i =name.length()-1; i>=0;i--)
        {
            
             reverse +=name.charAt(i);
        }
        if(name.equals(reverse))
        {
            System.out.println("true");
        }else
            System.out.println("false");
        
        
    }
}
