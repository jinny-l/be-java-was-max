package view;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ViewResolver {

    private static final String CLASS_PATH = "src/main/resources/";
    private static final String DEFAULT_PATH = CLASS_PATH + "templates";
    private static final String SECOND_PATH = CLASS_PATH + "static"; // TODO: 실제 명칭 확인 필요

    public static String getViewFrom(Path path) {
        if (hasFileInDefaultPathOf(path)) {
            return DEFAULT_PATH + path;
        }
        return Files.readAllBytes(Path.of(SECOND_PATH + view));
    }

    private static boolean hasFileInDefaultPathOf(String view) {
        return Files.exists(Path.of(DEFAULT_PATH + view));
    }
}
