/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Task4_Market;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ickoto
 */
public class Person {

    private String name;
    private double balance;
    List<Product> basket = new ArrayList();

    private void setName(String setName) {
        this.name = setName;
    }

    protected void setBalance(double money) {
        if (money >= 0) {
            this.balance = money;
            
        }else
        {
            this.balance = -1;
        }
        
        
    }
    protected String getName()
    {
        return this.name;
    }
    protected double getBalance()
    {
       
        
            return this.balance;
        
       
    }
    
     public Person(String name,double balance)
     {
         this.setName(name);
         this.setBalance(balance);
         
     }
}
