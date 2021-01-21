package uk.co.cdl.schemelookupservice.httpClient;

public enum PolicyType {
    PC("PC"),
    GV("GV"),
    MC("MC"),
    HH("HC");

    private String policyType;

    PolicyType(String policyType) {
        this.policyType = policyType;
    }
}
