package com.pfe.bookstore.utils;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Env {

    public static String getUrlImages() {
        try {
            return "http://"+InetAddress.getLocalHost().getHostAddress()+":8080/images/";
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        }
    }

}
