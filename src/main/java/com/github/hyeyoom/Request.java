package com.github.hyeyoom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Request {

    private final RequestLine requestLine;
    private final RequestHeaders requestHeaders;
    private final byte[] body;

    public Request(RequestLine requestLine, RequestHeaders requestHeaders, byte[] body) {
        this.requestLine = requestLine;
        this.requestHeaders = requestHeaders;
        this.body = body;
    }

    public Request(InputStream inputStream) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        // 1. 요청라인
        final String rawRequestLine = br.readLine();
        this.requestLine = new RequestLine(rawRequestLine);

        // 2. 요청 헤더들
        final List<String> rawHeaderAndValueList = new ArrayList<>();
        String rawHeader;
        while (!"".equals(rawHeader = br.readLine())) {
            rawHeaderAndValueList.add(rawHeader);
        }
        this.requestHeaders = new RequestHeaders(rawHeaderAndValueList);

        // 3. 요청 바디
        final List<String> rawBodyLineList = new ArrayList<>();
        String rawBody;
        while ((rawBody = br.readLine()) != null) {
            rawBodyLineList.add(rawBody);
        }
        this.body = String.join("", rawBodyLineList).getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestLine=" + requestLine +
                ", requestHeaders=" + requestHeaders +
                ", body=" + Arrays.toString(body) +
                '}';
    }
}
