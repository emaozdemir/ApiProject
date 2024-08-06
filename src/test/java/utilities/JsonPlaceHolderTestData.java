package utilities;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {
    public static Map<String,Object> jsonPlaceHolderMapper(Integer userId, String title,Boolean completed ){
        Map<String, Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("title",title);
        map.put("completed",completed);
        return map;
    }
}
