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
public class Task1e_Print2dArray {
    public static void main(String[] args) {
        Scanner sc =  new Scanner(System.in);
        
        int [][] arr;
        int rows = 4;
        int col = 4;
        int idx = 1;
        arr = new int [rows][col];
        
        
        
        for(int i = 0;i < col;i++)
            
        {
            for(int j=0 ; j<col;j++)
            {
                arr[i][j]=idx;
                idx++;
                
                System.out.print(arr[i][j]+ " ");
            }
            System.out.println();
        }
                
        
        
    }
    
    
}
