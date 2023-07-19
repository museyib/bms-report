package az.inci.bmsreport.model;

import lombok.Data;

@Data
public class Sbe
{
    private String parentSbeCode;
    private String sbeCode;
    private String sbeName;

    @Override
    public String toString()
    {
        return sbeCode + " - " + sbeName;
    }
}
