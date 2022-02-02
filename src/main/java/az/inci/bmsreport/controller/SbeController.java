package az.inci.bmsreport.controller;

import az.inci.bmsreport.model.Sbe;
import az.inci.bmsreport.service.SbeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SbeController {
    private SbeService service;

    @Autowired
    public void setService(SbeService service) {
        this.service = service;
    }

    @GetMapping("/sbe-list")
    public List<Sbe> getSbeList()
    {
        return service.getSbeList();
    }
}
