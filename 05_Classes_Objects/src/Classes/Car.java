/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author ickoto
 */
public class Car {

    String model;
    String brand;
    double enginePower;
    int year;
    int currentYear = 2017;
    double tax;

    public int insuranceCategory() {
        if ((currentYear - year) < 0) {
            System.out.println("Wrong year");
            return 0;
        }
        if ((currentYear - year) >= 0 && (currentYear - year) <= 8) {
            return 1;
        } else if ((currentYear - year) > 8 && (currentYear - year <= 15)) {
            return 2;
        } else if ((currentYear - year) > 15 && (currentYear - year <= 25)) {
            return 3;
        } else if ((currentYear - year) > 25) {
            return 4;
        }
        return -1;

    }

    public double carTax() {
        switch (insuranceCategory()) {
            case 1:

                this.tax = 150;
                break;
            case 2:

                this.tax = 200;
                break;
            case 3:

                this.tax = 300;
                break;
            case 4:

                this.tax = 500;
                break;
            default:
                break;
        }
        if (this.enginePower < 80) {
            return this.tax *20/100 + this.tax;
        } else if (this.enginePower > 140) {
            return this.tax* 45/100 + this.tax;
        } else 
            return this.tax;


       
    }
}
