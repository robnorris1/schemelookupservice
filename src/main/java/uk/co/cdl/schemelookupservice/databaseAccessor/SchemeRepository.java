package uk.co.cdl.schemelookupservice.databaseAccessor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import uk.co.cdl.schemelookupservice.model.Scheme;

public interface SchemeRepository extends JpaRepository<Scheme, Integer> {
    Scheme findBySchemeCode(String schemeCode);
    List<Scheme> findAllSchemesByPolicyType(String policyType);
}
