package Session;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class SessionIdManager {

    private static final Map<String, String> SESSION_STORAGE = new ConcurrentHashMap<>();

    public static String createSession() {
        String sessionId = UUID.randomUUID().toString();
        SESSION_STORAGE.put(sessionId, sessionId);
        return sessionId;
    }
}
