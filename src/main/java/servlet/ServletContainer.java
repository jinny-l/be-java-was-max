package servlet;

import annotation.RequestMapping;
import com.google.common.reflect.ClassPath;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ServletContainer {

    private static final Map<String, Class<? extends HttpServlet>> SERVLETS = loadServlets();;
    private static final String PACKAGE_NAME = "servlet.*";

    public static Class<? extends HttpServlet> getServlet(String url) {
        return SERVLETS.getOrDefault(url, HttpServlet.class);
    }

    // TODO: Http Method 가 다른데 똑같은 URL이면 스트림에서 충돌이 일어나서 못 읽어오는데, 읽어오게끔 수정 필요
    private static Map<String, Class<? extends HttpServlet>> loadServlets() {
        try {
             return findServlets().stream()
                    .flatMap(clazz -> Arrays.stream(clazz.getDeclaredMethods()))
                    .filter(method -> method.isAnnotationPresent(RequestMapping.class))
                    .collect(Collectors.toUnmodifiableMap(
                            method -> method.getAnnotation(RequestMapping.class).url(),
                            method -> method.getDeclaringClass().asSubclass(HttpServlet.class)
                    ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Set<Class<? extends HttpServlet>> findServlets() throws IOException {
        Set<Class<? extends HttpServlet>> collect = ClassPath.from(ClassLoader.getSystemClassLoader())
                .getAllClasses()
                .stream()
                .filter(classInfo -> classInfo.getPackageName().matches(PACKAGE_NAME))
                .map(classInfo -> (Class<? extends HttpServlet>) classInfo.load())
                .collect(Collectors.toUnmodifiableSet());
        System.out.println(collect);
        return collect;
    }
}
