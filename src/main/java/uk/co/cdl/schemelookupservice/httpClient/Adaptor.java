package uk.co.cdl.schemelookupservice.httpClient;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import uk.co.cdl.schemelookupservice.model.Request;
import uk.co.cdl.schemelookupservice.model.Scheme;

@Service
public class Adaptor {

    private final UrlProvider urlProvider;

    private final JsonMapper jsonMapper;

    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    public Adaptor(UrlProvider urlProvider, RestTemplateBuilder restTemplateBuilder) {
        this.urlProvider = urlProvider;
        this.restTemplateBuilder = restTemplateBuilder;
        this.jsonMapper = new JsonMapper();
    }

    public List<Scheme> getSchemes(PolicyType policyType){
        String url = this.urlProvider.getBaseUrl(policyType).orElse("");
        RestTemplate restTemplate = this.restTemplateBuilder.build();
        Request request = new Request();
        request.setPolicyType(policyType.getValue());
        String httpResponse = restTemplate.postForObject(url, request, String.class);
        List<Scheme> schemes = null;
        try {
            schemes = jsonMapper.readValue(httpResponse, jsonMapper.getTypeFactory().constructCollectionType(List.class, Scheme.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return schemes;
    }
}
