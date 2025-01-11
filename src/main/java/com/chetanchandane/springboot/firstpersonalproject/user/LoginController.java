package com.chetanchandane.springboot.firstpersonalproject.user;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Logger;

@Controller
public class LoginController {

    private AuthenticationService authenticationService;

    public LoginController(AuthenticationService authenticationService) {
        super();
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value="/api/v1/login", method = RequestMethod.GET)
    public String gotoLoginPage(){
        return "loginUser";
    }

    @RequestMapping(value="login", method = RequestMethod.POST)
    public String gotoWelcomePage(@RequestParam String username, @RequestParam String password, ModelMap model){
        //model
        model.put("username", username);

        //Authentication
        if(authenticationService.authenticate(username, password)){
            model.put("username", username);
            return "welcome";
        }
        model.put("errorMessage", "Invalid Credentials!, Please try again.");
        return "loginUser";


    }
//    @RequestMapping("login")
//    public String gotoLoginPage2(@RequestParam String name, ModelMap model){
//        model.put("name", name);
//        return "loginUser";
//
//    }
}
