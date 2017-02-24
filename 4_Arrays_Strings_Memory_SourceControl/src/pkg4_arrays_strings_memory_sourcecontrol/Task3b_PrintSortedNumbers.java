/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg4_arrays_strings_memory_sourcecontrol;

import java.util.Scanner;
import java.util.Arrays;
/**
 *
 * @author ickoto
 */
public class Task3b_PrintSortedNumbers {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
       
        int n = sc.nextInt();
        int []arr = new int [n];
        
        for(int i = 0; i <n;i++)
        {
            arr[i] =  sc.nextInt();
            
            
        }
       Arrays.sort(arr);
       
        for (int i =0; i<n ; i++)
        {
            System.out.print(arr[i] + " ");
        }
        

    }
}
