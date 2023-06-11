package servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import model.http.request.HttpRequest;
import model.http.response.HttpResponse;

public class DispatcherServlet {

    public void doDispatch(HttpRequest request, HttpResponse response)
            throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String path = Path.of(request.getUrl()).toString();

        HttpServlet servlet = ServletContainer.getServlet(path).getDeclaredConstructor().newInstance();
        servlet.service(request, response);
    }

}
