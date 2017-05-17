package Employee;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
            String email = "";
            int age = -1;

            switch (split.length) {
                case 4:
                    employees[i] = new Employee(name, salary, position, department);
                    break;
                case 5:
                    try {
                        age = Integer.parseInt(split[4].trim());
                            
                        employees[i] = new Employee(name, salary, position, department, age);

                    } catch (NumberFormatException ex) {
                        email = split[4].trim();
                        employees[i] = new Employee(name, salary, position, department, email);
                    }
                    break;
                case 6:
                    age = Integer.parseInt(split[4].trim());
                    email = split[5].trim();
                    employees[i] = new Employee(name, salary, position, department, age, email);
                    break;
                default:
                    break;

            }
        }

        for (int j = 0; j < 3; j++) {
            double max = 0;
            int idx = 0;
            String result = "";
            for (int k = 0; k < n; k++) {
                if ((employees[k] != null) && (max < employees[k].salary)) {
                    max = employees[k].salary;
                    result = employees[k].toString();
                    idx = k;
                }

            }

            System.out.println(result);
            employees[idx] = null;

            System.out.println();

        }

    }

}
