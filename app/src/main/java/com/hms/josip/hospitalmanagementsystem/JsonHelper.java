package com.hms.josip.hospitalmanagementsystem;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Josip on 17.1.2017..
 */
public class JsonHelper {

    //          "/Date(1484607600000+0100)/"
    public Date DeserializeDate(String dateString) {
        String result = dateString.split("\\+")[0].split("\\(")[1];

        return new Date(Long.parseLong(result));
    }
}
