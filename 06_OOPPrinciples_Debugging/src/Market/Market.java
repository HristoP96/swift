package Market;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Market {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line;
        try {

            System.out.println("Enter clien List");
            
            line = sc.nextLine();
            List<Person> clients = Market.clientList(line);
            
            System.out.println("Enter product List");
            
            line = sc.nextLine();
            List<Product> products = Market.productList(line);
            
            System.out.println("Let's start buying");

            while (!(line = sc.nextLine()).equalsIgnoreCase("END")) {
                try {
                    Market.buy(line, clients, products);
                } catch (IllegalArgumentException ex) {
                    System.out.println(ex.getMessage());

                }
            }
                for (Person client : clients) {
                    try {
                        System.out.println(client.toString());
                    } catch (NullPointerException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            

            }catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            return;

        }

        }

    

    public static List<Person> clientList(String line) throws IllegalArgumentException {
        List<Person> clients = new ArrayList<>();
        String[] lineSplit = line.split(";");
        for (String linePart : lineSplit) {
            String[] split = linePart.split("=");

            String name = split[0].trim();
            
            double balance = Double.parseDouble(split[1].trim());
            
            clients.add(new Person(name, balance));

        }
        return clients;
    }

    public static List<Product> productList(String line) throws IllegalArgumentException {

        List<Product> products = new ArrayList<>();
        String[] lineSplit = line.split(";");
        for (String linePart : lineSplit) {
            String[] split = linePart.split("=");
             String name = split[0].trim();
             
            double price = Double.parseDouble(split[1].trim());
            
            products.add(new Product(name, price));

        }

        return products;
    }

    public static void buy(String line, List<Person> clients, List<Product> products) throws IllegalArgumentException {
        line=line.trim();
        int lasIdx = line.lastIndexOf(" ");
        String personName = line.substring(0, lasIdx);
        String productName = line.substring(lasIdx + 1);
        

        for (Person client : clients) {
            if (client.getName().equals(personName)) {
                for (Product product : products) {
                    if (product.getName().equals(productName)) {
                        client.buyProduct(product);
                        return;
                    }

                }
            }

        }

    }
}
