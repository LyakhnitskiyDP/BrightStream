package com.lod.brightstream.utils;

import org.junit.Test;
import static org.junit.Assert.*;

public class HashUtilSHA512Test {
    
   @Test
   public void testSHA512_equalPasswords() {
       
       String password1 = "password";
       String password2 = "password";
       
       String password1Hashed = HashUtilSHA512.hashPassword(password1);
       String password2Hashed = HashUtilSHA512.hashPassword(password2);
       
       assertEquals(password1Hashed, password2Hashed);
   }
   
   @Test
   public void testSHA512_unequalPasswords() {
       
       String password1 = "password";
       String password2 = "anotherPassword123";
       
       String password1Hashed = HashUtilSHA512.hashPassword(password1);
       String password2Hashed = HashUtilSHA512.hashPassword(password2);
       
       assertNotEquals(password1Hashed, password2Hashed);
   }
}
