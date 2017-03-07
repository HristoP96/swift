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
public class SwiftDate {
    
    int year;
    int month;
    int day;
    int century;
    String Lyear;
    
    boolean isLeapYear()
    {
        if ((this.year %4 ==0 && this.year%100!=0) || this.year%400==0)
        {
           
            return true;
        }
        
        return false;
    }
    int getCentury()
    {
        this.century = (this.year / 100) + 1;
        
        return this.century;
    }
    
    int getDaysDifference(int swiftY,int swiftM,int swiftD)
    {
        int diff = Math.abs((this.day - swiftD) + ((this.month - swiftM) * 30) + ((this.year - swiftY)*365));
        return diff;
    }
    String getInfo()
    {
        if(isLeapYear())
        {
            return this.year +" "+ this.month +" " +this.day +" - " + getCentury() +" century ." + "It is LEAP year" ;
        }
        
        return this.year +" "+ this.month +" " +this.day +" - " + getCentury() +" century ";
       
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
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
