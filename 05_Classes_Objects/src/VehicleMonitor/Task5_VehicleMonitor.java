/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VehicleMonitor;

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
        Vehicle.fuelPrice = F;

        Vehicle[] vehicles = new Vehicle[N];

        for (int i = 0; i < N; i++) {

            String product = sc.nextLine();
            String[] split = product.split(",");

            String type = split[0].trim();
            String model = split[1].trim();
            int power = Integer.parseInt(split[2].trim());
            double fuelConsumption = Double.parseDouble(split[3].trim());
            int yearProduced = Integer.parseInt(split[4].trim());
            int distance = Integer.parseInt(split[5].trim());

            if (split.length == 6) {

                vehicles[i] = new Vehicle(type, model, power, fuelConsumption, yearProduced, distance);
                

            } else {
                int weight = Integer.parseInt(split[6].trim());
                String color = split[7].trim();
                vehicles[i] = new Vehicle(type, model, power, fuelConsumption, yearProduced, distance, weight, color);
            }
            System.out.println();
        }

        for (int j = 0; j < N; j++) {
            String licenseNo = String.format("%04d", j);

            System.out.println(licenseNo + " - " + vehicles[j].toString());

            System.out.println();

        }

    }
}
