/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Task3_BankManagement;


import Task2_CredentialsManager.Credentials;

/**
 *
 * @author ickoto
 */
public class Account  {
    

     String name;
    String govId;
    String userName;
    double balance = 0.0;
    
    Credentials person = new Credentials(this.name);

    protected void deposit(double amount) {
       this.balance= person.sum(this.balance, amount);
    }

    protected void withdraw(double amount) {
      this.balance =  person.subtract(this.balance, amount);
       
    }

    protected double getBalance() {
        return this.balance;
    }

    protected boolean hasAccess(String password) {
        return person.password.equals(password);
    }

    String getUsername() {
        return this.userName;
    }

    String getGovId() {
        return this.govId;
    }

    String getName() {
        return this.name;
    }
    
    public Account(String name , String govId,String username, String password)
    {
        this.name = name;
        this.govId=govId;
        this.userName =username;
        this.person = new Credentials(name);
        this.person.password = password;
        
    }
    
    public static void main(String[] args) {
        Account acg = new Account("kiro", "e21ereqw", "masdak", "123");
        acg.deposit(66);
        acg.withdraw(89);
        acg.deposit(23);
        System.out.println(acg.balance);
    }
}
