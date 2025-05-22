package co.converter;

import co.converter.models.ExchangeRate;
import co.converter.services.ApiService;
import co.converter.utils.CurrencyConverterUtils;
import co.converter.utils.CurrencyUtils;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        ApiService apiService = new ApiService();
        String[] currencies = {"ARS - Peso argentino", "BOB - Boliviano boliviano",
                "BRL - Real brasileño", "CLP - Peso chileno",
                "COP - Peso colombiano", "USD - Dólar estadounidense"};

        String menu = """
        *** Currency Converter ***
        1️ Convert from USD to another currency
        2️ Convert between two currencies
        3️ Exit
        """;

        int option;
        do {
            String inputOption = JOptionPane.showInputDialog(menu + "\nSelect an option:");

            if (inputOption == null || inputOption.equalsIgnoreCase("3")) {
                JOptionPane.showMessageDialog(null, "Thank you for using the converter!");
                break;
            }

            try {
                option = Integer.parseInt(inputOption);

                switch (option) {
                    case 1 -> convertFromUSD(apiService, currencies);
                    case 2 -> convertBetweenCurrencies(apiService, currencies);
                    default -> JOptionPane.showMessageDialog(null, "Invalid option, please try again.", "WARNING", JOptionPane.WARNING_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number.", "WARNING", JOptionPane.WARNING_MESSAGE);
            }
        } while (true);
    }

    private static void convertFromUSD(ApiService apiService, String[] currencies) {
        String selectedCurrency = (String) JOptionPane.showInputDialog(
                null, "Select the destination currency:",
                "Conversion from USD",
                JOptionPane.QUESTION_MESSAGE, null, currencies, currencies[0]);

        if (selectedCurrency == null) return;

        String currencyCode = selectedCurrency.split(" - ")[0];
        processConversion(apiService, "USD", currencyCode);
    }

    private static void convertBetweenCurrencies(ApiService apiService, String[] currencies) {
        String fromCurrency = (String) JOptionPane.showInputDialog(
                null, "Select the source currency:",
                "Conversion between currencies",
                JOptionPane.QUESTION_MESSAGE, null, currencies, currencies[0]);

        if (fromCurrency == null) return;

        String toCurrency = (String) JOptionPane.showInputDialog(
                null, "Select the destination currency:",
                "Conversion between currencies",
                JOptionPane.QUESTION_MESSAGE, null, currencies, currencies[0]);

        if (toCurrency == null) return;

        processConversion(apiService, fromCurrency.split(" - ")[0], toCurrency.split(" - ")[0]);
    }

    private static void processConversion(ApiService apiService, String fromCurrency, String toCurrency) {
        String inputValue = JOptionPane.showInputDialog("Enter the amount:");
        if (inputValue == null) return;

        try {
            String normalizedInput = inputValue.replace(".", "").replace(",", ".");
            double amount = Double.parseDouble(normalizedInput);

            String jsonResponse = apiService.fetchExchangeRates("USD");
            ExchangeRate exchangeRate = CurrencyConverterUtils.parseJsonResponse(jsonResponse);

            double convertedAmount = CurrencyConverterUtils.convertBetweenCurrencies(exchangeRate, fromCurrency, toCurrency, amount);
            String formattedAmount = CurrencyUtils.formatAmount(convertedAmount, 3);

            JOptionPane.showMessageDialog(null,
                    " "+ CurrencyUtils.formatAmount(amount, 3) + " " + fromCurrency + " is equivalent to " + formattedAmount + " " + toCurrency, "Conversion", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Conversion error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}