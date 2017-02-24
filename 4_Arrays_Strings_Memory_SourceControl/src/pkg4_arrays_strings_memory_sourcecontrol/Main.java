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
public class Main {

    /**
     * @param args the command line arguments
     */
   
	public static void main(String[] args){
        
        int[] arr = { -5, 10, 2, 11, -11, 9, -4, -3, 27, 4, -4};
        
        for(int number : arr){
            if(number < 0) {
                System.out.print("-");
            }
            else if(number == 0) {
                System.out.print("0");
            }
            else {
                System.out.print("+");
            }
            
            System.out.print(" ");
        }
    }
}

    

