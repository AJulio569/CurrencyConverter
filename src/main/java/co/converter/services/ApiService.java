package co.converter.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiService {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/";
    private static final String API_KEY = "26ae1405c1774151772d121d";
    private static final String LATEST_URL = "/latest/";
    private final HttpClient httpClient;

    public ApiService() {
        this.httpClient = HttpClient.newHttpClient();

    }

    public String fetchExchangeRates(String baseCurrency) {
        String requestUrl = API_URL +  API_KEY + LATEST_URL + baseCurrency;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(requestUrl))
                .header("Accept", "application/json")
                .timeout(java.time.Duration.ofSeconds(10))
                .GET()
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return response.body();
            } else {
                throw new RuntimeException("Error: Status Code " + response.statusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error in HTTP request: " + e.getMessage());
        }
    }

}
