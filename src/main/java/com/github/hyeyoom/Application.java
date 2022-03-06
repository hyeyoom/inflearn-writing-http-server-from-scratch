package com.github.hyeyoom;

import java.io.*;

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

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("line = " + line);
            }
        }
    }
}
