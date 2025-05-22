package co.converter.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class CurrencyUtils {
    public static String formatAmount(double value, int decimalPlaces) {
        if (decimalPlaces < 0) throw new IllegalArgumentException("Decimals cannot be negative.");

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("es", "CO"));
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');

        DecimalFormat df = new DecimalFormat("#,###.000", symbols);
        return df.format(bd);
    }

    public static String formatUserInput(String input) {
        String normalizedInput = input.replace(".", "").replace(",", ".");

        try {
            double amount = Double.parseDouble(normalizedInput);
            return formatAmount(amount, 3);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error: The entered value is not valid.");
        }
    }
}
