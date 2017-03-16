/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Task3_BankManagement;

/**
 *
 * @author ickoto
 */
public class Bank {

    Account[] accs = new Account[5];

    static double assets = 0.0;

    static boolean notNeg(double diggit) {
        return diggit >= 0;
    }

    protected Account openAccount(String name, String govId, String username, String password) {
        return new Account(name, govId, username, password);
    }

    protected boolean closeAccount(String username, String password) {
        for (int i = 0; i < accs.length; i++) {
            if (this.accs[i].userName.equals(username) && this.accs[i].hasAccess(password)) {
                this.accs[i] = null;
                return true;
            }

        }
        return false;
    }

    protected boolean deposit(String username, double amount) {
        for (int i = 0; i < accs.length; i++) {
            if (this.accs[i].getUsername().equals(username) && notNeg(amount)) {
                this.accs[i].deposit(amount);
                assets += this.accs[i].balance;
                return true;
            }
        }

        return false;
    }

    protected boolean withdraw(String username, String password, double amount) {
        for (int i = 0; i < accs.length; i++) {
            if ((this.accs[i].userName.equals(username) && this.accs[i].hasAccess(password)) && (notNeg(amount) && amount < this.accs[i].getBalance())) {
                this.accs[i].withdraw(amount);
                assets -= amount;
                return true;
            }
        }

        return false;
    }

    protected boolean transfer(String username, String password, double amount, String recipient) {
        if (notNeg(amount) == true) {
            for (int i = 0; i < accs.length; i++) {
                if (this.accs[i].userName.equals(username) && this.accs[i].hasAccess(password)) {
                    this.accs[i].withdraw(amount);
                }
                for (int j = 0; j < accs.length; j++) {
                    if (this.accs[j].userName.equals(recipient)) {
                        this.accs[j].deposit(amount);
                        return true;
                    }
                }

            }
        }
        return false;
    }
    
    protected static double getAssets()
    {
        return assets;
    }

    public Bank() {
        this.accs = new Account[5];

    }

}
