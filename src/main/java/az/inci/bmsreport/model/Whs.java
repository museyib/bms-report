package az.inci.bmsreport.model;

public class Whs {
    private String whsCode;
    private String whsName;

    public String getWhsCode() {
        return whsCode;
    }

    public void setWhsCode(String whsCode) {
        this.whsCode = whsCode;
    }

    public String getWhsName() {
        return whsName;
    }

    public void setWhsName(String whsName) {
        this.whsName = whsName;
    }

    @Override
    public String toString() {
        return whsCode + " - " + whsName;
    }
}
