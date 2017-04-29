/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MySqlDataStorage;

import AdressStorage.MySqlAddressStorage;
import CitizenStorage.MySqlCitizenStorage;
import EducationStorage.MySqlEducationStorage;
import SocialInsuranceStorage.MySqlSocialInsuranceStorage;


/**
 *
 * @author ickoto
 */
public class MySqlDataStorage {

    static final String DB_CONN_STRING = "jdbc:mysql://localhost:3306/citizen_registrations";
    static final String DB_USERNAME = "root";
    static final String DB_PASSWORD = "ickotomf56";

    public static MySqlCitizenStorage citizenStorage = new MySqlCitizenStorage(DB_CONN_STRING, DB_USERNAME, DB_PASSWORD);
    public static MySqlAddressStorage addressStorage = new MySqlAddressStorage(DB_CONN_STRING, DB_USERNAME, DB_PASSWORD);
    public static MySqlEducationStorage educationStorage = new MySqlEducationStorage(DB_CONN_STRING, DB_USERNAME, DB_PASSWORD);
    public static MySqlSocialInsuranceStorage insuranceStorage = new MySqlSocialInsuranceStorage(DB_CONN_STRING, DB_USERNAME, DB_PASSWORD);

}
