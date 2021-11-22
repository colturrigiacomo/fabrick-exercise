package com.fabrick.exercise.interceptors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class HttpHeaderInterceptor implements ClientHttpRequestInterceptor {

    public static final String AUTH_SCHEMA = "Auth-Schema";
    public static final String API_KEY = "Api-Key";

    @Value("${api.key}")
    private String apiKey;

    @Value("${auth.schema}")
    private String authSchema;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpHeaders headers = request.getHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(AUTH_SCHEMA, authSchema);
        headers.set(API_KEY, apiKey);
        return execution.execute(request, body);
    }
}
