package dwl.model.jvhe;

import dwl.utils.ParseUtil;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wenlong.ding
 * @date 2020/11/26 15:08
 */
@Data
public class NewsReq {
    private String type;
    private String key;

    public Map<String,String> toMap(){
        Map<String,String> map = new HashMap<>();
        ParseUtil.beanToMap(this).forEach((k, v)->map.put(k,String.valueOf(v)));
        return map;
    }
}
