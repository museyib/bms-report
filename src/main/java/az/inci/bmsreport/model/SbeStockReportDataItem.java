package az.inci.bmsreport.model;

public class SbeStockReportDataItem {
     private String parentSbeCode;
     private String sbeCode;
     private String sbeName;
     private String whsCode;
     private double firstBpBalance;
     private double lastBpBalance;
     private double firstStock;
     private double lastStock;

    public String getParentSbeCode() {
        return parentSbeCode;
    }

    public void setParentSbeCode(String parentSbeCode) {
        this.parentSbeCode = parentSbeCode;
    }

    public String getSbeCode() {
        return sbeCode;
    }

    public void setSbeCode(String sbeCode) {
        this.sbeCode = sbeCode;
    }

    public String getSbeName() {
        return sbeName;
    }

    public void setSbeName(String sbeName) {
        this.sbeName = sbeName;
    }

    public String getWhsCode() {
        return whsCode;
    }

    public void setWhsCode(String whsCode) {
        this.whsCode = whsCode;
    }

    public double getFirstBpBalance() {
        return firstBpBalance;
    }

    public void setFirstBpBalance(double firstBpBalance) {
        this.firstBpBalance = firstBpBalance;
    }

    public double getLastBpBalance() {
        return lastBpBalance;
    }

    public void setLastBpBalance(double lastBpBalance) {
        this.lastBpBalance = lastBpBalance;
    }

    public double getFirstStock() {
        return firstStock;
    }

    public void setFirstStock(double firstStock) {
        this.firstStock = firstStock;
    }

    public double getLastStock() {
        return lastStock;
    }

    public void setLastStock(double lastStock) {
        this.lastStock = lastStock;
    }
}
