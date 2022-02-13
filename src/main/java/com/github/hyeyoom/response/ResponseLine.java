package com.github.hyeyoom.response;

public class ResponseLine {

    private final String httpVersion;
    private final int statusCode;
    private final String reasonPhrase;

    public ResponseLine(String httpVersion, int statusCode, String reasonPhrase) {
        this.httpVersion = httpVersion;
        this.statusCode = statusCode;
        this.reasonPhrase = reasonPhrase;
    }
}
