package uk.co.cdl.schemelookupservice.databaseAccessor;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uk.co.cdl.schemelookupservice.model.Scheme;

//TODO: add docs

@Service
public class SchemeRepositoryService {

    @Autowired
    SchemeRepository schemeRepository;

    public List<Scheme> findSchemes(Scheme searchScheme) {
        Example<Scheme> example = Example.of(searchScheme);
        return schemeRepository.findAll(example);
    }

    @Transactional
    public Optional<Scheme> findBySchemeCode(String schemeCode) {
        Example<Scheme> example = Example.of(new Scheme().withSchemeCode(schemeCode));
        return schemeRepository.findOne(example);
    }

    @Transactional
    public List<Scheme> findAllSchemes() {
        return schemeRepository.findAll();
    }

    @Transactional
    public void saveAll(List<Scheme> schemes) {
        if (schemes != null && !schemes.isEmpty()) {
            schemeRepository.saveAll(schemes);
        }
    }

    /**
     * Deletes all the scheme records, provided in the parameters, from the database
     *
     * @param schemes the schemes to be deleted
     */
    public void deleteAll(List<Scheme> schemes) {
        schemeRepository.deleteAll(schemes);
    }
}