package CredentialsManager;

import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ickoto
 */
public class Credential {

    private String _name;
    private String _password;
   
    private List<String> _oldPasses = new ArrayList<>();

    

   

   

    public boolean changePass(String newPass, String oldPass) {

        if(!checkPass(oldPass)){
            return false;
        }

        if (_password.equals(newPass)) {
            return false;
        }

        if (this._oldPasses.contains(newPass)) {
            return false;
        } else {
            this._oldPasses.add(newPass);
        }
        this._password = newPass;

        return true;

    }

    public Credential(String name, String pass) {
        _setName(name);
        _setPass(pass);
        this._oldPasses.add(pass);
    }
    
     public boolean checkPass(String Pass) {

        if (this._password.equals(Pass)) {
            return true;
        }
        return false;
    }
    
    private void _setName(String username) {
        _name = username;

    }
    
    private void _setPass(String pass){
        _password = pass;
    }

    public String getName() {
        return _name;

    }
    
    
    

}
