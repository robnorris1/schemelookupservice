package uk.co.cdl.schemelookupservice;

import org.springframework.data.repository.CrudRepository;

import uk.co.cdl.schemelookupservice.model.Scheme;

public interface SchemeRepository extends CrudRepository<Scheme, Integer> {
}
