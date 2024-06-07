package com.regression.framework.rest.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;


public class RestClient {
    private static final Logger logger = LogManager.getLogger(RestClient.class);
    private final String url;
    private final RequestSpecification request;
    private final HttpHeaders headers = new HttpHeaders();
    private final ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();

    public RestClient(final String url, final HttpHeaders headers) {
        headers.forEach(this.headers::addAll);

        this.url = url;
        request = RestAssured
                .given()
                .headers(headers)
                .baseUri(url);
    }

    public Response GET(final String endpoint) {
        return request.when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }
    public Response POST(final String endpoint, final Object requestBody) {
        return request
                .when()
                .body(requestBody)
                .post(endpoint)
                .then()
                .extract()
                .response();
    }
    public Response DELETE(final String endpoint) {
        return request
                .when()
                .delete(endpoint)
                .then()
                .extract()
                .response();
    }
    private void logCurl(final String requestType, final String endpoint, final Object body){
        StringBuilder curlCommand = new StringBuilder("curl -X ").append(requestType).append(" ");

        headers.forEach((key, value) ->
                value.forEach(val -> curlCommand.append("-H \"").append(key).append(": ").append(val).append("\" ")));

        curlCommand.append(url).append(endpoint).append(" ");

        if (body != null) {
            try {
                String jsonPayload = mapper.writeValueAsString(body);
                curlCommand.append("-d '").append(jsonPayload).append("' ");
            } catch (JsonProcessingException e) {
                logger.debug("Failed to convert payload to JSON", e);
            }
        }

        logger.info("Curl command: " + curlCommand.toString().trim());
    }
}
