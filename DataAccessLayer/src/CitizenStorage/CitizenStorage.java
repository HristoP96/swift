/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CitizenStorage;

import DALException.DALException;
import address.Address;
import education.Education;
import insurance.SocialInsuranceRecord;
import java.util.List;
import java.util.Stack;
import personaldetails.Citizen;

/**
 *
 * @author ickoto
 */
public interface CitizenStorage {
    public Citizen getCitizen(int id) throws DALException;
    
    public int insertCitizen(Citizen citizen) throws DALException;
    
    public void removeCitizen(int id) throws DALException;
}
