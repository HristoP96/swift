/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg4_arrays_strings_memory_sourcecontrol;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author ickoto
 */
public class Task4b_PrintLetterCount {
    public static void main(String[] args) {
            Map<Character, Integer> map = new HashMap<Character, Integer>();
            Scanner sc  = new Scanner(System.in);
            
            
            String name =  sc.nextLine();
            int cnt =  1;
            
            for (int i = 0; i < name.length(); i++) {
                Character ch = name.charAt(i);
                ch = Character.toLowerCase(ch);
                if (map.containsKey(ch)) {
                    int count = map.get(ch);
                    count += 1;
                    map.put(ch, count);
                } else {
                    map.put(ch, 1);
                }
            }
            
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                System.out.println(pair.getKey() + " = " + pair.getValue());
                it.remove(); // avoids a ConcurrentModificationException
            }
                    
                   
    }
    
}
