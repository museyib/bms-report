package az.inci.bmsreport.service;

import az.inci.bmsreport.model.ComparativeReportData;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
@Service
public class ComparativeReportService extends AbstractService
{
    public List<ComparativeReportData> bmReport(String userId, Integer monthId)
    {
        List<ComparativeReportData> reportDataList = new ArrayList<>();

        Query query = entityManager.createNativeQuery("EXEC DBO.SP_WEB_BM_REPORT :USER_ID");
        if (monthId != 0) {
            query = entityManager.createNativeQuery("EXEC DBO.SP_WEB_BM_REPORT_FOR_MONTH :USER_ID, :MONTH_ID");
            query.setParameter("MONTH_ID", monthId);
        }
        query.setParameter("USER_ID", userId);

        List<Object[]> resultList = query.getResultList();

        double totalPositiveSaleChange = 0;
        double totalNegativeSaleChange = 0;
        double totalPositivePaymentChange = 0;
        double totalNegativePaymentChange = 0;

        for(Object[] result : resultList)
        {
            String sale = String.valueOf(result[2]);
            String payment = String.valueOf(result[3]);
            boolean selfFlag = Integer.parseInt(String.valueOf(result[4])) == 1;

            ComparativeReportData reportData = new ComparativeReportData();
            reportData.setSbeCode((String) result[0]);
            reportData.setSbeName((String) result[1]);
            reportData.setSaleChangePercent(Double.parseDouble(sale));
            if(!payment.equals("null"))
                reportData.setPaymentChangePercent(Double.parseDouble(payment));
            reportData.setSelfFlag(selfFlag);

            if(reportData.getSaleChangePercent() > 0)
                totalPositiveSaleChange += reportData.getSaleChangePercent();
            if(reportData.getSaleChangePercent() < 0)
                totalNegativeSaleChange += reportData.getSaleChangePercent();
            if(reportData.getPaymentChangePercent() > 0)
                totalPositivePaymentChange += reportData.getPaymentChangePercent();
            if(reportData.getPaymentChangePercent() < 0)
                totalNegativePaymentChange += reportData.getPaymentChangePercent();


            reportDataList.add(reportData);
        }

        for(ComparativeReportData reportData : reportDataList)
        {
            if(reportData.getSaleChangePercent() > 0)
                reportData.setPositiveSaleRatio(reportData.getSaleChangePercent()/totalPositiveSaleChange);
            if(reportData.getSaleChangePercent() < 0)
                reportData.setNegativeSaleRatio(reportData.getSaleChangePercent()/totalNegativeSaleChange);
            if(reportData.getPaymentChangePercent() > 0)
                reportData.setPositivePaymentRatio(reportData.getPaymentChangePercent()/totalPositivePaymentChange);
            if(reportData.getPaymentChangePercent() < 0)
                reportData.setNegativePaymentRatio(reportData.getPaymentChangePercent()/totalNegativePaymentChange);
        }

        entityManager.close();
        return reportDataList;
    }

    public List<ComparativeReportData> sbeReport(String userId)
    {
        List<ComparativeReportData> reportDataList = new ArrayList<>();

        Query query = entityManager.createNativeQuery("EXEC DBO.SP_WEB_SBE_REPORT :USER_ID");
        query.setParameter("USER_ID", userId);

        List<Object[]> resultList = query.getResultList();

        double totalPositiveSaleChange = 0;
        double totalNegativeSaleChange = 0;
        double totalPositivePaymentChange = 0;
        double totalNegativePaymentChange = 0;

        for(Object[] result : resultList)
        {
            String sale = String.valueOf(result[2]);
            String payment = String.valueOf(result[3]);
            boolean selfFlag = Integer.parseInt(String.valueOf(result[4])) == 1;

            ComparativeReportData reportData = new ComparativeReportData();
            reportData.setSbeCode((String) result[0]);
            reportData.setSbeName((String) result[1]);
            reportData.setSaleChangePercent(Double.parseDouble(sale));
            if(!payment.equals("null"))
                reportData.setPaymentChangePercent(Double.parseDouble(payment));
            reportData.setSelfFlag(selfFlag);
            reportDataList.add(reportData);

            if(reportData.getSaleChangePercent() > 0)
                totalPositiveSaleChange += reportData.getSaleChangePercent();
            if(reportData.getSaleChangePercent() < 0)
                totalNegativeSaleChange += reportData.getSaleChangePercent();
            if(reportData.getPaymentChangePercent() > 0)
                totalPositivePaymentChange += reportData.getPaymentChangePercent();
            if(reportData.getPaymentChangePercent() < 0)
                totalNegativePaymentChange += reportData.getPaymentChangePercent();
        }

        for(ComparativeReportData reportData : reportDataList)
        {
            if(reportData.getSaleChangePercent() > 0)
                reportData.setPositiveSaleRatio(reportData.getSaleChangePercent()/totalPositiveSaleChange);
            if(reportData.getSaleChangePercent() < 0)
                reportData.setNegativeSaleRatio(reportData.getSaleChangePercent()/totalNegativeSaleChange);
            if(reportData.getPaymentChangePercent() > 0)
                reportData.setPositivePaymentRatio(reportData.getPaymentChangePercent()/totalPositivePaymentChange);
            if(reportData.getPaymentChangePercent() < 0)
                reportData.setNegativePaymentRatio(reportData.getPaymentChangePercent()/totalNegativePaymentChange);
        }

        entityManager.close();
        return reportDataList;
    }

    public List<ComparativeReportData> sbeReportForSbe(String userId, String sbeCode, Integer monthId)
    {
        List<ComparativeReportData> reportDataList = new ArrayList<>();

        Query query = entityManager.createNativeQuery(
                "EXEC DBO.SP_WEB_SUB_SBE_REPORT_FOR_SBE :USER_ID, :SBE_CODE");
        if (monthId != 0) {
            query = entityManager.createNativeQuery(
                    "EXEC DBO.SP_WEB_SUB_SBE_REPORT_FOR_SBE_AND_MONTH :USER_ID, :SBE_CODE, :MONTH_ID");
            query.setParameter("MONTH_ID", monthId);
        }
        query.setParameter("USER_ID", userId);
        query.setParameter("SBE_CODE", sbeCode);

        List<Object[]> resultList = query.getResultList();

        double totalPositiveSaleChange = 0;
        double totalNegativeSaleChange = 0;
        double totalPositivePaymentChange = 0;
        double totalNegativePaymentChange = 0;

        for(Object[] result : resultList)
        {
            String sale = String.valueOf(result[2]);
            String payment = String.valueOf(result[3]);
            boolean selfFlag = Integer.parseInt(String.valueOf(result[4])) == 1;

            ComparativeReportData reportData = new ComparativeReportData();
            reportData.setSbeCode((String) result[0]);
            reportData.setSbeName((String) result[1]);
            reportData.setSaleChangePercent(Double.parseDouble(sale));
            if(!payment.equals("null"))
                reportData.setPaymentChangePercent(Double.parseDouble(payment));
            reportData.setSelfFlag(selfFlag);
            reportDataList.add(reportData);

            if(reportData.getSaleChangePercent() > 0)
                totalPositiveSaleChange += reportData.getSaleChangePercent();
            if(reportData.getSaleChangePercent() < 0)
                totalNegativeSaleChange += reportData.getSaleChangePercent();
            if(reportData.getPaymentChangePercent() > 0)
                totalPositivePaymentChange += reportData.getPaymentChangePercent();
            if(reportData.getPaymentChangePercent() < 0)
                totalNegativePaymentChange += reportData.getPaymentChangePercent();
        }

        for(ComparativeReportData reportData : reportDataList)
        {
            if(reportData.getSaleChangePercent() > 0)
                reportData.setPositiveSaleRatio(reportData.getSaleChangePercent()/totalPositiveSaleChange);
            if(reportData.getSaleChangePercent() < 0)
                reportData.setNegativeSaleRatio(reportData.getSaleChangePercent()/totalNegativeSaleChange);
            if(reportData.getPaymentChangePercent() > 0)
                reportData.setPositivePaymentRatio(reportData.getPaymentChangePercent()/totalPositivePaymentChange);
            if(reportData.getPaymentChangePercent() < 0)
                reportData.setNegativePaymentRatio(reportData.getPaymentChangePercent()/totalNegativePaymentChange);
        }

        entityManager.close();
        return reportDataList;
    }
}
