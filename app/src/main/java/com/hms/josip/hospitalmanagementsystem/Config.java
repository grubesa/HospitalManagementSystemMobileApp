package com.hms.josip.hospitalmanagementsystem;

/**
 * Created by Josip on 17.1.2017..
 */
public class Config {

    //private final String URL = "http://574f9d06.ngrok.io/"; //ngrok

    private final String URL = "http://192.168.1.5:8082/"; //home

    //private final String URL = "http://10.129.44.4:8082/"; //faks

    public String getURL() {
        return URL;
    }
}
