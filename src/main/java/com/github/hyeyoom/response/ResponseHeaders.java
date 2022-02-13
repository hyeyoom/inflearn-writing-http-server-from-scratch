package com.github.hyeyoom.response;

import java.util.Map;

public class ResponseHeaders {

    private final Map<String, String> responseHeaders;

    public ResponseHeaders(Map<String, String> responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

    public void addHeader(String name, String value) {
        responseHeaders.put(name, value);
    }

    public Map<String, String> getResponseHeaders() {
        return responseHeaders;
    }

    @Override
    public String toString() {
        return "ResponseHeaders{" +
                "responseHeaders=" + responseHeaders +
                '}';
    }
}
