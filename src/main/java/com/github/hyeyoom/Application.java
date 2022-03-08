package com.github.hyeyoom;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 3.3. 과제
 * Request 객체에 파싱한 데이터를 잘 넣어봅시다.
 */
public class Application {
    public static void main(String[] args) throws IOException {
        final File fileToRead = new File("http_message.txt");
        try (InputStream inputStream = new FileInputStream(fileToRead)) {
            final Request request = new Request(inputStream);
            System.out.println("request = " + request);
        }
    }
}
