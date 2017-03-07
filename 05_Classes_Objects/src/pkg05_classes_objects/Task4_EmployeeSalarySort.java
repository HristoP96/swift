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
public class Task4_EmployeeSalarySort {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        Employee temp;

        Employee[] employees = new Employee[n];

        for (int i = 0; i < n; i++) {

            String resume = sc.nextLine();
            String[] split = resume.split(",");

            String name = split[0].trim();
            double salary = Double.parseDouble(split[1].trim());
            String position = split[2].trim();
            String department = split[3].trim();

            switch (split.length) {
                case 4:
                    employees[i] = new Employee(name, salary, position, department);
                    break;
                case 6:
                    int age = Integer.parseInt(split[4].trim());
                    String email = split[n - 1].trim();
                    employees[i] = new Employee(name, salary, position, department, age, email);
                    break;
                default:
                    break;

            }
        }

        for (int j = 0; j < 3; j++) {
            double max = 0;
            int idx = 0;
            for (int k = 0; k < n; k++) {
                if ((employees[k] != null) && (max < employees[k].salary)) {
                    max = employees[k].salary;
                    idx = k;
                }
            }
                if (employees[idx].email == null) {

                    System.out.println(employees[idx].name + " " + employees[idx].department + " " + employees[idx].position + " " + employees[idx].email);
                } else {
                    System.out.println(employees[idx].name + " " + employees[idx].department + " " + employees[idx].position);
                }

                employees[idx] = null;
                System.out.println();

            }

        }

    }


