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
public class Task2i_ToBinaryAndHexa {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int number = sc.nextInt();

        int result = 0;
        String Binary = "";
        String rev = "";
        while (number >= 1) {
            
            result = number % 2;
            Binary += result;

            number /= 2;

        }
        
        rev = new StringBuffer(Binary).reverse().toString();//нямаше как да напиша задачата без стринг и намерих този метод да ми обърне числото
        System.out.println(rev);

    }
}
