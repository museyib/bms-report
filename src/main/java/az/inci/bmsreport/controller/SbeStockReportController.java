package az.inci.bmsreport.controller;

import az.inci.bmsreport.model.SbeStockReportData;
import az.inci.bmsreport.service.SbeStockReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("admin/reports/sbe-stock-report")
public class SbeStockReportController {
    private SbeStockReportService reportService;

    @Autowired
    public void setReportService(SbeStockReportService reportService) {
        this.reportService = reportService;
    }

    @RequestMapping("/sbe-report")
    public String getReport(@RequestParam(value = "start-date", required = false) String startDate,
                            @RequestParam(value = "end-date", required = false) String endDate,
                            @RequestParam(value = "sbe-list", required = false) List<String> sbeList,
                            Model model)
    {
        SbeStockReportData report;
        if (startDate != null && endDate != null)
            report = reportService.getReport(startDate, endDate, sbeList);
        else
            report = new SbeStockReportData();
        model.addAttribute("report_data", report);

        return "admin/reports/sbe-stock-report/sbe-report";
    }
}
