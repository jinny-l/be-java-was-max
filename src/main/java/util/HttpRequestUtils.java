package util;

import java.util.HashMap;
import java.util.Map;

public class HttpRequestUtils {

    private static final int PARAMETERS_INDEX = 1;
    private static final int KEY_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    public static Map<String, String> parseQueryParams(String url) {
        Map<String, String> params = new HashMap<>();

        String queryString = url.split("\\?")[PARAMETERS_INDEX];
        String[] tokens = queryString.split("&");

        for (String token : tokens) {
            String[] param = token.split("=");
            params.put(param[KEY_INDEX], param[VALUE_INDEX]);
        }
        return params;
    }
}
