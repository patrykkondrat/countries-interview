package org.patrykkondrat.countries.service;

import graphql.ExecutionResult;
import graphql.GraphQL;
import org.patrykkondrat.countries.client.GraphQLClient;
import org.patrykkondrat.countries.model.Continent;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class GraphqlClientService {

    private GraphQLClient graphQLClient;

    public GraphqlClientService(GraphQLClient graphQLClient) {
        this.graphQLClient = graphQLClient;
    }

    public Continent getCountriesFromContinent(String continent) {
        return graphQLClient.getCountriesFromContinent(continent);
    }

}
