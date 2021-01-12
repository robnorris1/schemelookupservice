package uk.co.cdl.schemelookupservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Enrichment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String enrichmentCode;

    private String enrichmentName;

    private int mtaDetailsBitMask;

    private String enrichmentBusinessType;

    private String scid;

    public String getEnrichmentCode() {
        return enrichmentCode;
    }

    public void setEnrichmentCode(String enrichmentCode) {
        this.enrichmentCode = enrichmentCode;
    }

    public String getEnrichmentName() {
        return enrichmentName;
    }

    public void setEnrichmentName(String enrichmentName) {
        this.enrichmentName = enrichmentName;
    }

    public int getMtaDetailsBitMask() {
        return mtaDetailsBitMask;
    }

    public void setMtaDetailsBitMask(int mtaDetailsBitMask) {
        this.mtaDetailsBitMask = mtaDetailsBitMask;
    }

    public String getEnrichmentBusinessType() {
        return enrichmentBusinessType;
    }

    public void setEnrichmentBusinessType(String enrichmentBusinessType) {
        this.enrichmentBusinessType = enrichmentBusinessType;
    }

    public String getScid() {
        return scid;
    }

    public void setScid(String scid) {
        this.scid = scid;
    }
}