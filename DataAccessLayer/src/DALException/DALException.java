package DALException;

import java.sql.SQLException;

public class DALException extends SQLException{

    public DALException(String message, Throwable cause) {
        super(message, cause);
    }

    public DALException(String massage) {
        super(massage);
    }
    
    
    
    
}
