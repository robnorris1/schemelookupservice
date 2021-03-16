package uk.co.cdl.schemelookupservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import uk.co.cdl.schemelookupservice.service.SchemeService;

@SpringBootApplication
public class SchemelookupserviceApplication {

    private SchemeService schemeService;

    public static void main(String[] args) {
        SpringApplication.run(SchemelookupserviceApplication.class, args);
    }

}
