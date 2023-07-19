package az.inci.bmsreport.model;

import lombok.Data;

@Data
public class Price
{
    private String priceCode;
    private String priceName;
    @Override
    public String toString()
    {
        return priceCode + " - " + priceName;
    }
}
