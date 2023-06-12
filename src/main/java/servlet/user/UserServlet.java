package servlet.user;

import annotation.RequestMapping;
import java.io.IOException;
import java.util.List;
import model.http.request.HttpRequest;
import model.http.response.ContentType;
import model.http.response.HttpResponse;
import model.http.response.StatusLine;
import servlet.HttpServlet;
import view.ViewResolver;

public class UserServlet extends HttpServlet {

    private final UserService userService;

    public UserServlet() {
        this.userService = new UserService();
    }

    @Override
    @RequestMapping(url = "/user/create")
    protected void doGet(HttpRequest request, HttpResponse response) throws IOException {
        StatusLine statusLine = new StatusLine(200, "OK");
        String viewName = "/user/form.html";
        byte[] view = ViewResolver.getView(viewName);

        response.setStatusLine(statusLine);
        response.getHeader().add("Content-Type", List.of(ContentType.from(viewName).getValue()));
        response.getHeader().add("Content-Length", List.of(String.valueOf(view.length)));
        response.setBody(view);
    }

    @Override
    @RequestMapping(url = "/user/join")
    protected void doPost(HttpRequest request, HttpResponse response) {
        StatusLine statusLine = new StatusLine(302, "FOUND");

        response.setStatusLine(statusLine);
        response.getHeader().add("Location", List.of("/"));
        byte[] body = {};
        response.setBody(body);
        userService.join(request.getParams());
    }
}
