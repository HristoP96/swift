package BankManagement;

import java.util.LinkedList;
import java.util.List;

public class Bank {

    public List<Account> accs = new LinkedList<>();

    private static double assets = 0.0;

    public Bank() {

    }

    public void openAccount(String username, String password, String name, String govId) {

        if (!(accs.size() < 5)) {
            throw new ArrayIndexOutOfBoundsException("OPEN fail");
        } else {
            accs.add(new Account(username, password, name, govId));
            System.out.println("OPEN success");
        }

    }

    public void closeAccount(String username, String password) {
        for (Account acc : accs) {
            if (acc.getUsername().equals(username) && acc.credential.checkPass(password)) {
                accs.remove(acc);
                System.out.println("CLOSE success");
                return;
            }

        }
        throw new IllegalAccessError("CLOSE fail");

    }

    public void deposit(String username, double amount) {
        try {
            for (Account acc : accs) {
                if (acc.getUsername().equals(username)) {
                    acc.deposit(amount);
                    assets += amount;
                    System.out.println("DEPOSIT success");
                    return;
                }
            }
            throw new IllegalAccessError("DEPOSIT fail");

        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void withdraw(String username, String password, double amount) {
        try {
            for (Account acc : accs) {
                if (acc.getUsername().equals(username)) {
                    acc.withdraw(amount, password);
                    assets -= amount;
                    System.out.println("WITHDRAW success");
                    return;
                }
            }
            throw new IllegalAccessError("WITHDRAW fail");
        } catch (IllegalAccessError ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void transfer(String username, String password, double amount, String recipient) {
        try {
            for (Account acc : accs) {
                if (acc.getUsername().equals(username)) {
                    acc.withdraw(amount, password);
                    for (Account accRecipient : accs) {
                        if (accRecipient.getUsername().equals(recipient)) {
                            accRecipient.deposit(amount);
                            System.out.println("TRANSFER success");
                            return;
                        }
                    }
                }
            }
            throw new IllegalAccessError("TRANSFER fail");

        } catch (IllegalAccessError ex) {
            System.out.println("TRANSFER fail");
        } catch (IllegalArgumentException ex) {
            System.out.println("TRANSFER fail");
        }

    }

    protected static double getAssets() {
        return assets;
    }

}
