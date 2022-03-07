package com.github.hyeyoom;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 3.3. 과제
 * Request 객체에 파싱한 데이터를 잘 넣어봅시다.
 */
public class Application {
    public static void main(String[] args) throws IOException {
        final File fileToRead = new File("http_message.txt");
        try (InputStream inputStream = new FileInputStream(fileToRead)) {
            final BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            // 1. 요청라인
            final String rawRequestLine = br.readLine();
            final String[] methodAndURIAndProtocol = rawRequestLine.split(" ");
            final RequestLine requestLine = new RequestLine(
                    HttpMethod.valueOf(methodAndURIAndProtocol[0].trim()),
                    methodAndURIAndProtocol[1].trim(),
                    methodAndURIAndProtocol[2].trim()
            );
            System.out.println("requestLine = " + requestLine);

            // 2. 요청 헤더들
            final List<String> rawHeaderAndValueList = new ArrayList<>();
            String rawHeader;
            while (!"".equals(rawHeader = br.readLine())) {
                rawHeaderAndValueList.add(rawHeader);
            }
            final Map<String, String> headerMap = new HashMap<>();
            for (String rawHeaderAndValue : rawHeaderAndValueList) {
                final String[] headerAndValue = rawHeaderAndValue.split(":");
                headerMap.put(headerAndValue[0].trim(), headerAndValue[1].trim());
            }
            final RequestHeaders requestHeaders = new RequestHeaders(headerMap);
            System.out.println("requestHeaders = " + requestHeaders);

            // 3. 요청 바디
            final List<String> rawBodyLineList = new ArrayList<>();
            String rawBody;
            while ((rawBody = br.readLine()) != null) {
                rawBodyLineList.add(rawBody);
            }
            final byte[] body = String.join("", rawBodyLineList).getBytes(StandardCharsets.UTF_8);
            final Request request = new Request(requestLine, requestHeaders, body);
        }
    }
}
