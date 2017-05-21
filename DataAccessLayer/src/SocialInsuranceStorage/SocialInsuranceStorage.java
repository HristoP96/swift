package SocialInsuranceStorage;

import DALException.DALException;
import insurance.SocialInsuranceRecord;
import java.util.List;
import personaldetails.Citizen;

public interface SocialInsuranceStorage {

    public SocialInsuranceRecord getSocialInsurance(int id) throws DALException;

    public int insertSocialInsurance(SocialInsuranceRecord socialInsurance, int id) throws DALException;

    public void removeSocialInsurance(int id) throws DALException;

    public void insertInsurances(Citizen person) throws DALException;

    public void removeInsurances(int id) throws DALException;

    public List<SocialInsuranceRecord> getInsurances(int id) throws DALException;

}
