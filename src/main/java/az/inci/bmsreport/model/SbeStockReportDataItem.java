package az.inci.bmsreport.model;

import lombok.Data;

@Data
public class SbeStockReportDataItem
{
    private String parentSbeCode;
    private String sbeCode;
    private String sbeName;
    private String whsCode;
    private double firstBpBalance;
    private double lastBpBalance;
    private double firstStock;
    private double lastStock;
}
