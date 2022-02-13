package com.github.hyeyoom.response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Response {

    private ResponseLine responseLine;
    private ResponseHeaders responseHeaders;
    private byte[] body;

    public Response(ResponseLine responseLine) {
        this.responseLine = responseLine;
        this.responseHeaders = new ResponseHeaders(new HashMap<>());
    }

    public static Response ok() {
        return new Response(new ResponseLine("HTTP/1.1", 200, "OK"));
    }

    public Response addHeader(String name, String value) {
        responseHeaders.addHeader(name, value);
        return this;
    }

    public Response body(String html) {
        body = html.getBytes(StandardCharsets.UTF_8);
        return this;
    }

    public byte[] toBytes() {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            final char CR = '\r';
            final char LF = '\n';
            baos.write(responseLine.getRequestLineString().getBytes(StandardCharsets.UTF_8));
            baos.write(CR);
            baos.write(LF);
            if (body != null) {
                final int contentLength = body.length;
                addHeader("Content-Length", String.valueOf(contentLength));
            }

            final Map<String, String> responseHeaders = this.responseHeaders.getResponseHeaders();
            for (Map.Entry<String, String> entry : responseHeaders.entrySet()) {
                baos.write(String.format("%s: %s", entry.getKey(), entry.getValue()).getBytes(StandardCharsets.UTF_8));
                baos.write(CR);
                baos.write(LF);
            }
            baos.write(CR);
            baos.write(LF);

            if (body != null) {
                baos.write(body);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return "Response{" +
                "responseLine=" + responseLine +
                ", responseHeaders=" + responseHeaders +
                ", body=unable to show}";
    }
}
