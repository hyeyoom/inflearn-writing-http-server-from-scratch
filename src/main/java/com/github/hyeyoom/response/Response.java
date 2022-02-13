package com.github.hyeyoom.response;

public class Response {

    private ResponseLine responseLine;
    private ResponseHeaders responseHeaders;
    private byte[] body;

    public static Response ok() {
        return null;
    }

    public Response addHeader(String name, String value) {
        return null;
    }

    public Response body(String html) {
        return null;
    }

    public byte[] toBytes() {
        return null;
    }
}
