package servlet;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import model.http.request.HttpRequest;
import model.http.response.ContentType;
import model.http.response.HttpResponse;
import model.http.response.StatusLine;
import view.ViewResolver;

public class HttpServlet {

    public void service(HttpRequest request, HttpResponse response) throws IOException {
        String method = request.getMethod();

        if (method.equals("GET")) {
            doGet(request, response);
        }

        if (method.equals("POST")) {
            doPost(request, response);
        }
    }

    protected void doGet(HttpRequest request, HttpResponse response) throws IOException {
        String path = Path.of(request.getUrl()).toString();

        byte[] view = ViewResolver.getView(path);
        StatusLine statusLine = new StatusLine(200, "OK");

        response.setStatusLine(statusLine);
        response.getHeader().add("Content-Type", List.of(ContentType.from(path).getValue()));
        response.getHeader().add("Content-Length", List.of(String.valueOf(view.length)));
        response.setBody(view);
    }

    protected void doPost(HttpRequest request, HttpResponse response) throws IOException {
    }

}
