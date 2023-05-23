package model.http.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpRequestBuilder {

    public static HttpRequest build(BufferedReader br) throws IOException {
        RequestLine requestLine = new RequestLine(br.readLine().split(" "));
        RequestHeader requestHeader = parseRequestHeader(br);
        return new HttpRequest(requestLine, requestHeader);
    }

    private static RequestHeader parseRequestHeader(BufferedReader br) throws IOException {
        List<String> requestHeaders = new ArrayList<>();
        String oneLine;
        while (!(oneLine = br.readLine()).equals("")) {
            requestHeaders.add(oneLine);
        }
        return new RequestHeader(requestHeaders);
    }
}
