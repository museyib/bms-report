package az.inci.zreport.controller;

import az.inci.zreport.model.SbeStockReportData;
import az.inci.zreport.service.SbeStockReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("reports/sbe-stock-report")
public class SbeStockReportController
{
    private final SbeStockReportService reportService;

    @RequestMapping("/sbe-report")
    public String getReport(@RequestParam(value = "start-date", required = false) String startDate,
                            @RequestParam(value = "end-date", required = false) String endDate,
                            @RequestParam(value = "sbe-list", required = false) List<String> sbeList,
                            @RequestParam(value = "price-code", required = false) String priceCode,
                            Model model)
    {
        SbeStockReportData report;
        if (startDate != null && endDate != null)
            report = reportService.getReport(startDate, endDate, sbeList, priceCode);
        else
            report = new SbeStockReportData();
        model.addAttribute("report_data", report);

        return "reports/sbe-stock-report/sbe-report";
    }
}
