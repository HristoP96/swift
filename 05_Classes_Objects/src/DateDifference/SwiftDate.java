package DateDifference;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Scanner;

/**
 *
 * @author ickoto
 */
public class SwiftDate {

    int year;
    int month;
    int day;
    int century;
    String Lyear;

    public SwiftDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;

    }

    boolean isLeapYear() {
        if ((this.year % 4 == 0 && this.year % 100 != 0) || this.year % 400 == 0) {

            return true;
        }

        return false;
    }

    int getCentury() {
        this.century = (this.year / 100) + 1;

        return this.century;
    }

    int getDaysDifference(SwiftDate date) {
        int diff = Math.abs((this.day - date.day) + ((this.month - date.month) * 30) + ((this.year - date.year) * 365));
        return diff;
    }

    String getInfo() {
        if (isLeapYear()) {
            return this.year + " " + this.month + " " + this.day + " - " + getCentury() + " century ." + "It is LEAP year";
        }

        return this.year + " " + this.month + " " + this.day + " - " + getCentury() + " century ";

    }

}
