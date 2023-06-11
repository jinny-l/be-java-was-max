package model.http.request;

import java.util.Map;
import model.http.Header;

public class HttpRequest {

    private final RequestLine requestLine;
    private final Header header;
    private final RequestBody requestBody;

    public HttpRequest(RequestLine requestLine, Header header, RequestBody requestBody) {
        this.requestLine = requestLine;
        this.header = header;
        this.requestBody = requestBody;
    }

    public String getUrl() {
        return requestLine.getUrl();
    }

    public Map<String, String> getParams() {
        Map<String, String> paramsOfGet = requestLine.getParams();
        if (paramsOfGet.isEmpty()) { // 쿼리 스트링에 파라미터가 없을 때 바디에 파라미터 반환
            return requestBody.getParams();
        }
        return paramsOfGet; // 쿼리 스트링에 파라미터가 있을 때 해당 파라미터 반환
    }

    public String getMethod() {
        return requestLine.getMethod();
    }

    @Override
    public String toString() {
        return "\n------start-------"
                + "\n" + requestLine
                + "\n" + header
                + "\n"
                + "\n" + requestBody
                + "\n------end-------";
    }
}
