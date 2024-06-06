package org.regression.rest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpHeaders;

import java.util.Map;

public class RestClient {

    private final RequestSpecification request;
    private String baseUrl;


    public RestClient(final String baseUrl, final Map<String, String> headerMap) {
        this.baseUrl = baseUrl;
        this.request = RestAssured
                .given()
                .baseUri(baseUrl)
                .headers(headerMap)
                .header(HttpHeaders.CONTENT_TYPE, "application/json");
    }

    private Response GET(final String endpoint) {
        return request
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }

}
