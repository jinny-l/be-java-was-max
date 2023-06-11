package webserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import model.http.request.HttpRequest;
import model.http.request.HttpRequestBuilder;
import model.http.response.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import servlet.DispatcherServlet;

public class RequestHandler implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);

    private Socket connection;

    public RequestHandler(Socket connectionSocket) {
        this.connection = connectionSocket;
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

            HttpResponse httpResponse = new HttpResponse();
            DispatcherServlet dispatcherServlet = new DispatcherServlet();
            dispatcherServlet.doDispatch(httpRequest, httpResponse);

            writeResponse(dos, httpResponse);
            logger.debug("\nResponse: {}", httpResponse);

        } catch (IOException | NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            logger.error(e.getMessage());
        }
    }

    private void writeResponse(DataOutputStream dos, HttpResponse httpResponse) {
        try {
            dos.writeBytes(httpResponse.getStatusLine().toString());
            dos.writeBytes(System.lineSeparator());

            dos.writeBytes(httpResponse.getHeader().toString());
            dos.writeBytes(System.lineSeparator());
            dos.writeBytes(System.lineSeparator());

            dos.write(httpResponse.getBody(), 0, httpResponse.getBody().length);

            dos.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
