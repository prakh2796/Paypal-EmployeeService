package com.paypal.bfs.test.employeeserv.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClientResponseException;

import java.nio.charset.Charset;

public class EmployeeCrudException extends RestClientResponseException {
    public EmployeeCrudException(String message, int statusCode, String statusText, HttpHeaders responseHeaders, byte[] responseBody, Charset responseCharset) {
        super(message, statusCode, statusText, responseHeaders, responseBody, responseCharset);
    }
}
