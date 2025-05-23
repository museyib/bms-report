package az.inci.zreport.model;

import java.util.ArrayList;
import java.util.List;

public class SbeStockReportData
{
    private String startDate;
    private String endDate;
    private double firstBpBalance;
    private double lastBpBalance;
    private double firstStock;
    private double lastStock;
    private List<SbeStockReportDataItem> dataItemList = new ArrayList<>();

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

    public List<SbeStockReportDataItem> getDataItemList()
    {
        return dataItemList;
    }

    public void setDataItemList(List<SbeStockReportDataItem> dataItemList)
    {
        this.dataItemList = dataItemList;
    }

    public void adItem(SbeStockReportDataItem item)
    {
        dataItemList.add(item);
    }

    public double getFirstBpBalance()
    {
        return firstBpBalance;
    }

    public void setFirstBpBalance(double firstBpBalance)
    {
        this.firstBpBalance = firstBpBalance;
    }

    public double getLastBpBalance()
    {
        return lastBpBalance;
    }

    public void setLastBpBalance(double lastBpBalance)
    {
        this.lastBpBalance = lastBpBalance;
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
