package servlet.login;

import Session.SessionIdManager;
import annotation.RequestMapping;
import db.Database;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import model.User;
import model.http.request.HttpRequest;
import model.http.response.ContentType;
import model.http.response.HttpResponse;
import model.http.response.StatusLine;
import servlet.HttpServlet;
import view.ViewResolver;

public class LoginServlet extends HttpServlet {

    // TODO: UserService를 이미 UserServlet에서 사용하고 있어서 문제가 생기는 듯하다.
//    private final UserService userService;
//
//    public LoginServlet(UserService userService) {
//        this.userService = userService;
//    }

    @Override
    @RequestMapping(url = "/user/login")
    protected void doGet(HttpRequest request, HttpResponse response) throws IOException {
        StatusLine statusLine = new StatusLine(200, "OK");
        String viewName = "/user/login.html";
        byte[] view = ViewResolver.getView(viewName);

        response.setStatusLine(statusLine);
        response.getHeader().add("Content-Type", List.of(ContentType.from(viewName).getValue()));
        response.getHeader().add("Content-Length", List.of(String.valueOf(view.length)));
        response.setBody(view);
    }

    @Override
    @RequestMapping(url = "/user/login/request")
    protected void doPost(HttpRequest request, HttpResponse response) throws IOException {
        StatusLine statusLine = new StatusLine(302, "FOUND");

        response.setStatusLine(statusLine);

        byte[] body = {};
        response.setBody(body);

        if (login(request.getParams())) {
            String sessionId = SessionIdManager.createSession();
            response.getHeader().add("Set-Cookie", List.of("jsession=" + sessionId +"; Path = /"));
            response.getHeader().add("Location", List.of("/"));
        } else {
            response.getHeader().add("Location", List.of("/user/login_failed.html"));
        }
    }

    // TODO: UserService에 만들었는데, 에러가 있어서 임의로 갖고 왔다.
    public boolean login(Map<String, String> params) {
        User findUser = Database.findUserById(params.get("userId"));
        return findUser != null && findUser.getPassword().equals(params.get("password"));
    }
}
