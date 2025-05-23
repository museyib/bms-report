package az.inci.zreport.model;

import lombok.Data;

@Data
public class ComparativeReportData
{
    private String sbeCode;
    private String sbeName;
    private double saleChangePercent;
    private double paymentChangePercent;
    private boolean selfFlag;
    private double positiveSaleRatio;
    private double negativeSaleRatio;
    private double positivePaymentRatio;
    private double negativePaymentRatio;
}
