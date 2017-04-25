/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdressStorage;

import DALException.DALException;
import address.Address;

/**
 *
 * @author ickoto
 */
public interface AddressStorage {
    
    
    public Address getAddress(int id) throws DALException;
    
    public int insertAddress(Address address) throws DALException;
    
    public void removeAdress(int id) throws DALException;
    
}
