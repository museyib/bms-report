package az.inci.bmsreport.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportScheduler
{
    private final ReportEmailService emailService;
    private final MailContentBuilder mailContentBuilder;
    private final SaleStockReportService reportService;

    @Scheduled(cron = "0 0 21 * * *")
    public void sendReport()
    {
        emailService.sendEmail(mailContentBuilder.build(reportService.getReportData()));
    }
}