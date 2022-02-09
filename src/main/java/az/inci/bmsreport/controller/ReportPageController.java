package az.inci.bmsreport.controller;

import az.inci.bmsreport.service.SbeService;
import az.inci.bmsreport.service.WhsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/reports/sbe-stock-report")
public class ReportPageController {
    private SbeService sbeService;
    private WhsService whsService;

    @Autowired
    public void setSbeService(SbeService sbeService) {
        this.sbeService = sbeService;
    }

    @Autowired
    public void setWhsService(WhsService whsService) {
        this.whsService = whsService;
    }

    @RequestMapping("/report")
    public String reportPage(Model model)
    {
        model.addAttribute("sbe_list", sbeService.getSbeList());
        model.addAttribute("whs_list", whsService.getWhsList());

        return "admin/reports/sbe-stock-report/report";
    }
}
