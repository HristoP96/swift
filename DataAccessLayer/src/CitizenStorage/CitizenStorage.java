
package CitizenStorage;

import DALException.DALException;
import personaldetails.Citizen;

public interface CitizenStorage {
    public Citizen getCitizen(int id) throws DALException;
    
    public int insertCitizen(Citizen citizen) throws DALException;
    
    public void removeCitizen(int id) throws DALException;
    
    
}
