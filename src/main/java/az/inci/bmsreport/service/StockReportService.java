package az.inci.bmsreport.service;

import az.inci.bmsreport.model.StockReportData;
import az.inci.bmsreport.model.StockReportDataItem;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.util.List;

@Service
public class StockReportService extends AbstractService {

    public StockReportData getReport(String startDate, String endDate, List<String> whsList)
    {
        StockReportData reportData = new StockReportData();
        reportData.setStartDate(startDate);
        reportData.setEndDate(endDate);

        Query query = entityManager.createNativeQuery("EXEC DBO.SP_WEB_STOCK_REPORT ?, ?, ?");
        query.setParameter(1, startDate);
        query.setParameter(2, endDate);

        if (whsList != null)
        {
            StringBuilder whsListString= new StringBuilder();
            for (String s : whsList)
                whsListString.append(s).append(" ");

            query.setParameter(3, whsListString.toString().trim());
        }
        else
            query.setParameter(3, null);

        List<Object[]> resultList = query.getResultList();

        for (Object[] result : resultList)
        {
            StockReportDataItem dataItem = new StockReportDataItem();
            dataItem.setWhsCode(String.valueOf(result[0]));
            dataItem.setWhsName(String.valueOf(result[1]));
            dataItem.setFirstStock(Double.parseDouble(String.valueOf(result[2])));
            dataItem.setLastStock(Double.parseDouble(String.valueOf(result[3])));
            reportData.adItem(dataItem);
        }

        return reportData;
    }
}
