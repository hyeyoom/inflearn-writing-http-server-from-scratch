package com.github.hyeyoom;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 3.2. 과제
 *
 * 읽어온 데이터를 요청 메세지의 형식에 맞게 다음과 같이 출력해봅시다._.
 * 1. 요청라인
 *  - method
 *  - URI
 *  - protocol
 * 2. Headers
 * 3. Body
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
