/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessLayer;

/**
 *
 * @author ickoto
 */
public class DALException extends Exception  {

    public DALException(String massage, Throwable cause) {
        super(massage, cause);
    }

}
