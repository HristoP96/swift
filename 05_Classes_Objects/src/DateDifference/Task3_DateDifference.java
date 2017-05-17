package DateDifference;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import DateDifference.SwiftDate;
import java.util.Scanner;

/**
 *
 * @author ickoto
 */
public class Task3_DateDifference {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int xyear = sc.nextInt();
        int xmonth = sc.nextInt();
        int xday = sc.nextInt();

        int yyear = sc.nextInt();
        int ymonth = sc.nextInt();
        int yday = sc.nextInt();

        SwiftDate x = new SwiftDate(xyear, xmonth, xday);
        SwiftDate y = new SwiftDate(yyear, ymonth, yday);

        System.out.println(x.getDaysDifference(y));
        System.out.println(x.getInfo());
        System.out.println(y.getInfo());
    }
}
