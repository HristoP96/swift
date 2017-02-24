/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg4_arrays_strings_memory_sourcecontrol;
import java.util.Scanner;

/**
 *
 * @author ickoto
 */
public class Task4d_ConvertibleStrings {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String name = sc.nextLine();
        String code = sc. nextLine ();
        
        
        char[] ch = name.toCharArray();
        
       // for(char c:ch)
       /*     
        {
            if(code.equals("encode") && c>='a' && c<='z' ){
           c++;
           ch[]=c;
           System.out.print(ch);
            }else if (code.equals("decode") && c>='a' && c<='z' )
            {
                c--;
                
               System.out.print(c);//J ibuf tusjoht opx.
            }
        }
        System.out.println(ch);
*/
       for (int i = 0;i<ch.length;i++)
       {
           if(code.equals("encode") && (ch[i]>='a'&& ch[i]<='z')|(ch[i]>='A'&& ch[i]<='Z')){
           ch[i]++;
           }
           if (code.equals("decode")&& (ch[i]>='a'&& ch[i]<='z')|(ch[i]>='A'&& ch[i]<='Z') ){
               ch[i]--;
           }
       }
        System.out.println(ch);
    }
}
