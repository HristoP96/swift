/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

public class Person {

    int age;
    String name;

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    private void setAge(int age) {

        if (this.age < 0 || this.age > 150) {
            throw new IllegalArgumentException("Wrong age");
        }
        this.age = age;

    }

    public Person() {
        this.name = "No name";
        this.age = -1;
    }

    public Person(String personName) {
        this.setName(personName);
        this.age = -1;
    }

    public Person(int personAge, String personName) {
        this.setName(personName);
        this.setAge(personAge);

    }

    String introduce() {
        if (getAge() == -1 && getName().equals("No name")) {
            return "I am John Doe.";
        } else if (getAge() == -1) {
            return "Hello,I am " + getName();
        } else {
            return "Hello,I am " + getName() + " I am " + getAge() + " years old.";
        }
    }

}
