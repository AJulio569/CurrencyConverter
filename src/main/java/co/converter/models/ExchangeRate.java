package co.converter.models;

import java.util.Map;

public class ExchangeRate {
    private String base_code;
    private Map<String, Double> conversion_rates;

    public ExchangeRate(String base_code, Map<String, Double> conversion_rates) {
        this.base_code = base_code;
        this.conversion_rates = conversion_rates;
    }

    public String getBaseCurrency() {
        return base_code;
    }

    public Map<String, Double> getRates() {
        return conversion_rates;
    }
}
