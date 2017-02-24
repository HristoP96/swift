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
public class Task3c_PrintClusterCount {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];
        int count = 0;int p = 0;
        int max = 0;
        for (int j = 0; j < n; j++) {
            arr[j] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {

            for (int k = i+1; k < n; k++) {
               if(arr[i]==arr[k])
               {
                   count++;
                   
               }if(p==0)
               {
                   max = count;
               }else if(count > max)
               {
                   max = count;
               }
               
               if(arr[i]!=arr[k])
               {
                   count = 1;
               }
               
            }
            p++;

        }
        System.out.println(max);

    }
}
