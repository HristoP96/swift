/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg05_classes_objects;

import java.util.Scanner;

/**
 *
 * @author ickoto
 */
public class Task5_VehicleMonitor {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        double F = sc.nextDouble();
        sc.nextLine();

        Vehicle[] vehicles = new Vehicle[N];

        

        for (int i = 0; i < N; i++) {
            vehicles[i] = new Vehicle();
            String product = sc.nextLine();
            String[] split = product.split(",");

            vehicles[i].type = split[0].trim();
            vehicles[i].model = split[1].trim();
            vehicles[i].power = Integer.parseInt(split[2].trim());
            vehicles[i].fuelConsumption = Double.parseDouble(split[3].trim());
            vehicles[i].yearProduced = Integer.parseInt(split[4].trim());
            vehicles[i].distance = Integer.parseInt(split[5].trim());

            if (split.length == 6) {
                vehicles[i].weight = -1;
                vehicles[i].color = "N/A";

            } else if (split.length == 8) {
                vehicles[i].weight = Integer.parseInt(split[6].trim());
                vehicles[i].color = split[7].trim();

            }
            System.out.println();
        }

        for (int j = 0; j < N; j++) {
            String licenseNo = String.format("%04d", j);
            System.out.println(licenseNo + " - " + vehicles[j].model + ", " + vehicles[j].color);
            System.out.printf("Insurance cost: %.2f - Тravel cost: %.2f ",vehicles[j].getInsurancePrice() , vehicles[j].calculateTripPrice(F, vehicles[j].distance));
            
            System.out.println();
           
        }
        
        //реших да направя задачата по различен начин , единственото нещо,което съм взел от от решенията е String licenseNo = String.format("%04d", j);,защото не можах да се сетя за алтернатива.
        
    }
}
