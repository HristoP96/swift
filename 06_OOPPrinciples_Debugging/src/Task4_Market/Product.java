/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Task4_Market;

/**
 *
 * @author ickoto
 */
public class Product {

     private String name;

    private void setName(String setName) {
        name = setName;
    }

    protected String getName() {
        return name;
    }
     private double price;

    private void setPrice(double setPrice) {
        if (setPrice >= 0) {
            price = setPrice;
            
        }
        else this.price = -1;
    }

    protected double getPrice() {
        
            return price;
        
        

    }
   public Product(String name, double price)
   {
       this.setName(name);
       this.setPrice(price);
   }

}
