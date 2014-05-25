package ua.av.utils;

import java.util.List;
import java.util.regex.Pattern;

public class AddEmployeeFeature {
    private static final Pattern P = Pattern.compile("\\d+\\.?\\d*$");

    public static boolean isValid(List<String> listToValidate) {
    boolean result;

        if (listToValidate.get(1).length() < 46 ||
                listToValidate.get(2).length() < 46) {
            result = true;

        } else {
            return false;
        }

        if (isDateValid(listToValidate.get(3))) {
            result = true;
        } else {
            return false;
        }
        for (int i = 4; i < listToValidate.size() - 4; i++) {
            if(listToValidate.get(i).isEmpty()) {
                return false;
            } else if(!P.matcher(listToValidate.get(i)).matches()) {
                    return false;
                }
            }
        return result;
    }

    public static boolean isDateValid(String date) {
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8, 10));

        if ((year >= 1950 && year < 1996) && (month >= 1 && month <= 12)) {
            if ((month >= 1 && month <= 12)
                    || (month == 1 && (day >= 1 && day <= 31))
                    || (month == 2 && (day >= 1 && day <= 29))
                    || (month == 3 && (day >= 1 && day <= 31))
                    || (month == 4 && (day >= 1 && day <= 30))
                    || (month == 5 && (day >= 1 && day <= 31))
                    || (month == 6 && (day >= 1 && day <= 30))
                    || (month == 7 && (day >= 1 && day <= 31))
                    || (month == 8 && (day >= 1 && day <= 31))
                    || (month == 9 && (day >= 1 && day <= 30))
                    || (month == 10 && (day >= 1 && day <= 31))
                    || (month == 11 && (day >= 1 && day <= 30))
                    || (month == 12 && (day >= 1 && day <= 31))) {
                return true;
            }
        }
        return false;
    }

}
