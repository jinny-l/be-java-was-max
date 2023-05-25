package webserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import model.User;
import model.http.request.HttpRequest;
import model.http.request.HttpRequestBuilder;
import model.http.response.HttpResponse;
import model.http.response.ResponseHeader;
import model.http.response.StatusLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import user.UserController;
import user.UserService;
import view.ViewResolver;

public class RequestHandler implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);

    private final UserController userController;

    private Socket connection;

    public RequestHandler(Socket connectionSocket) {
        this.connection = connectionSocket;
        userController = new UserController(new UserService());
    }

    public void run() {
        logger.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                connection.getPort());

        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
            BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));

            HttpRequest httpRequest = HttpRequestBuilder.build(br);
            logger.debug("Request: {}", httpRequest);

            String url = httpRequest.getUrl();

            if (url.startsWith("/user/create")) {
                Map<String, String> params = httpRequest.getParams();
                User user = new User(
                        params.get("userId"), params.get("password"),
                        params.get("name"), params.get("email"));
                logger.debug("User: {}", user);
                url = "/index.html";
            }

            // Response 생성
            HttpResponse httpResponse = new HttpResponse();

            // status line
            StatusLine statusLine = new StatusLine(200, "OK");
            httpResponse.setStatusLine(statusLine);

            // View 찾기
            String view = ViewResolver.getView(Paths.get(url));
            logger.debug("View: {}", view);

            // body
            DataOutputStream dos = new DataOutputStream(out);
            logger.debug(Files.readString(Path.of(view)));
            byte[] body = Files.readAllBytes(
                    new File(view).toPath() // TODO: 경로 별도로 추출
            );

            // header
            ResponseHeader responseHeader = new ResponseHeader(url, body.length);
            httpResponse.setResponseHeader(responseHeader);

            response200Header(dos, httpResponse);
            responseBody(dos, body);
            logger.debug("Response: \n{}", httpResponse);

        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void response200Header(DataOutputStream dos, HttpResponse httpResponse) {
        try {
            dos.writeBytes(httpResponse.toString());
//            dos.write(body, 0, body.length);
//            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
//            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
//            dos.writeBytes("\r\n");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void responseBody(DataOutputStream dos, byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
