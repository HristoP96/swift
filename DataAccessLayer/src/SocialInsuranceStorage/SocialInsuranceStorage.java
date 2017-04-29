/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SocialInsuranceStorage;

import DALException.DALException;
import insurance.SocialInsuranceRecord;
import personaldetails.Citizen;

/**
 *
 * @author ickoto
 */
public interface SocialInsuranceStorage {
    
    
     public SocialInsuranceRecord getSocialInsurance(int id) throws DALException;

    public int insertSocialInsurance(SocialInsuranceRecord socialInsurance,int id) throws DALException;
    
    public void removeSocialInsurance(int id) throws DALException;
    
    public void insertInsurances (Citizen person) throws DALException;
    
}
