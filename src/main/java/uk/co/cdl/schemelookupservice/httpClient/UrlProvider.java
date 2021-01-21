package uk.co.cdl.schemelookupservice.httpClient;

import java.util.Optional;

public class UrlProvider {

    private static final String MOTOR_QUOTESENGINE_URL = "uk.co.cdl.quotes.motor.quotesengine-url";

    private static final String HOUSE_QUOTESENGINE_URL = "uk.co.cdl.quotes.house.quotesengine-url";

    public Optional<String> getBaseUrl(PolicyType policyType) {
        switch (policyType) {
            case PC:
            case GV:
            case MC:
                return Optional.ofNullable(getMotorQuotesengineUrl());
            case HH:
                return Optional.ofNullable(getHouseQuotesengineUrl());
            default:
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
