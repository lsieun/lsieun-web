package lsieun.net.http;

import lsieun.net.http.bean.*;
import lsieun.net.http.utils.HttpHeaderUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class HttpByteParser {
    public static HttpRequest toHttpRequest(final byte[] bytes) {
        Range first_line_range = getFirstLineRange(bytes);
        if (first_line_range == null) return null;
        RequestLine request_line = getRequestLine(bytes, first_line_range.from, first_line_range.to);
        boolean contain_payload = "POST".equalsIgnoreCase(request_line.method);

        Range header_range = getHeaderRange(bytes, first_line_range.to);
        if (header_range == null) return null;
        HttpHeader header = getHeader(bytes, header_range.from, header_range.to - header_range.from);

        if (!contain_payload) {
            return new HttpRequest(request_line, header, null, header_range.to);
        }

        int contentLength = header.getContentLength();
        Range payloadRange = getPayloadRange(bytes, header_range.to, contentLength);
        if (payloadRange == null) return null;


        byte[] payload = new byte[contentLength];
        for (int i = 0; i < contentLength; i++) {
            payload[i] = bytes[payloadRange.from + i];
        }
        return new HttpRequest(request_line, header, payload, payloadRange.to);
    }

    private static Range getFirstLineRange(final byte[] bytes) {
        int length = bytes.length;
        byte[] marks = new byte[2];
        for (int i = 0; i < length; i++) {
            byte b = bytes[i];
            marks[0] = marks[1];
            marks[1] = b;
            if (marks[0] == '\r' && marks[1] == '\n') {
                return new Range(0, i);
            }
        }
        return null;
    }

    private static Range getHeaderRange(final byte[] bytes, int from) {
        int length = bytes.length;
        byte[] marks = new byte[4];
        for (int i = from; i < length; i++) {
            byte b = bytes[i];
            marks[0] = marks[1];
            marks[1] = marks[2];
            marks[2] = marks[3];
            marks[3] = b;
            if (marks[0] == '\r' && marks[1] == '\n' &&
                    marks[2] == '\r' && marks[3] == '\n'
            ) {
                return new Range(from, i + 1);
            }
        }
        return null;
    }

    private static Range getPayloadRange(final byte[] bytes, int from, int length) {
        if (from + length <= bytes.length) {
            return new Range(from, from + length);
        }
        return null;
    }

    public static RequestLine getRequestLine(final byte[] bytes) {
        return getRequestLine(bytes, 0, bytes.length);
    }

    public static RequestLine getRequestLine(final byte[] bytes, int offset, int length) {
        String first_line = new String(bytes, offset, length, StandardCharsets.US_ASCII).trim();
        String[] first_line_array = first_line.split("\\s", 3);
        String method = first_line_array[0];
        String path = first_line_array[1];
        String version = first_line_array[2];

        RequestLine request_line = new RequestLine(method, path, version);
        return request_line;
    }

    public static HttpHeader getHeader(final byte[] bytes) {
        return getHeader(bytes, 0, bytes.length);
    }

    public static HttpHeader getHeader(final byte[] bytes, int offset, int length) {
        String header_value = new String(bytes, offset, length, StandardCharsets.US_ASCII).trim();
        String[] array = header_value.split("\\r\\n");

        HttpHeader header = new HttpHeader();
        for (String str : array) {
            String[] kv = str.split("[:]", 2);
            String key = kv[0].trim();
            String value = kv[1].trim();
            header.add(key, value);
        }
        return header;
    }



}
