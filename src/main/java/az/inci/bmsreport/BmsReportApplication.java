package az.inci.bmsreport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class BmsReportApplication {

    public static void main(String[] args) {
        SpringApplication.run(BmsReportApplication.class, args);
    }

    @GetMapping("/")
    public String home()
    {
        return "index";
    }
}
