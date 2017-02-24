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
public class Task1a_CreateArray {
    public static void main(String[] args) {
        Scanner sc =  new Scanner(System.in);
        
        int[] arr = {5,9,11,3,6,4,7};
        
        for(int i = 0;i<arr.length;i++)
        {
            System.out.println(arr[i]);
        }
    }
    
}
