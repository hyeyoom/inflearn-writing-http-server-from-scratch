package com.github.hyeyoom.request;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RequestHeaders {

    private final Map<String, String> headers;

    public RequestHeaders(List<String> rawHeaders) {
        /* 
        스트림으로 변환
        this.headers = new HashMap<>();
        this.headers = rawHeaders
                .stream()
                .map(rawHeader -> rawHeader.split(":"))
                .collect(
                        Collectors.toMap(
                                nameAndValue -> nameAndValue[0].trim(),
                                nameAndValue -> nameAndValue[1].trim()
                        )
                );
        */
        this.headers = new HashMap<>();
        for (String rawHeader : rawHeaders) {
            final String[] fieldNameAndFieldValue = rawHeader.split(":");
            headers.put(fieldNameAndFieldValue[0].trim(), fieldNameAndFieldValue[1].trim());
        }
    }

    public String getHeaderValue(String headerName) {
        return this.headers.get(headerName);
    }

    @Override
    public String toString() {
        return "RequestHeaders{" +
                "headers=" + headers +
                '}';
    }
}
