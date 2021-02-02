package uk.co.cdl.schemelookupservice.model;

import java.util.List;

public class Response {

    private List<Scheme> schemes;

    public Response(List<Scheme> schemes) {
        this.schemes = schemes;
    }

    public List<Scheme> getSchemes() {
        return schemes;
    }

    public void setSchemes(List<Scheme> schemes) {
        this.schemes = schemes;
    }
}
