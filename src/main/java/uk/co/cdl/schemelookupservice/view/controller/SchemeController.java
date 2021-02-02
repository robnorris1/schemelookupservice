package uk.co.cdl.schemelookupservice.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import uk.co.cdl.schemelookupservice.model.Response;
import uk.co.cdl.schemelookupservice.model.Scheme;
import uk.co.cdl.schemelookupservice.service.SchemeService;

@org.springframework.stereotype.Controller
@RestController
public class SchemeController {

    @Autowired
    SchemeService schemeService;

    @GetMapping(path = "/listScheme", produces = "application/json")
    public ResponseEntity<Response> getAllSchemes() {
        return ResponseEntity.ok(schemeService.getAllSchemes());
    }

    @GetMapping(path = "/listScheme", produces = "application/json")
    public ResponseEntity<Response> getBySchemeCode(String schemeCode) {
        return ResponseEntity.ok(schemeService.getBySchemeCode(schemeCode));
    }

    @GetMapping(path = "/listScheme", produces = "application/json")
    public ResponseEntity<Response> searchScheme(Scheme searchScheme) {
        return ResponseEntity.ok(schemeService.findSchemes(searchScheme));
    }
}
