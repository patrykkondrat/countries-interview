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
        String uri = "https://restcountries.com/v3.1/name/" + countryName;
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

        String name = node.get("name").get("common").asText();
        String nativeName = node.get("name").get("nativeName").elements().next().get("official").toString();
        String capital = node.get("capital").get(0).asText();
        Long population = node.get("population").longValue();
        String currency = node.get("currencies").elements().next().get("name").toString();
        String subregion = node.get("subregion").asText();
        String languages = node.get("languages").elements().next().toString();

        return new CountryDetails(name, nativeName, capital, currency, population, subregion, languages);
    }
}
