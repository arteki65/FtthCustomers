package pl.aptewicz.ftthcustomers.model;


public class FtthRestApiError {

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String translate() {
        if(FtthRestApiExceptionConstants.NO_EDGES_NEAR_ISSUE_LOCATION_FOUND.equals(code)) {
            return "Nieobsługiwany obszar.";
        }
        return "INTERNAL SERVER ERROR";
    }
}
