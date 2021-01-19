package com.lod.brightstream.utils;

import java.util.Arrays;
import java.util.Optional;
import javax.servlet.http.Cookie;


public class CookieUtil {
    
    public static Optional<String> getCookieValue(Cookie[] cookies, String name) {
        
        if (cookies == null) return Optional.empty();
        
        return Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(name))
                .map(cookie -> cookie.getValue())
                .findAny();
    }
    
    public static Cookie getEmptyCookie(String name) {
        
        Cookie cookie = new Cookie(name, "");
        cookie.setMaxAge(0);
        
        return cookie;
    }
    
    
    
}
