package az.inci.bmsreport.model;

import java.util.ArrayList;
import java.util.List;

public class SbeStockReportData {
    private String startDate;
    private String endDate;
    private List<SbeStockReportDataItem> dataItemList = new ArrayList<>();

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<SbeStockReportDataItem> getDataItemList() {
        return dataItemList;
    }

    public void setDataItemList(List<SbeStockReportDataItem> dataItemList) {
        this.dataItemList = dataItemList;
    }

    public void adItem(SbeStockReportDataItem item)
    {
        dataItemList.add(item);
    }
}
