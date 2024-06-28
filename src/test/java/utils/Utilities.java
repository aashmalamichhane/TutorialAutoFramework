package utils;

import java.util.Date;

public class Utilities {

    public  static String  generateEmailTimeStamp()
    {
        Date date = new Date();
        String timestamp = date.toString().replace(" ","_").replace(":","_");
        return "aashma"+timestamp+"@yopmail.com";
    }
}

