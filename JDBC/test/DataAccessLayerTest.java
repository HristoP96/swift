/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import DataAccessLayer.MySqlAddressStorage;
import DataAccessLayer.Adress;
import DataAccessLayer.DALException;

/**
 *
 * @author ickoto
 */
public class DataAccessLayerTest extends MySqlAddressStorage {

    MySqlAddressStorage test = new MySqlAddressStorage();
    Adress sample  ;

    @Test
    public void testIfEmpty() throws DALException {

        assertNotNull(test.getAddress(3));
        

    }

    @Test
    public void getAdressTest() throws DALException {

        final String DBMS_CONN_STRING = "jdbc:mysql://localhost:3306/citizen_registrations";
        test.setUrl(DBMS_CONN_STRING);
        final String DBMS_USERNAME = "root";
        test.setUserName(DBMS_USERNAME);
        final String DBMS_PASSWORD = "ickotomf56";
        test.setPass(DBMS_PASSWORD);

        assertTrue(test.getAddress(3).toString(), test.getAddress(3) instanceof Adress);

    }

    @Test
    public void testDALException() throws DALException {
        try {
            final String DBMS_CONN_STRING = "jdbc:mysql://localhost:3306/citizen_registrations";
            test.setUrl(DBMS_CONN_STRING);
            final String DBMS_USERNAME = "root";
            test.setUserName(DBMS_USERNAME);
            final String DBMS_PASSWORD = "ickotomf56";
            test.setPass(DBMS_PASSWORD);

            test.getAddress(4);

        } catch (DALException ex) {
            fail("DALException caught");

        }
    }

    @Test
    public void insertAddressTest() throws DALException {
        final String DBMS_CONN_STRING = "jdbc:mysql://localhost:3306/citizen_registrations";
        test.setUrl(DBMS_CONN_STRING);
        final String DBMS_USERNAME = "root";
        test.setUserName(DBMS_USERNAME);
        final String DBMS_PASSWORD = "ickotomf56";
        test.setPass(DBMS_PASSWORD);
        String  country = "Serbia";
        String city = "Belgrade";
        String municipaluty =  "Belgrade";
        String postal_code = "1200";
        String street = "Skadarlija";
        String number = "12";
        int floor = 3;
        int apartmentNo=5;
        sample = new Adress(country, city, municipaluty, postal_code, street, number, floor, apartmentNo);
        
        
        assertNull("Wrong adress  insert",  test.insertAddress(sample));
    }

}
