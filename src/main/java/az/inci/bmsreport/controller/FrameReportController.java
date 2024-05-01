package az.inci.bmsreport.controller;

import az.inci.bmsreport.model.ComparativeReportData;
import az.inci.bmsreport.model.User;
import az.inci.bmsreport.service.ComparativeReportService;
import az.inci.bmsreport.service.MonthService;
import az.inci.bmsreport.service.security.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reports/frame")
public class FrameReportController
{
    private final ComparativeReportService reportService;
    private final UserService userService;
    private final MonthService monthService;

    @GetMapping("/report-for-bm")
    public String getReportForBm(Model model,
                                 @RequestParam(value = "month-id", required = false) Integer monthId)
    {
        List<ComparativeReportData> reportDataList = reportService.bmReport("ADMIN", 0);
        model.addAttribute("report_data", reportDataList);
        model.addAttribute("isSuperVisor", false);
        model.addAttribute("isAdmin", true);
        model.addAttribute("months", monthService.months());
        return "reports/comparative/report-for-bm";
    }

    @GetMapping("/report-for-sub-bm")
    public String getReportForSubBm(Model model,
                                    @RequestParam("sbe-code") String sbeCode,
                                    @RequestParam(value = "month-id", required = false) Integer monthId)
    {
        List<ComparativeReportData> reportDataList = reportService.sbeReportForSbe("ADMIN", sbeCode, monthId);
        model.addAttribute("report_data", reportDataList);
        model.addAttribute("isSuperVisor", false);
        model.addAttribute("months", monthService.months());
        return "reports/comparative/report-for-bm";
    }

    @GetMapping("/report-for-sub-bm-sbe")
    public String getReportForSubBmSbe(Model model,
                                       @RequestParam("sbe-code") String sbeCode,
                                       @RequestParam(value = "month-id", required = false) Integer monthId)
    {
        List<ComparativeReportData> reportDataList = reportService.sbeReportForSbe("ADMIN", sbeCode, monthId);
        model.addAttribute("report_data", reportDataList);
        model.addAttribute("months", monthService.months());
        return "reports/comparative/report-for-sub-sbe";
    }
}
