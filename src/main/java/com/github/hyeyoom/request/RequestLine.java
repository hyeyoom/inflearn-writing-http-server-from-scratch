package com.github.hyeyoom.request;

public class RequestLine {

    private final HttpMethod method;
    private final String requestURI;
    private final String protocol;

    public RequestLine(HttpMethod method, String requestURI, String protocol) {
        this.method = method;
        this.requestURI = requestURI;
        this.protocol = protocol;
    }
}
