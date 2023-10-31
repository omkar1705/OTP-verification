package service;

import java.util.Random;

public class GenarateOTP {
    public static String getOTP(){
        Random ramdom = new Random();
        return String.format("%04d",  ramdom.nextInt(10000));
    }
}
