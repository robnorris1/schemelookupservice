package uk.co.cdl.schemelookupservice.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

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

    @ManyToMany
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

}
