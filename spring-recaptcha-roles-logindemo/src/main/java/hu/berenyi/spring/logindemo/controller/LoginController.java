package hu.berenyi.spring.logindemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/")
    public String showIndexView() {
        return "index";
    }

    @GetMapping("/login")
    public String showLoginView() {
        return "login";
    }
    
    @GetMapping("/accessdenied")
    public String showAccessDeniedView() {
        return "accessdenied";
    }
}
