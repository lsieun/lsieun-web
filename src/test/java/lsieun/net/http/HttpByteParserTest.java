package lsieun.net.http;

import lsieun.net.http.bean.RequestLine;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class HttpByteParserTest {

    @Test
    public void getRequestLine() {
        String first_line = "GET /path/to/file.html HTTP/1.1";
        byte[] bytes = first_line.getBytes(StandardCharsets.US_ASCII);
        final RequestLine requestLine = HttpByteParser.getRequestLine(bytes);
        String result = String.format("%s %s %s", requestLine.method, requestLine.path, requestLine.version);
        assertEquals(first_line, result);
    }
}