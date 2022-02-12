package com.github.hyeyoom.request;

public class RequestLine {

    private final HttpMethod method;
    private final String requestURI;
    private final String protocol;

    public RequestLine(final String rawRequestLine) {
        final String[] methodAndURIAndProtocol = rawRequestLine.split(" ");
        this.method = HttpMethod.valueOf(methodAndURIAndProtocol[0]);
        this.requestURI = methodAndURIAndProtocol[1];
        this.protocol = methodAndURIAndProtocol[2];
    }

    @Override
    public String toString() {
        return "RequestLine{" +
                "method=" + method +
                ", requestURI='" + requestURI + '\'' +
                ", protocol='" + protocol + '\'' +
                '}';
    }
}
