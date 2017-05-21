package EducationStorage;

import DALException.DALException;
import education.Education;
import java.util.List;

public interface EducationStorage {

    public Education getEducation(int id) throws DALException;

    public int insertEducation(Education education) throws DALException;

    public void removeEducation(int id) throws DALException;

    public void removeEducations(int id) throws DALException;

    public void removeEducationMatches(int id) throws DALException;

    public List<Education> getEducations(int id) throws DALException;

}
