package com.github.hyeyoom;

public class RequestLine {

    private final HttpMethod httpMethod;
    private final String requestURI;
    private final String protocol;

    public RequestLine(String rawRequestLine) {
        final String[] methodAndURIAndProtocol = rawRequestLine.split(" ");
        this.httpMethod = HttpMethod.valueOf(methodAndURIAndProtocol[0].trim());
        this.requestURI = methodAndURIAndProtocol[1].trim();
        this.protocol = methodAndURIAndProtocol[2].trim();
    }

    @Override
    public String toString() {
        return "RequestLine{" +
                "httpMethod=" + httpMethod +
                ", requestURI='" + requestURI + '\'' +
                ", protocol='" + protocol + '\'' +
                '}';
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
