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
public class Task2b_PrintNonDivisors {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int i;
        
        for (i = 0;i<=n; i++)
                {
                    if(i%7!=0 && i%3!=0)
                    {
                        System.out.printf("%d ",i);
                    }
                }

    }
}
