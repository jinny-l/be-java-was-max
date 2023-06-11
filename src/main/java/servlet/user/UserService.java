package servlet.user;

import db.Database;
import java.util.Map;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    // TODO: 회원 중복 확인 로직 추가 필요
    public void join(Map<String, String> params) {
        User user = new User(params.get("userId"), params.get("password"), params.get("name"), params.get("email"));
        Database.addUser(user);
        logger.info("user: {}", user);
    }

}
