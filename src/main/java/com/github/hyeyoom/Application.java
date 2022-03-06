package com.github.hyeyoom;

import java.io.*;

public class Application {
    public static void main(String[] args) throws IOException {
        // 1. 파일스트림에서 파일을 로드해봅시다.
        final File fileToRead = new File("http_message.txt");
        try (InputStream inputStream = new FileInputStream(fileToRead)) {
            final BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = br.readLine()) != null) {
                // 2. 로드한 내용을 화면에 출력해봅시다.
                System.out.println("line = " + line);
            }
        }
    }
}
