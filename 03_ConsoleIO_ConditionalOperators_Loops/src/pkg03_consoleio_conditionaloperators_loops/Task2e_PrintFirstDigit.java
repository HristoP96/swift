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
public class Task2e_PrintFirstDigit {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int number = sc.nextInt();

        int firstDigit = number;
        while (firstDigit > 9) {
            firstDigit = firstDigit / 10;

        }
        System.out.println(firstDigit);

    }
}
