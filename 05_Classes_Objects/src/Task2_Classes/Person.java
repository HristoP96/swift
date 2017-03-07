/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Task2_Classes;


    public class Person {

        int age;
        String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
            if (this.age < 0 || this.age > 150) {
                System.out.println("Wrong number for age");
            }

        }

        public Person() {
            this.name = "No name";
            this.age = -1;
        }

        public Person(String personName) {
            this.name = personName;
            this.age = -1;
        }

        public Person(int personAge, String personName) {
            this.name = personName;
            this.age = personAge;

        }

        String introduce() {
            if (age == -1 && name.equals("No name")) {
                return "I am John Doe.";
            }
            if (age == -1) {
                return "Hello,I am " + getName();
            }
            return "Hello,I am " + getName() + " I am " + getAge() + " years old.";
        }

    }

    

