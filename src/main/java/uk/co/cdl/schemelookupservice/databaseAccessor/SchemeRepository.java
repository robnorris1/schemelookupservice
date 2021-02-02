package uk.co.cdl.schemelookupservice.databaseAccessor;

import org.springframework.data.jpa.repository.JpaRepository;

import uk.co.cdl.schemelookupservice.model.Scheme;

public interface SchemeRepository extends JpaRepository<Scheme, Integer> {
    Scheme findBySchemeCode(String schemeCode);
}
