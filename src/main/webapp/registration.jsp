
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bright Stream | Registration</title>
        
        <link rel="stylesheet" href="styles/login-register.css" />
        
        <script>    
            function validate() {
                var email = document.register_form.txt_email;
                var password = document.register_form.txt_password;
                var name = document.register_form.txt_name;
                
                var errorMessage = document.getElementById("error_message");

                if (name.value == null || name.value == "") {
                    
                    errorMessage.innerText = "Please, enter your name";
                    name.style.background = 'rgba(252, 225, 129, .5)';
                    name.focus();
                    return false;
                }   
                
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
                
                if (password.value.length < 6) {
                    errorMessage.innerText = "Please, enter more reliable password";
                    password.style.background = 'rgba(252, 225, 129, .5)';
                    password.focus();
                    return false;
                }  
            }         
        </script>
        
    </head>
    <body>
        
        <form action="registration" class="form-register" method="post" name="register_form" onsubmit="return validate()">
            
            <div class="form-register-with-email">
                
                <div class="form-title-row">
                    
                    <h1>Register</h1>
                    
                </div>
                
                <p id="error_message" style="color: crimson;">
                <c:if test="${errorMessage != null}">
                    ${errorMessage}
                </c:if>
                </p>
                
                <br />
                
                <div class="form-row">
                    
                    <label>
                        <span>Name</span>
                        <input type="text" name="txt_name" id="name" placeholder="Your name..." value="${preName}">
                    </label>
                    
                </div>
                
                <div class="form-row">
                    
                    <label>
                        <span>Email</span>
                        <input type="email" name="txt_email" id="email" placeholder="Your email...">
                    </label>
                    
                </div>
                
                <div class="form-row">
                    
                    <label>
                        <span>Password</span>
                        <input type="password" name="txt_password" id="password" placeholder="Your password...">
                    </label>
                    
                </div>
                
                <input type="submit" name="btn_register" value="Register" />
                
                <br />
                
                <a href="login.jsp" class="form-log-in-with-existing">Already have an account? <b> Login here!</b></a>         
            </div> 
        </form>
        
    </body>
</html>
