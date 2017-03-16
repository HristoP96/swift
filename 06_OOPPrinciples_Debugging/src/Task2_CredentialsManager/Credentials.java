package Task2_CredentialsManager;

import Task1_CalculatorClient.Calculator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ickoto
 */
public class Credentials extends Calculator {

    Calculator calc;
    String name;
    public String password;
    static String oldp = "";

    protected void setPass(String pass) {
        this.password = pass;
        oldp += pass + ",";
    }

    private void setName(String username) {
        name = username;

    }

    public String getName() {
        return name;

    }

    protected boolean checkOldPass(String Pass) {
        String[] split = oldp.split(",");
        for (int i = 0; i < split.length; i++) {
            if (Pass.equals(split[i].trim())) {

                return true;
            }
        }

        return false;
    }

    protected boolean changePass(String newPass) {

        if (newPass.equals(this.password) || (checkOldPass(newPass) == true)) {

            return false;
        }
        setPass(newPass);
        return true;

    }

    public Credentials(String name) {
        setName(name);
        this.calc = new Calculator();
    }

}
