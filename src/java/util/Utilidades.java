package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilidades {

    public static String formatDate(Date fecha, String formato) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(fecha);
        } catch (Exception e) {
        }
        return null;
    }

    public static Date validateInputDate(String input) {

        if (validateInputText(input) == null) {
            return null;
        }

        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(input);
        } catch (ParseException e) {
        }

        return null;
    }

    public static String validateInputText(String input) {

        return input != null && !input.trim().isEmpty() ? input : null;

    }

    public static Integer validateInputNumber(String input) {

        if (validateInputText(input) == null) {
            return null;
        }
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
        }

        return null;
    }

    public static Float validateInputFloat(String input) {

        if (validateInputText(input) == null) {
            return null;
        }
        try {
            return Float.parseFloat(input);
        } catch (NumberFormatException e) {
        }

        return null;
    }

}
