package org.patrykkondrat.countries.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CountryDetails(
        @JsonProperty("name.common")
        String name,
        @JsonProperty("name.nativeName.*.official")
        String nativeName,
        @JsonProperty("capital")
        String capital,
        @JsonProperty("currencies.*.name")
        String currencies,
        @JsonProperty("population")
        Long population,
        @JsonProperty("subregion")
        String subregion,
        @JsonProperty("languages.pol")
        String languages
) {

}