/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg03_consoleio_conditionaloperators_loops;

/**
 *
 * @author ickoto
 */
public class Task5c_PrintEverySentence {
    public static void main(String[] args) {
        String stc = "Peter loves dogs.He doesn’t like cats.Maria likes cats";
       
        
        
       for(String  dot :stc.split("\\."))
       {
           System.out.println(dot);
       }
    }
    
}
