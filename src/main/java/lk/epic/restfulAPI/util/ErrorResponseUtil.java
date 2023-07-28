package lk.epic.restfulAPI.util;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class ErrorResponseUtil {
    private static ErrorResponseUtil errorResponseUtil;
    private final Map<String, String> userErrorHashMap;

    private ErrorResponseUtil() {
        userErrorHashMap = new HashMap<>();
        userErrorHashMap.put("04", "User Already Exists");
        userErrorHashMap.put("06", "Bad Request");
        userErrorHashMap.put("02", "No such user exists!");
        userErrorHashMap.put("03", "Invalid Credentials");
    }

    public static ErrorResponseUtil getInstance() {
        return errorResponseUtil == null ? errorResponseUtil = new ErrorResponseUtil() : errorResponseUtil;
    }

    public Map<String, String> getErrorHashMap() {
        return userErrorHashMap;
    }
}
