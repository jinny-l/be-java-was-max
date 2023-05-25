package view;

import java.nio.file.Files;
import java.nio.file.Path;

public class ViewResolver {

    private static final String CLASS_PATH = "src/main/resources/";
    private static final String DEFAULT_PATH = "templates";
    private static final String SECOND_PATH = "static"; // TODO: 실제 명칭 확인 필요

    public static String getView(Path path) {
        if (hasFileInDefaultPathOf(path)) {
            return CLASS_PATH + DEFAULT_PATH + path;
        }
        return CLASS_PATH + SECOND_PATH + path;
    }

    private static boolean hasFileInDefaultPathOf(Path path) {
        return Files.exists(Path.of(CLASS_PATH + DEFAULT_PATH + path));
    }
}
