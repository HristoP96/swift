/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Task1_CalculatorClient;
import java.util.Scanner;

/**
 *
 * @author ickoto
 */
public class Task1_CalculatorClient {
    
    public static void main(String[] args) {
        Scanner sc =  new Scanner (System.in);
        
        String command;
        Calculator client = new Calculator();
        
        do 
        {
            
            
            command =  sc.next();
            if(command.equals("END"))
            {
                break;
            }
            double a = sc.nextDouble();
            double b = sc.nextDouble();
            
            switch (command)
            {
                case "SUM":
                    System.out.printf("%.3f",client.sum(a, b));
                    break;
                case "MUL":
                    System.out.printf("%.3f",client.multiply(a, b));
                    break;
                case "SUB":
                    System.out.printf("%.3f",client.subtract(a, b));
                    break;
                case "DIV":
                    System.out.printf("%.3f",client.divide(a, b));
                    break;
                case "PER":
                    System.out.printf("%.3f",client.percenage(a, b));
                    break;
            }
            System.out.println();
            
                
            
        }while (command.equals("END")== false);
    }
    
}
