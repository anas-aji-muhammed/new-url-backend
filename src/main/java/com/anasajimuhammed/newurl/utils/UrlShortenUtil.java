package com.anasajimuhammed.newurl.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UrlShortenUtil {
    public static String md5UrlShorten(String longUrl){
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(longUrl.getBytes());
            byte[] messageDigest = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(Integer.toHexString(0xFF & b));
            }
            return hexString.toString();
        }
        catch (NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }

}
