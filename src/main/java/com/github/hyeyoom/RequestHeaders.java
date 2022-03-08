package com.github.hyeyoom;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestHeaders {

    private final Map<String, String> headers;

    public RequestHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public RequestHeaders(List<String> rawHeaderAndValueList) {
        final Map<String, String> headerMap = new HashMap<>();
        for (String rawHeaderAndValue : rawHeaderAndValueList) {
            final String[] headerAndValue = rawHeaderAndValue.split(":");
            headerMap.put(headerAndValue[0].trim(), headerAndValue[1].trim());
        }
        this.headers = headerMap;
    }

    @Override
    public String toString() {
        return "RequestHeaders{" +
                "headers=" + headers +
                '}';
    }
}
