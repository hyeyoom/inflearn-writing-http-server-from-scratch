package com.github.hyeyoom;

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
            final List<String> rawLines = getRawLines(br);
            final int contentLength = getContentLength(rawLines);

            final int indexOfEndOfRawBytes = getEndOfRawBytesIndex(rawBytes);
            final int bodyOffset = getBodyOffset(rawBytes);

            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            for (int i = bodyOffset; i < indexOfEndOfRawBytes; i++) {
                baos.write(rawBytes[i]);
            }
            final byte[] rawBody = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
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
