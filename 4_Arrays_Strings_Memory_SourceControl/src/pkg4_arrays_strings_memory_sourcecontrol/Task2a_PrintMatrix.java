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
public class Task2a_PrintMatrix {

    public static void main(String[] args) {

        int row = 4;
        int col = 4;
        int[][] arr;

        arr = new int[row][col];

        int i, j;
        int idx = 1;
        int temp;
        int[][] ar = new int[col][row];

        for (i = 0; i < row; i++) {

            for (j = 0; j < col; j++) {
                arr[j][i] = idx;

                idx++;              

            }

        }

        for (i = 0; i < col; i++) {
            for (j = 0; j < row; j++) {

            }
            System.out.println();
        }
    }
}
