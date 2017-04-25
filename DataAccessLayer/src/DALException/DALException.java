/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DALException;

import java.sql.SQLException;

/**
 *
 * @author ickoto
 */
public class DALException extends SQLException{

    public DALException(String message, Throwable cause) {
        super(message, cause);
    }

    public DALException(String massage) {
        super(massage);
    }
    
    
    
    
}
