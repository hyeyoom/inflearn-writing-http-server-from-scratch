package com.github.hyeyoom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Application {
    public static void main(String[] args) throws IOException {
        // 1. 브라우저의 요청을 8080으로 받고
        final ServerSocket serverSocket = new ServerSocket(8080);
        try (Socket socket = serverSocket.accept()) {
            try (InputStream inputStream = socket.getInputStream()) {
                final BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

                String line = null;
                while (!"".equals(line = br.readLine())) {
                    // 2. 문자열로 바꾸어 출력해보세요
                    System.out.println("line = " + line);
                }
            }
        }
    }
}
