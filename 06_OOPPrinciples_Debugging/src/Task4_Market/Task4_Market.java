/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Task4_Market;

import java.util.Scanner;

/**
 *
 * @author ickoto
 */
public class Task4_Market {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String command;
        int operations = 1;
        String[] splitObj;
        Person[] clients = null;
        String[] split;
        Product[] products = null;

        do {
            command = sc.nextLine();

            if (command.equals("END")) {
                for (int i = 0; i < clients.length; i++) {
                    String produktB = "";
                    for (int j = 0; j < clients[i].basket.size(); j++) {

                        produktB = clients[i].basket.get(j).getName();
                        if (clients[i].basket.size() > 1) {
                            produktB = clients[i].basket.get(j).getName() + ", " + produktB;
                        }

                    }
                    if (clients[i].basket.isEmpty()) {
                        System.out.println(clients[i].getName() + " – Nothing bought ");
                    } else {
                        System.out.println(clients[i].getName() + "-" + produktB);
                    }

                }
                break;
            }
            switch (operations) {
                case 1:
                    splitObj = command.split(";");                    
                    clients = new Person[splitObj.length];
                    for (int i = 0; i < clients.length; i++) {
                        split = splitObj[i].split("=");
                        clients[i] = new Person(split[0].trim(), Double.parseDouble(split[1].trim()));
                        if (clients[i].getBalance()<0) {
                            System.out.println("Balance can’t be negative.");
                            command = "END";
                            

                        }
                    }
                    operations++;
                    break;
                case 2:
                    splitObj = command.split(";");                    
                    products = new Product[splitObj.length];
                    for (int i = 0; i < products.length; i++) {
                        split = splitObj[i].split("=");
                        products[i] = new Product(split[0].trim(), Double.parseDouble(split[1]));
                        if(products[i].getPrice()<0){
                            System.out.println("Price cant be negative");
                            command = "END";
                        }
                    }
                    operations++;
                    break;
                default:
                    split = command.split(" ");
                    String clientName = "";
                    String productName = "";
                    double cost = 0;
                    double amount = 0;
                    switch (split.length) {
                        case 2:

                            for (int i = 0; i < split.length; i++) {
                                for (int j = 0; j < clients.length; j++) {
                                    if (split[i].trim().equals(clients[j].getName())) {
                                        clientName = split[i].trim();
                                        amount = clients[j].getBalance();
                                    }
                                }
                                for (int j = 0; j < products.length; j++) {
                                    if (split[i].trim().equals(products[j].getName())) {
                                        cost = products[j].getPrice();
                                        productName = split[i].trim();
                                    }
                                }
                            }
                            if (amount >= cost) {
                                System.out.println(clientName + " bought " + productName);

                                for (Person person : clients) {
                                    if (clientName.equals(person.getName())) {
                                        person.setBalance(amount - cost);
                                        for (Product product : products) {
                                            if (productName.equals(product.getName())) {
                                                person.basket.add(product);
                                            }
                                        }

                                    }
                                }
                            } else if (amount < cost) {
                                System.out.println(clientName + " can’t afford " + productName);
                            }
                            break;
                        case 3:
                            for (int i = 0; i < split.length; i++) {
                                for (int j = 0; j < clients.length; j++) {
                                    if ((split[0].trim() + " " + split[1].trim()).equals(clients[j].getName()));
                                    {
                                        clientName = (split[0].trim() + " " + split[1].trim());
                                        amount = clients[j].getBalance();
                                    }
                                }
                                for (int j = 0; j < products.length; j++) {
                                    if (split[i].trim().equals(products[j].getName())) {
                                        cost = products[j].getPrice();
                                        productName = split[i].trim();
                                    }
                                }

                            }
                            if (amount >= cost) {
                                System.out.println(clientName + " bought " + productName);

                                for (Person person : clients) {
                                    if (clientName.equals(person.getName())) {
                                        person.setBalance(amount - cost);
                                        for (Product product : products) {
                                            if (productName.equals(product.getName())) {
                                                person.basket.add(product);
                                            }
                                        }

                                    }
                                }
                            } else if (amount < cost) {
                                System.out.println(clientName + " can’t afford " + productName);
                            }
                            break;

                    }
            }

        } while (command.equals("END") == false);
        

    }

}
