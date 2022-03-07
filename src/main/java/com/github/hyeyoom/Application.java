package com.github.hyeyoom;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
            System.out.println("1. Request Line");
            System.out.println("   -> method  : " + methodAndURIAndProtocol[0].trim());
            System.out.println("   -> URI     : " + methodAndURIAndProtocol[1].trim());
            System.out.println("   -> protocol: " + methodAndURIAndProtocol[2].trim());

            // 2. 요청 헤더들
            final List<String> rawHeaderAndValueList = new ArrayList<>();
            String rawHeader;
            while (!"".equals(rawHeader = br.readLine())) {
                rawHeaderAndValueList.add(rawHeader);
            }
            System.out.println("2. Headers");
            for (String rawHeaderAndValue : rawHeaderAndValueList) {
                final String[] headerAndValue = rawHeaderAndValue.split(":");
                System.out.println("   -> name: " + headerAndValue[0].trim() + ", value: " + headerAndValue[1].trim());
            }

            // 3. 요청 바디
            final List<String> rawBodyLineList = new ArrayList<>();
            String rawBody;
            while ((rawBody = br.readLine()) != null) {
                rawBodyLineList.add(rawBody);
            }
            System.out.println("3. Body");
            for (String bodyLine : rawBodyLineList) {
                System.out.println("   -> " + bodyLine);
            }
        }
    }
}
