package com.mainpage.shuttlereservation.domains;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {

    public boolean emailValidator(String email){
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        Pattern pattern = Pattern.compile(regex);
        /*Pattern pattern = Pattern.compile(regex);

        for(String email : emails){
            Matcher matcher = pattern.matcher(email);
            System.out.println(email +" : "+ matcher.matches());
        }*/

        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
}
