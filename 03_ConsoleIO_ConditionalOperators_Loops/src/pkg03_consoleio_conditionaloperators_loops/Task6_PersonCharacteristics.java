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
public class Task6_PersonCharacteristics {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        
        for(int i = 0 ; i<3;i++){
        int age = sc.nextInt();
        String name = sc.next();
        String vorname = sc.next();
        int year = sc.nextInt();
        double weight = sc.nextDouble();
        int height = sc.nextInt();
        String prof = sc.next();
        
            System.out.printf("%s %s is %d years old .He was born in %d .His weight  is %f and he is %d cm  tall.He is a %s",name , vorname,age , year,weight,height,prof);
        }
                
        
    }
    
    
}
