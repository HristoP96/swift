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
public class Vehicle {
    
    String type;
    String model;
    int power;
    double fuelConsumption;
    int yearProduced;
    int distance;
    int weight;
    String color;

    Vehicle() {
       
    }
   
    
    public Vehicle(String type,String model,int power,double Consumption,int year,int distance,int weight,String color)
    {
        
        this.type = type;
        this.model = model;
        this.power = power;
        this.fuelConsumption = Consumption;
        this.yearProduced = year;
        this.distance=distance;
        
        this.weight = weight;
        this.color = color;
        
    }
     public Vehicle(String type,String model,int power,double Consumption,int year,int distance)
    {
       
        this.type = type;
        this.model = model;
        this.power = power;
        this.fuelConsumption = Consumption;
        this.yearProduced = year;
        this.distance =distance;
        
        this.weight=-1;
        this.color = "N/A";
    }
     
     double calculateTripPrice(double fuelPrice, double distance)
     {
         return distance *(fuelConsumption/100)*fuelPrice;
     }
     double getInsurancePrice()
     {
         int carAge = 2017 - this.yearProduced;
         double typeCoefficient = 0.0;
           switch (this.type) 
           {
               case "car":
                   typeCoefficient = 1.00;
                   break;
               case "suv":
                   typeCoefficient = 1.12;
                   break;
               case "truck":
                   typeCoefficient = 1.20;
                   break;
               case "motorcycle":
                   typeCoefficient = 1.50;
                   break;
               default :
                   break;
                   
           }
           
           return (0.16 * this.power)* (1.25*carAge)*(0.05*this.fuelConsumption)*typeCoefficient;
           
     }
    
}
    
        

