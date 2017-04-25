/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EducationStorage;

import DALException.DALException;
import education.Education;

/**
 *
 * @author ickoto
 */
public interface EducationStorage {

    public Education getEducation(int id) throws DALException;

    public int insertEducation(Education education) throws DALException;
    
    public void removeEducation(int id) throws DALException;

}
