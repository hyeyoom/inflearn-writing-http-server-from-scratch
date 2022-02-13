package com.github.hyeyoom;

import com.github.hyeyoom.request.Request;
import com.github.hyeyoom.request.RequestHeaders;
import com.github.hyeyoom.request.RequestLine;
import com.github.hyeyoom.response.Response;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        final File fileToRead = new File("HTTPMessageToParse.txt");
        try (final InputStream inputStream = new FileInputStream(fileToRead)) {
            final byte[] rawBytes = readBytes(inputStream);

            final InputStream bais = new ByteArrayInputStream(rawBytes);
            final BufferedReader br = new BufferedReader(new InputStreamReader(bais));

            final String rawRequestLine = br.readLine();
            final RequestLine requestLine = new RequestLine(rawRequestLine);

            final List<String> rawLines = getRawLines(br);
            final RequestHeaders requestHeaders = new RequestHeaders(rawLines);

            final String maybeContentLength = requestHeaders.getHeaderValue("Content-Length");
            int contentLength = getContentLength(maybeContentLength);

            final int indexOfEndOfRawBytes = getEndOfRawBytesIndex(rawBytes);
            final int bodyOffset = getBodyOffset(rawBytes);

            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            for (int i = bodyOffset; i < indexOfEndOfRawBytes; i++) {
                baos.write(rawBytes[i]);
            }

            final byte[] rawBody = baos.toByteArray();
            final Request request = new Request(requestLine, requestHeaders, rawBody);
            System.out.println("request = " + request);
        } catch (IOException e) {
            e.printStackTrace();
        }

        final File responseFile = new File("http_response.bin");
        try (final OutputStream outputStream = new FileOutputStream(responseFile)) {
            final Response response = Response
                    .ok()
                    .addHeader("Content-Type", "text/html;")
                    .body("<!DOCTYPE html>\n" +
                            "<html lang=\"ko\">\n" +
                            "<head>\n" +
                            "    <meta charset=\"UTF-8\">\n" +
                            "    <title>바닥부터 작성해보는 웹서버</title>\n" +
                            "</head>\n" +
                            "<body>\n" +
                            "<h1>귀여운 멍멍이를 드리겠습니다</h1>\n" +
                            "<img src=\"https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/dog-puppy-on-garden-royalty-free-image-1586966191.jpg\"/>\n" +
                            "</body>\n" +
                            "</html>"
                    );
            System.out.println("response = " + response);
            final byte[] rawResponse = response.toBytes();
            System.out.println(new String(rawResponse));
            outputStream.write(rawResponse);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getContentLength(String maybeContentLength) {
        if (maybeContentLength != null) {
            return Integer.parseInt(maybeContentLength);
        } else {
            return -1;
        }
    }

    private static int getBodyOffset(byte[] rawBytes) {
        final char CR = '\r';
        final char LF = '\n';
        for (int i = 0; i < rawBytes.length; i++) {
            if (rawBytes[i] == CR && rawBytes[i + 1] == LF && rawBytes[i + 2] == CR && rawBytes[i + 3] == LF) {
                return i + 4;
            }
        }
        return -1;
    }

    private static int getEndOfRawBytesIndex(byte[] rawBytes) {
        for (int i = 0; i < rawBytes.length; i++) {
            if (rawBytes[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    private static int getContentLength(List<String> rawLines) {
        for (String rawLine : rawLines) {
            if (rawLine.startsWith("Content-Length")) {
                final String[] fieldNameAndFieldValue = rawLine.split(":");
                return Integer.parseInt(fieldNameAndFieldValue[1].trim());
            }
        }
        return -1;
    }

    private static List<String> getRawLines(BufferedReader br) throws IOException {
        final List<String> rawLines = new ArrayList<>();
        String line;
        while (!"".equals(line = br.readLine())) {
            rawLines.add(line);
        }
        return rawLines;
    }

    private static byte[] readBytes(InputStream inputStream) throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final byte[] buffer = new byte[4096];
        while (inputStream.read(buffer) != -1) {
            baos.write(buffer);
        }
        return baos.toByteArray();
    }
}
