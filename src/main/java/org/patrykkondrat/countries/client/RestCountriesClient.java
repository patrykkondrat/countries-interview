package org.patrykkondrat.countries.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import lombok.SneakyThrows;
import org.patrykkondrat.countries.model.CountryDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
public class RestCountriesClient {
    private RestTemplate restTemplate = new RestTemplate();

    private ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public CountryDetails getCountryDetailsFromRest(String countryName) {
        String finalCountryName = countryName.contains(" ") ? countryName.replaceAll(" ", "%20") : countryName;
        String uri = "https://restcountries.com/v3.1/name/" + finalCountryName;
        ResponseEntity<String> response = restTemplate.getForEntity(new URI(uri), String.class);
        String responseBody = response.getBody();
        return parseResponseToCountryDetails(responseBody);
    }

    public CountryDetails parseResponseToCountryDetails(String responseBody) throws JsonProcessingException {
        Gson gson = new Gson();
        JsonElement jsonElement = gson.fromJson(responseBody, JsonElement.class);
        String newJson = gson.toJson(jsonElement);
        String hope = newJson.substring(1, newJson.length() - 1);
        JsonNode node = objectMapper.readTree(hope);

        try {
            String name = node.path("name").path("common").asText();
            String nativeName = node.path("name").path("nativeName").elements().next().path("official").asText();
            String capital = node.path("capital").path(0).asText();
            Long population = node.path("population").longValue();
            String currency = node.path("currencies").elements().next().path("name").asText();
            String subregion = node.path("subregion").asText();
            String languages = node.path("languages").elements().next().asText();

            return new CountryDetails(name, nativeName, capital, currency, population, subregion, languages);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
