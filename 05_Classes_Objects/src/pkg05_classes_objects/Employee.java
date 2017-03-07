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
public class Employee {
    
    String name;
    double salary;
    String position;
    String department;
    int age ;
    String email;
    
   
    public Employee(String name, double salary, String position, String department,int age,String email)
    {
       this.name = name;
       this.salary = salary;
       this.position = position;
       this.department = department;
       this.age = age;
       this.email = email;
    }
    public Employee(String name, double salary, String position, String department)
    {
        this(name, salary, position, department, -1, null);
    }
    
}
