package uk.co.cdl.schemelookupservice.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.co.cdl.schemelookupservice.databaseAccessor.SchemeRepositoryService;
import uk.co.cdl.schemelookupservice.httpClient.Adaptor;
import uk.co.cdl.schemelookupservice.httpClient.PolicyType;
import uk.co.cdl.schemelookupservice.model.Response;
import uk.co.cdl.schemelookupservice.model.Scheme;

@Service
public class SchemeService {

    @Autowired
    private SchemeRepositoryService schemeRepositoryService;

    @Autowired
    private Adaptor adaptor;

    public SchemeService() {

    }

    public Response getAllSchemes() {
        storeToDatabase(PolicyType.PC);
        return new Response(schemeRepositoryService.getAllSchemes());
    }

    public Response getBySchemeCode(String schemeCode) {
        return new Response(Collections.singletonList(schemeRepositoryService.getBySchemeCode(schemeCode).orElse(new Scheme())));
    }

    public Response findSchemes(Scheme searchScheme) {
        return new Response(schemeRepositoryService.findSchemes(searchScheme));
    }

    private List<Scheme> getSchemesFromEngine(PolicyType policyType) {
        List<Scheme> schemes = adaptor.getSchemes(policyType);
        return schemes;
    }

    private void storeToDatabase(PolicyType policyType){
          List<Scheme> schemesFromEngine = getSchemesFromEngine(policyType);
          schemeRepositoryService.saveSchemes(schemesFromEngine);
    }

    //equals to myDb and QeDb check


}
