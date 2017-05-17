package Employee;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ickoto
 */
public class Employee {

    String name;
    double salary;
    String position;
    String department;
    int age;
    String email;

    public Employee(String name, double salary, String position, String department, int age, String email) {
        this.name = name;
        this.salary = salary;
        this.position = position;
        this.department = department;
        this.age = age;
        this.email = email;
    }

    public Employee(String name, double salary, String position, String department) {
        this(name, salary, position, department, -1, null);
    }

    public Employee(String name, double salary, String position, String department, int age) {
        this(name, salary, position, department, age, null);

    }

    public Employee(String name, double salary, String position, String department, String email) {
        this(name, salary, position, department, -1, email);
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setSalary(double salary) {
        this.salary = salary;
    }

    private void setPositio(String position) {
        this.position = position;
    }

    private void setDepartmnet(String department) {
        this.department = department;
    }

    private void setAge(int age) {
        this.age = age;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public double getSalary() {
        return this.salary;
    }

    public String getPosition() {
        return this.position;
    }

    public String getDepartment() {
        return this.department;
    }

    public int getAge() {
        if (this.age <= -1) {
            throw new NullPointerException("No age found");
        }
        return this.age;
    }

    public String getEmail() {
        if (this.email.isEmpty()) {
            throw new NullPointerException("No email found");
        }
        return this.email;
    }

    @Override
    public String toString() {
        try {
            return this.getName() + " " + this.getDepartment() + "," + this.getPosition() + "," + this.getSalary() + "," + this.getEmail();

        } catch (NullPointerException ex) {
            return this.getName() + " " + this.getDepartment() + "," + this.getPosition() + "," + this.getSalary();
        }
    }
}
