/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg4_arrays_strings_memory_sourcecontrol;

/**
 *
 * @author ickoto
 */
public class Task2b_PrintMatrix {
    public static void main(String[] args) {
        int col = 4;
        int row = 4;
        int idx = 1;
        
        int [][] arr = new int [row][col];
        
        for(int i = 0;i<row;i++)
        {
            for(int j = 0; j<col;j++)
            {
                arr[j][i] = idx;
                idx++;
                
            }
        }
        
        for(int i = 0; i<row;i++)
        {
            for(int j=0;j<col;j++)
            {
                 
              if((j+1)%2==0){
                  System.out.printf("%4d",arr[row-1-i][j]);
                } else System.out.printf("%4d",arr[i][j]);
               
                
            }
             System.out.println();
        }
        
        
        
    }
    
}
