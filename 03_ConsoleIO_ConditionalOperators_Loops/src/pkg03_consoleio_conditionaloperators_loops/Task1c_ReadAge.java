
package pkg03_consoleio_conditionaloperators_loops;

import java.util.Scanner;
public class Task1c_ReadAge {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
            int age =  sc.nextInt();
            
            
            String answer ;
            
            if(age >= 18)
            {
                System.out.println("Yes");
            }
            else if(age<18 && age>=1)
                System.out.println("No");
            if(age<0)
            {
                System.out.println("Error");
            }
    }
    
}
