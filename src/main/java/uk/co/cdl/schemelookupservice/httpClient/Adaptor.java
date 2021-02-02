package uk.co.cdl.schemelookupservice.httpClient;

import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import uk.co.cdl.schemelookupservice.model.Scheme;

@Service
public class Adaptor {
    private final UrlProvider urlProvider;

    private RestTemplateBuilder restTemplateBuilder;

    public Adaptor(UrlProvider urlProvider, RestTemplateBuilder restTemplateBuilder) {
        this.urlProvider = urlProvider;
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public List<Scheme> getSchemes(PolicyType policyType){
        String url = this.urlProvider.getBaseUrl(policyType).orElse("");
        RestTemplate restTemplate = this.restTemplateBuilder.build();
        ParameterizedTypeReference<List<Scheme>> typeReference = ParameterizedTypeReference<List<Scheme>>(){};
        List<Scheme> schemes = restTemplate.exchange(url, HttpMethod.GET,null, typeReference).getBody();
    }
}
