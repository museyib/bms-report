package az.inci.bmsreport.controller;

import az.inci.bmsreport.model.StockReportData;
import az.inci.bmsreport.service.StockReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/reports/sbe-stock-report")
public class StockReportController {
    private StockReportService reportService;

    @Autowired
    public void setReportService(StockReportService reportService) {
        this.reportService = reportService;
    }

    @RequestMapping("/stock-report")
    public String getReport(@RequestParam(value = "start-date", required = false) String startDate,
                            @RequestParam(value = "end-date", required = false) String endDate,
                            @RequestParam(value = "whs-list", required = false) List<String> whsList,
                            Model model)
    {
        StockReportData report;
        if (startDate != null && endDate != null)
            report = reportService.getReport(startDate, endDate, whsList);
        else
            report = new StockReportData();
        model.addAttribute("report_data", report);

        return "admin/reports/sbe-stock-report/stock-report";
    }
}
