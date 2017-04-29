
import DALException.DALException;
import address.Address;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import personaldetails.Citizen;
import personaldetails.Gender;
import static MySqlDataStorage.MySqlDataStorage.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ickoto
 */
public class trash {
    

    public static void main(String[] args) throws DALException {
        System.out.println(citizenStorage.getCitizen(7).toString());
    }
}
    

