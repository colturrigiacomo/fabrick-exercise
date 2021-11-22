package com.fabrick.exercise.services;

import com.fabrick.exercise.exceptions.FabrickException;
import com.fabrick.exercise.mappers.Mapper;
import com.fabrick.exercise.models.*;
import com.fabrick.exercise.models.generics.GenericResponse;
import com.fabrick.exercise.models.requests.MoneyTransfer;
import com.fabrick.exercise.utility.ExceptionUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BankServiceImpl implements BankService {

    @Value("${fabrick.url}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Balance getBalance(String accountId) {

        String completedUrl = url + "/" + accountId + "/balance";

        try{
            ResponseEntity<GenericResponse<Balance>> response = restTemplate.exchange(
                    completedUrl, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                    });
            if( response.getBody() != null ){
                return response.getBody().getPayload();
            }
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            genericException(e.getResponseBodyAsString(), e.getStatusCode(), e.getMessage());
        }

        return null;
    }

    @Override
    public List<Transaction> getTransactions(String accountId, Date dateFrom, Date dateTo) {

        String completedUrl = url + "/" + accountId + "/transactions";
        completedUrl = UriComponentsBuilder.fromHttpUrl(completedUrl)
                .queryParam("fromAccountingDate", "{dateFrom}")
                .queryParam("toAccountingDate", "{dateTo}")
                .encode().toUriString();

        Map<String, String> params = new HashMap<>();
        params.put("dateFrom", dt.format(dateFrom));
        params.put("dateTo", dt.format(dateTo));

        try {
            ResponseEntity<GenericResponse<TransactionsList>> response = restTemplate.exchange(
                    completedUrl, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                    }, params);
            if( response.getBody() != null ){
                return response.getBody().getPayload().getList();
            }

        } catch (HttpClientErrorException | HttpServerErrorException e) {
            genericException(e.getResponseBodyAsString(), e.getStatusCode(), e.getMessage());
        }

        return null;
    }

    @Override
    public PaymentResponse createMoneyTransfer(MoneyTransfer req, String accountId){
        String completedUrl = url + "/" + accountId + "/payments/money-transfers";

        Payment payment = Mapper.mapMoneyTrasferToPayment(req);
        HttpEntity<Payment> entity = new HttpEntity<>(payment);

        try {
            ResponseEntity<GenericResponse<PaymentResponse>> response = restTemplate.exchange(
                    completedUrl, HttpMethod.POST, entity, new ParameterizedTypeReference<>() {
                    });
            if( response.getBody() != null ){
                return response.getBody().getPayload();
            }
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            genericException(e.getResponseBodyAsString(), e.getStatusCode(), e.getMessage());
        }

        return null;
    }

    private void genericException(String responseBodyAsString, HttpStatus httpStatus, String message) {
        if (StringUtils.isNotBlank(responseBodyAsString) ){
            GenericResponse<?> genericResponse = null;
            try{
                genericResponse = objectMapper.readValue(responseBodyAsString, GenericResponse.class);
            } catch (JsonProcessingException e){
                throw new FabrickException(ExceptionUtil.genericError(message), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            throw new FabrickException(genericResponse.getErrors(), httpStatus);
        }
        throw new FabrickException(ExceptionUtil.genericError(message), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
