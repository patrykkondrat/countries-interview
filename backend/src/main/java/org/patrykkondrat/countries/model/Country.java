package org.patrykkondrat.countries.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public record Country(
    @JsonProperty("name") String name,
    @JsonProperty("code") String code
) {

}

