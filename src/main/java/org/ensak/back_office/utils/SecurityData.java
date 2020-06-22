package org.ensak.back_office.utils;

import java.util.regex.Pattern;

public class SecurityData {

    /**
     * cette methode permet au travers d'une expression reguliere si l'adresse entré par
     * l'administrateur est conforme avec le format normal d'une adresse email
     * @param email
     * @return
     */
    public static boolean isValidEmail(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }


    /**
     * cette methode permet au travers d'une expression reguliere si le numero de telephone entré par
     * l'administrateur est conforme avec les numero marocains
     * @param number
     * @return
     */

    public static boolean isValidPhoneNumber(String number)
    {
        String phoneRegex = "(\\+212|0)([ \\-_/]*)(\\d[ \\-_/]*){9}";

        Pattern pat = Pattern.compile(phoneRegex);
        if (number == null)
            return false;
        return pat.matcher(number).matches();
    }
}
