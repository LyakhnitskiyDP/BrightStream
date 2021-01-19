package com.lod.brightstream.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;


/*
 *MessageDigest md = null;
        
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch(NoSuchAlgorithmException noAlgException) {
            System.err.println(noAlgException);
        }
        
        byte[] hashedPasswordBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
        
        StringBuilder hashedPassword = new StringBuilder();

        for (byte b: hashedPasswordBytes) hashedPassword.append((char) b);
        
        return hashedPassword.toString();
 * 
 */
public class HashUtilSHA512 {

    public static String hashPassword(String password) {
        
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Hashing algorith couldn't be initialized");
        }
        
        md.update(password.getBytes());
        
        byte[] hashedBytes = md.digest();
        
        StringBuilder sb = new StringBuilder(hashedBytes.length * 2);
        
        for (byte hashedByte : hashedBytes) {
            //2's complement representations in 32bit and 8bit contexts are not the same
            int hashedInt = hashedByte & 0xFF;
            
            if (hashedInt <= 0xF)
                sb.append("0");
            
            sb.append(Integer.toHexString(hashedInt));
        }
        
        
        return sb.toString();
    }
    
    
    
}
