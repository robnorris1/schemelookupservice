package uk.co.cdl.schemelookupservice.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date updated;

    private String policyType;

    public Audit(Date updated, String policyType) {
        this.updated = updated;
        this.policyType = policyType;
    }

    public Audit() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }
}
