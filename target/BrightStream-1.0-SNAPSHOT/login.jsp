<%@page import="com.lod.brightstream.BE.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bright Stream | Log In</title>
        <link rel="stylesheet" href="styles/login-register.css" />
        
        <script>
            function validate() {
                var email = document.login_form.txt_email;
                var password = document.login_form.txt_password;
                var errorMessage = document.getElementById("error_message");
                
                if (email.value == null || email.value == "") {
                    
                    errorMessage.innerText = "Please, enter your email";
                    email.style.background = 'rgba(252, 225, 129, .5)';
                    email.focus();
                    return false;
                }
                
                if (password.value == null || password.value == "") {
                    errorMessage.innerText = "Please, enter your password";
                    password.style.background = 'rgba(252, 225, 129, .5)';
                    password.focus();
                    return false;
                }   
            }      
        </script>
        
    </head>
    <body>
        
        <form action="login" class="form-register" method="post" name="login_form" onsubmit="return validate()">
            
            <div class="form-register-with-email">
                
                <div class="form-title-row">
                    
                    <h1>Log In</h1>
                    
                </div>
                
                <p id="error_message" style="color: crimson;">
                <c:if test="${errorMessage != null}">
                    ${errorMessage}
                </c:if>
                </p>
                
                <br />
                
                <div class="form-row">
                    
                    <label>
                        <span>Email</span>
                        <input type="email" name="txt_email" id="email" placeholder="Your email" value="${preEmail}">
                    </label>
                    
                </div>
                    
                
                <div class="form-row">
                    
                    <label>
                        <span>Password</span>
                        <input type="password" name="txt_password" id="password" placeholder="Your password">
                    </label>
                    
                </div>
                
                <input type="submit" name="btn_login" value="Login" />
                
                <br />
                
                <a href="registration.jsp" class="form-log-in-with-existing">You don't have an account? <b> Register here</b></a>         
            </div> 
        </form>
        
    </body>
</html>
