package uk.co.cdl.schemelookupservice.httpClient;

public enum PolicyType {
    PC("PC"),
    GV("GV"),
    MC("MC"),
    HC("HC");

    private String policyType;

    PolicyType(String policyType) {
        this.policyType = policyType;
    }

    public String getValue(){
        return this.policyType;
    }
}
