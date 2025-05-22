package co.converter.utils;

import co.converter.models.ExchangeRate;
import com.google.gson.Gson;
import java.util.Map;

public class CurrencyConverterUtils {
    private static final Gson gson = new Gson();

    public static ExchangeRate parseJsonResponse(String jsonResponse) {
        try {
            ExchangeRate exchangeRate = gson.fromJson(jsonResponse, ExchangeRate.class);
            if (exchangeRate.getRates() == null) {
                throw new RuntimeException("Error: 'conversion_rates' is null in the JSON response.");
            }
            return exchangeRate;
        } catch (Exception e) {
            throw new RuntimeException("Error processing JSON: " + e.getMessage());
        }
    }

    public static double convertCurrency(ExchangeRate exchangeRate, String targetCurrency, double amount) {
        if (exchangeRate.getRates().containsKey(targetCurrency)) {
            return amount * exchangeRate.getRates().get(targetCurrency);
        } else {
            throw new IllegalArgumentException("Currency not found: " + targetCurrency);
        }
    }

    public static double convertBetweenCurrencies(ExchangeRate exchangeRate, String fromCurrency, String toCurrency, double amount) {
        Map<String, Double> rates = exchangeRate.getRates();

        if (!rates.containsKey(fromCurrency) || !rates.containsKey(toCurrency)) {
            throw new IllegalArgumentException("One of the coins is not available.");
        }

        double amountInUSD = amount / rates.get(fromCurrency);
        return (double) amountInUSD * rates.get(toCurrency);
    }
}
