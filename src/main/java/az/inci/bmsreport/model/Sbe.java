package az.inci.bmsreport.model;

public class Sbe {
    private String parentSbeCode;
    private String sbeCode;
    private String sbeName;

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

    @Override
    public String toString() {
        return sbeCode + " - " + sbeName;
    }
}
