package az.inci.bmsreport.model;

import java.util.ArrayList;
import java.util.List;

public class StockReportData
{
    private String startDate;
    private String endDate;
    private double firstStock;
    private double lastStock;
    private List<StockReportDataItem> dataItemList = new ArrayList<>();

    public String getStartDate()
    {
        return startDate;
    }

    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }

    public String getEndDate()
    {
        return endDate;
    }

    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }

    public List<StockReportDataItem> getDataItemList()
    {
        return dataItemList;
    }

    public void setDataItemList(List<StockReportDataItem> dataItemList)
    {
        this.dataItemList = dataItemList;
    }

    public void adItem(StockReportDataItem item)
    {
        dataItemList.add(item);
    }

    public double getFirstStock()
    {
        return firstStock;
    }

    public void setFirstStock(double firstStock)
    {
        this.firstStock = firstStock;
    }

    public double getLastStock()
    {
        return lastStock;
    }

    public void setLastStock(double lastStock)
    {
        this.lastStock = lastStock;
    }
}
