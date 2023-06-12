package servlet.home;

import annotation.RequestMapping;
import java.io.IOException;
import java.util.List;
import model.http.request.HttpRequest;
import model.http.response.ContentType;
import model.http.response.HttpResponse;
import model.http.response.StatusLine;
import servlet.HttpServlet;
import view.ViewResolver;

public class HomeServlet extends HttpServlet {

    @Override
    @RequestMapping(url = "/")
    public void doGet(HttpRequest request, HttpResponse response) throws IOException {
        StatusLine statusLine = new StatusLine(200, "OK");
        String viewName = "/index.html";
        byte[] view = ViewResolver.getView(viewName);

        response.setStatusLine(statusLine);
        response.getHeader().add("Content-Type", List.of(ContentType.from(viewName).getValue()));
        response.getHeader().add("Content-Length", List.of(String.valueOf(view.length)));
        response.setBody(view);
    }

}
