package com.github.hyeyoom.response;

import java.util.Map;

public class ResponseHeaders {

    private final Map<String, String> responseHeaders;

    public ResponseHeaders(Map<String, String> responseHeaders) {
        this.responseHeaders = responseHeaders;
    }
}
