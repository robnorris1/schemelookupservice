package uk.co.cdl.schemelookupservice.httpClient;

import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class UrlProvider {

//    private static final String MOTOR_QUOTESENGINE_URL = "https://ca6c0728-09c7-461a-acdf-62960020cbb2.mock.pstmn.io/getallschemes";

    private static final String MOTOR_QUOTESENGINE_URL = "https://strata-motor-quotes-latest.dev.cdlcloud.co.uk/getAllSchemes";

    private static final String HOUSE_QUOTESENGINE_URL = "https://strata-house-quotes-latest.dev.cdlcloud.co.uk/getAllSchemes";

    public Optional<String> getBaseUrl(PolicyType policyType) {
        switch (policyType) {
            case PC:
            case GV:
            case MC:
                return Optional.ofNullable(getMotorQuotesengineUrl());
            case HC:
                return Optional.ofNullable(getHouseQuotesengineUrl());
            default:
                //TODO: add exception
                return null;
        }
    }

    private String getHouseQuotesengineUrl() {
        return HOUSE_QUOTESENGINE_URL;
    }

    private String getMotorQuotesengineUrl() {
        return MOTOR_QUOTESENGINE_URL;
    }

}
