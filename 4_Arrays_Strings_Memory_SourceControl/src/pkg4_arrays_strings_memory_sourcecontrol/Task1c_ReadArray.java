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
public class Task1c_ReadArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
            int N = sc.nextInt();
            
            for(int i =0;i<N;i++)
            {
                int number = sc.nextInt();
                
                System.out.print(number+" ,");
            }
    }
    
}
