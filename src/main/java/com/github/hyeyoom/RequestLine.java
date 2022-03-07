package com.github.hyeyoom;

public class RequestLine {

    private final HttpMethod httpMethod;
    private final String requestURI;
    private final String protocol;

    public RequestLine(HttpMethod httpMethod, String requestURI, String protocol) {
        this.httpMethod = httpMethod;
        this.requestURI = requestURI;
        this.protocol = protocol;
    }

    @Override
    public String toString() {
        return "RequestLine{" +
                "httpMethod=" + httpMethod +
                ", requestURI='" + requestURI + '\'' +
                ", protocol='" + protocol + '\'' +
                '}';
    }
}
