# spring-multimodule-recaptcha-roles-logindemo
### Login Demo project for Spring Boot with reCAPTCHA

The demo contains the following modules:

recaptcha-spring-boot-starter:  
This based on the https://github.com/mkopylec/recaptcha-spring-boot-starter, I copied it to here with minor changes (making it maven from gradle module and also some minor changes in the code)

spring-recaptcha-roles-logindemo (the real runnable spring boot application):  
This is my coding, using the recaptcha-spring-boot-starter module to the reCAPTCHA validation, implementing the abstract com.github.mkopylec.recaptcha.security.login.LoginFailuresManager (hu.berenyi.spring.logindemo.security.login.manager.CustomLoginFailuresManager) and using the com.github.mkopylec.recaptcha.security.login.FormLoginConfigurerEnhancer in the @Configuration hu.berenyi.spring.logindemo.config.MvcConfiguration.

The project includes Thymeleaf, Apache Derby database with the creation of a db tables from the Entity classes, also dataloader (creation of users in the db)

## Functional description
### Sign-in page
The sign in page contains a form

The element of the sign in form:

• Username

• Password

• Sign in button

### Behavior
• The user fills in the username and password field, then clicks the sign in button

• The system compares the received data with the users in the database

▪ If the data matches the user is signed in and the system redirects to the central administration page.

▪ In case there is no match the user is redirected to the sign in page.

▪ after three failed sign-in attempts, on the subsequent attempts the user should solve a CAPTCHA
picture test

### User roles
All users have one or more roles. In case a user has more than one role, they have the union of the
permissions, so they get all the permission from all the roles.
The system defines three roles:

• Administrator

    ▪ Has access to the administrator subpages, as well as all the other subpages.

• Content editor

    ▪ Has access to the content editor subpages

• Signed in user

    ▪ Has access to the singed in user subpages
### The elements of the page:
Menu:

▪ displays the links of the subpages the user has permission to access. It also displays the logout button

• Sign-in data: Displays the username, the roles the user belongs to, and the time of the last login

### Content editor's subpage
This subpage can be accessed by the users with the “content editor” role. (and of course with the Administrator role)
### Signed in user's subpage
This subpage can be accessed by the users with “signed in user” role. (and of course with the Administrator role)
### Admin's subpage
This subpage can be accessed by the users with “Administrator” role. This contains also the datas of the all users.

### The users listed below created in the system (Username - Roles):

Admin    -    Administrator

User1    -    Content editor + Signed in user

User2    -    Content editor

User3    -    Signed in user
