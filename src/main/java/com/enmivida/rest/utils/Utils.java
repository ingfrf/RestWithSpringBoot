package com.enmivida.rest.utils;

public class Utils {

    public static boolean isNumeric(String number) {
        if (number != null) {
            /**
             * -?           allows zero or more - for negative numbers in the string.
             * \\d+         checks the string must have at least 1 or more numbers (\\d).
             * (\\.\\d+)?   allows zero or more of the given pattern (\\.\\d+) in which
             * \\.          checks if the string contains . (decimal points) or not
             * \\d+         If yes, it should be followed by at least one or more number
             */
            return number.matches("-?\\d+(\\.\\d+)?");
        }
        return false;
    }

    public static double convertToDouble(String number) {
        if (number != null) {
            return Double.parseDouble(number);
        }
        return 0D;
    }
}
