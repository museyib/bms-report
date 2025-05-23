package az.inci.zreport.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController
{

    @RequestMapping("/login")
    public String login()
    {
        return "login";
    }

    @RequestMapping("403")
    public String error()
    {
        return "error/403";
    }
}
