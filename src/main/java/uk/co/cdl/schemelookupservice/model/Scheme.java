package uk.co.cdl.schemelookupservice.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Scheme {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String schemeCode;

    private String schemeName;

    private String underwriterCode;

    private String underwriterName;

    private String insurerSCID;

    private String companyGroupCode;

    private String brokerStatus;

    private int companyNumber;

    @JsonProperty(value = "enrichments")
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "scheme_enchrichment",
            joinColumns = {@JoinColumn(name = "fk_scheme")}, inverseJoinColumns = {@JoinColumn(name = "fk_enrichment")})
    private List<Enrichment> enrichments;

    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }

    public Scheme withSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
        return this;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getUnderwriterCode() {
        return underwriterCode;
    }

    public void setUnderwriterCode(String underwriterCode) {
        this.underwriterCode = underwriterCode;
    }

    public String getUnderwriterName() {
        return underwriterName;
    }

    public void setUnderwriterName(String underwriterName) {
        this.underwriterName = underwriterName;
    }

    public String getInsurerSCID() {
        return insurerSCID;
    }

    public void setInsurerSCID(String insurerSCID) {
        this.insurerSCID = insurerSCID;
    }

    public String getCompanyGroupCode() {
        return companyGroupCode;
    }

    public void setCompanyGroupCode(String companyGroupCode) {
        this.companyGroupCode = companyGroupCode;
    }

    public String getBrokerStatus() {
        return brokerStatus;
    }

    public void setBrokerStatus(String brokerStatus) {
        this.brokerStatus = brokerStatus;
    }

    public List<Enrichment> getEnrichments() {
        return enrichments;
    }

    public void setEnrichments(List<Enrichment> enrichments) {
        this.enrichments = enrichments;
    }

    public int getCompanyNumber() {
        return companyNumber;
    }

    public void setCompanyNumber(int companyNumber) {
        this.companyNumber = companyNumber;
    }
}
