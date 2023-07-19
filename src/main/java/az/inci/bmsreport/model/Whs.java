package az.inci.bmsreport.model;

import lombok.Data;

@Data
public class Whs
{
    private String whsCode;
    private String whsName;

    @Override
    public String toString()
    {
        return whsCode + " - " + whsName;
    }
}
