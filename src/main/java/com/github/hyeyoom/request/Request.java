package com.github.hyeyoom.request;

import java.util.Arrays;

public class Request {

    private final RequestLine requestLine;
    private final RequestHeaders requestHeaders;
    private final byte[] body;

    public Request(RequestLine requestLine, RequestHeaders requestHeaders, byte[] body) {
        this.requestLine = requestLine;
        this.requestHeaders = requestHeaders;
        this.body = body;
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestLine=" + requestLine +
                ", requestHeaders=" + requestHeaders +
                ", body=unable to show}";
    }
}
