package uk.co.cdl.schemelookupservice.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.co.cdl.schemelookupservice.databaseAccessor.AuditRepository;
import uk.co.cdl.schemelookupservice.databaseAccessor.SchemeRepositoryService;
import uk.co.cdl.schemelookupservice.httpClient.Adaptor;
import uk.co.cdl.schemelookupservice.httpClient.PolicyType;
import uk.co.cdl.schemelookupservice.model.Audit;
import uk.co.cdl.schemelookupservice.model.Response;
import uk.co.cdl.schemelookupservice.model.Scheme;

@Service
public class SchemeService {

    @Autowired
    private SchemeRepositoryService schemeRepositoryService;

    @Autowired
    private AuditRepository auditRepository;

    @Autowired
    private Adaptor adaptor;

    public SchemeService() {

    }

    public Response getAllSchemes() {
        checkAndSyncDatabase(PolicyType.PC);
        return new Response(schemeRepositoryService.findAllSchemes());

        //TODO: check if you need to call the QE
    }

    public Response getBySchemeCode(String schemeCode) {
        checkAndSyncDatabase(PolicyType.PC);
        return new Response(Collections.singletonList(schemeRepositoryService.findBySchemeCode(schemeCode).orElse(new Scheme())));

        //TODO: check if you need to call the QE
    }

    public Response findSchemes(Scheme searchScheme) {
        checkAndSyncDatabase(PolicyType.PC);
        return new Response(schemeRepositoryService.findSchemes(searchScheme));

        //TODO: check if you need to call the QE
    }

    private List<Scheme> getSchemesFromEngine(PolicyType policyType) {
        return adaptor.getSchemes(policyType);
    }

    private void syncDatabase(PolicyType policyType) {
        List<Scheme> schemesFromDatabase = schemeRepositoryService.findAllSchemes();
        List<Scheme> schemesFromEngine = this.getSchemesFromEngine(policyType);

        //helpers
        Predicate<Scheme> findScheme = scheme -> schemesFromDatabase.stream().anyMatch(s -> s.sameSchemeCode(scheme));

        Predicate<Scheme> needsUpdating = scheme -> !schemesFromDatabase.stream()
                .filter(databaseScheme -> databaseScheme.sameSchemeCode(scheme))
                .findAny().get().equals(scheme);

        BiFunction<Scheme, Scheme, Scheme> updateScheme = (engineScheme, databaseScheme) -> databaseScheme.update(engineScheme);

        Function<Scheme, Scheme> findAndUpdateScheme = scheme -> {
            Scheme databaseScheme = schemesFromDatabase.stream()
                    .filter(s -> s.sameSchemeCode(scheme))
                    .findAny().get();
            return updateScheme.apply(scheme, databaseScheme);
        };

        Map<Boolean, List<Scheme>> splitSchemes = schemesFromEngine.stream()
                .collect(Collectors.groupingBy(findScheme::test, HashMap::new, Collectors.toList()));

        List<Scheme> existingSchemes = splitSchemes.get(Boolean.TRUE);
        List<Scheme> newSchemes = splitSchemes.get(Boolean.FALSE);

        List<Scheme> updatedSchemes = existingSchemes.stream()
                .filter(needsUpdating)
                .map(findAndUpdateScheme)
                .collect(Collectors.toList());

        List<Scheme> schemesToBeDeleted = schemesFromDatabase.stream()
                .filter(s -> schemesFromEngine.stream().noneMatch(x -> x.sameSchemeCode(s)))
                .collect(Collectors.toList());

        schemeRepositoryService.deleteAll(schemesToBeDeleted);
        schemeRepositoryService.saveAll(newSchemes);
        schemeRepositoryService.saveAll(updatedSchemes);

        auditRepository.save(new Audit(new Date(System.currentTimeMillis())));
    }

    private void checkAndSyncDatabase(PolicyType policyType) {
        LocalDate localDate = LocalDate.now();
        if(auditRepository.findAllWhereUpdatedAfter(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant())).isEmpty()){
            syncDatabase(policyType);
        }
    }
}
