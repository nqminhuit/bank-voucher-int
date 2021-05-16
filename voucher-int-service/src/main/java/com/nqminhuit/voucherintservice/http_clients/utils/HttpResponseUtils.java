package com.nqminhuit.voucherintservice.http_clients.utils;

import java.net.URI;
import java.net.http.HttpClient.Version;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import javax.net.ssl.SSLSession;

public class HttpResponseUtils {

    public static HttpResponse<String> response(String body, int statusCode) {
        return new HttpResponse<String>() {

            @Override
            public String body() {
                return body;
            }

            @Override
            public HttpHeaders headers() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public Optional<HttpResponse<String>> previousResponse() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public HttpRequest request() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public Optional<SSLSession> sslSession() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public int statusCode() {
                return statusCode;
            }

            @Override
            public URI uri() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public Version version() {
                // TODO Auto-generated method stub
                return null;
            }
        };
    }
}
