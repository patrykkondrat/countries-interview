package org.patrykkondrat.countries.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;



public record Continent(
    @JsonProperty("name") String name,
    @JsonProperty("code") String code,
    @JsonProperty("countries") List<Country> countries
) {

}

