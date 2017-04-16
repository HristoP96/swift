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
public interface AddressStorage {

    public Adress getAddress(int id) throws DALException;

    public int insertAddress(Adress address) throws DALException;
}
