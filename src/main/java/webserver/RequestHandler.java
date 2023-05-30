package webserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Map;
import model.User;
import model.http.request.HttpRequest;
import model.http.request.HttpRequestBuilder;
import model.http.response.HttpResponse;
import model.http.response.HttpResponseBuilder;
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
        logger.debug("New Client Connect! Connected IP : {}, Port : {}",
                connection.getInetAddress(),
                connection.getPort());

        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
            BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            DataOutputStream dos = new DataOutputStream(out);

            HttpRequest httpRequest = HttpRequestBuilder.build(br);
            logger.debug("\nRequest: {}", httpRequest);

            String url = httpRequest.getUrl();

            if (url.startsWith("/user/create")) {
                Map<String, String> params = httpRequest.getParams();
                User user = new User(
                        params.get("userId"), params.get("password"),
                        params.get("name"), params.get("email"));
                logger.debug("User: {}", user);
                url = "/index.html";
            }

            // View 찾기
            String view = ViewResolver.getViewFrom(Paths.get(url));
            logger.debug("View: {}", view);

            // Response 생성
            HttpResponse httpResponse = HttpResponseBuilder.build(new HttpResponse(), view, 200, "OK");

            writeResponse(dos, httpResponse);
            logger.debug("\nResponse: {}", httpResponse);

        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void writeResponse(DataOutputStream dos, HttpResponse httpResponse) {
        try {
            dos.writeBytes(httpResponse.getStatusLine().toString());
            dos.writeBytes(System.lineSeparator());

            dos.writeBytes(httpResponse.getResponseHeader().toString());
            dos.writeBytes(System.lineSeparator());
            dos.writeBytes(System.lineSeparator());

            dos.write(httpResponse.getBody(), 0, httpResponse.getBody().length);

            dos.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
