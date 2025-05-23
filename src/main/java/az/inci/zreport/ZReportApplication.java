package az.inci.zreport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class ZReportApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(ZReportApplication.class, args);
    }

    @GetMapping("/")
    public String home(Model model)
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails)
        {username = ((UserDetails) principal).getUsername();}
        else
        {username = principal.toString();}

        model.addAttribute("username", username);
        return "index";
    }
}
