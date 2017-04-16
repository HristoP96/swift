/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBCExercises;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author ickoto
 */
public class trash {
    public static void main(String[] args) {
         Timestamp tmstmp = Timestamp.valueOf(LocalDateTime.now());
         System.out.println(tmstmp);
    }
          
}
