package uk.co.cdl.schemelookupservice.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uk.co.cdl.schemelookupservice.model.Response;
import uk.co.cdl.schemelookupservice.model.Scheme;
import uk.co.cdl.schemelookupservice.service.SchemeService;

@org.springframework.stereotype.Controller
@RestController
public class SchemeController {

    @Autowired
    SchemeService schemeService;

    public SchemeController() {
    }

    @GetMapping(path = "/listScheme", produces = "application/json")
    public ResponseEntity<Response> getAllSchemes(@RequestParam String policyType) {
        return ResponseEntity.ok(schemeService.getAllSchemes(policyType));
    }

    @GetMapping(path = "/findScheme", produces = "application/json")
    public ResponseEntity<Response> getBySchemeCode(@RequestParam String schemeCode, @RequestParam String policyType) {
        return ResponseEntity.ok(schemeService.getBySchemeCode(schemeCode, policyType));
    }

    @GetMapping(path = "/findSchemes", produces = "application/json")
    public ResponseEntity<Response> searchScheme(Scheme searchScheme) {
        return ResponseEntity.ok(schemeService.findSchemes(searchScheme));
    }
}
