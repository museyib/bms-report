package az.inci.bmsreport.model;

public class StockReportDataItem
{
    private String whsName;
    private String whsCode;
    private double firstStock;
    private double lastStock;

    public String getWhsName()
    {
        return whsName;
    }

    public void setWhsName(String whsName)
    {
        this.whsName = whsName;
    }

    public String getWhsCode()
    {
        return whsCode;
    }

    public void setWhsCode(String whsCode)
    {
        this.whsCode = whsCode;
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
