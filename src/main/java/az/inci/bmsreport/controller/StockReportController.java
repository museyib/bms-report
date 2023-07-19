package az.inci.bmsreport.controller;

import az.inci.bmsreport.model.StockReportData;
import az.inci.bmsreport.service.StockReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reports/sbe-stock-report")
public class StockReportController
{
    private final StockReportService reportService;

    @RequestMapping("/stock-report")
    public String getReport(@RequestParam(value = "start-date", required = false) String startDate,
                            @RequestParam(value = "end-date", required = false) String endDate,
                            @RequestParam(value = "whs-list", required = false) List<String> whsList,
                            @RequestParam(value = "price-code", required = false) String priceCode,
                            Model model)
    {
        StockReportData report;
        if (startDate != null && endDate != null)
            report = reportService.getReport(startDate, endDate, whsList, priceCode);
        else
            report = new StockReportData();
        model.addAttribute("report_data", report);

        return "reports/sbe-stock-report/stock-report";
    }
}
