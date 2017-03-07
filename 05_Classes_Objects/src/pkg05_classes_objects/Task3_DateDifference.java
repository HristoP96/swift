/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg05_classes_objects;
import java.util.Scanner;
/**
 *
 * @author ickoto
 */
public class Task3_DateDifference {
    public static void main(String[] args) {
        
    
    SwiftDate x = new SwiftDate();
        SwiftDate y = new SwiftDate();
        Scanner sc = new Scanner(System.in);
        
        x.year = sc.nextInt();
        x.month = sc.nextInt();
        x.day = sc.nextInt();
        
        y.year = sc.nextInt();
        y.month= sc.nextInt();
        y.day = sc.nextInt();
        
        System.out.println(x.getDaysDifference(y.year, y.month,  y.day));
        System.out.println(x.getInfo());
        System.out.println(y.getInfo());
    }
}
