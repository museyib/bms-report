package az.inci.bmsreport.service;

import az.inci.bmsreport.model.SbeStockReportData;
import az.inci.bmsreport.model.SbeStockReportDataItem;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SbeStockReportService extends AbstractService
{

    public SbeStockReportData getReport(String startDate, String endDate, List<String> sbeList, String priceCode)
    {
        SbeStockReportData reportData = new SbeStockReportData();
        reportData.setStartDate(startDate);
        reportData.setEndDate(endDate);

        double firstBpBalance = 0;
        double lastBpBalance = 0;
        double firstStock = 0;
        double lastStock = 0;

        Query query = entityManager.createNativeQuery("EXEC DBO.SP_WEB_SBE_STOCK_REPORT ?, ?, ?, ?");
        query.setParameter(1, startDate);
        query.setParameter(2, endDate);

        if (sbeList != null)
        {
            StringBuilder sbeListString = new StringBuilder();
            for (String s : sbeList)
            {sbeListString.append(s).append(",");}

            query.setParameter(3, sbeListString.toString().trim());
        }
        else
        {query.setParameter(3, null);}
        query.setParameter(4, priceCode);

        List<Object[]> resultList = query.getResultList();

        for (Object[] result : resultList)
        {
            SbeStockReportDataItem dataItem = new SbeStockReportDataItem();
            dataItem.setParentSbeCode(String.valueOf(result[0]));
            dataItem.setSbeCode(String.valueOf(result[1]));
            dataItem.setSbeName(String.valueOf(result[2]));
            dataItem.setWhsCode(String.valueOf(result[3]));
            dataItem.setFirstBpBalance(Double.parseDouble(String.valueOf(result[4])));
            dataItem.setLastBpBalance(Double.parseDouble(String.valueOf(result[5])));
            dataItem.setFirstStock(Double.parseDouble(String.valueOf(result[6])));
            dataItem.setLastStock(Double.parseDouble(String.valueOf(result[7])));
            reportData.adItem(dataItem);

            firstBpBalance += dataItem.getFirstBpBalance();
            lastBpBalance += dataItem.getLastBpBalance();
            firstStock += dataItem.getFirstStock();
            lastStock += dataItem.getLastStock();
        }

        reportData.setFirstBpBalance(firstBpBalance);
        reportData.setLastBpBalance(lastBpBalance);
        reportData.setFirstStock(firstStock);
        reportData.setLastStock(lastStock);

        return reportData;
    }
}
