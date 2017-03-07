/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg05_classes_objects;

/**
 *
 * @author ickoto
 */
public class Task1_Methods {
    
    
    
   static void Task1a_Printing(int age , String name)
    {
       
        System.out.println(name + " is " + age +" yeats old.");
    }
    
   
    
    static   boolean Task1b_AreEqual(int a , int b)
    {
        if(a==b)
        {
           return true;  
        }else 
        {           
            return false;
        }
    }
    
    static int Task1c_Contains(int[] arr,int x)
    {
        for (int i = 0; i < arr.length; i++) {
            
            if(arr[i]==x)
            {
                return i;
            }
        }
        return -1;
    }
    
    public static void main(String[] args) {
        
        Task1a_Printing(34,"Kircho" );
       boolean a = Task1b_AreEqual(6, 6);
       boolean b= Task1b_AreEqual(8, 6);
       boolean c=Task1b_AreEqual(6, 19);
       
        System.out.println(a );
        System.out.println(b);
        System.out.println(c);
        
        int [] array = {3,6,8,1,2,3,4};
        int result = Task1c_Contains(array, 9);
        System.out.println(result);
        
    }
    
    
    
    
}
