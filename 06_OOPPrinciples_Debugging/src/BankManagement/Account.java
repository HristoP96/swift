package BankManagement;

import CredentialsManager.Credential;
import Calculator.Calculator;

public class Account {
    
    
    private String _name;
    private String _govId;
    private String _userName;
    private double _balance = 0.0;

    Credential credential;

    public Account(String username , String password , String name, String govId) {
        this._name = name;
        this._govId = govId;
        this._userName = username;
        this.credential = new Credential(name, password);

    }

    public void deposit(double amount) {
        if(!notNeg(amount )){
            throw new IllegalArgumentException("DEPOSIT fail");
        }
        this._balance = Calculator.sum(this._balance, amount);
    }

    public void withdraw(double amount, String password) {
        if (!(this._hasAccess(password)) || amount > this.getBalance()) {
            throw new IllegalAccessError("WITHDRAW fail");
        }
        this._balance = Calculator.subtract(this._balance, amount);

    }
    
    @Override
    public String toString(){
        return this._name + ", " + this.getGovId() + ", " + this.getBalance();
    }

    private boolean _hasAccess(String password) {
        
        return credential.checkPass(password);
    }

    public double getBalance() {
        return this._balance;
    }

    public String getUsername() {
        return this._userName;
    }

    public String getGovId() {
        return this._govId;
    }

    public String getName() {
        return this._name;
    }
    
    private static boolean notNeg(double number) {
        return number >= 0;
    }

}
