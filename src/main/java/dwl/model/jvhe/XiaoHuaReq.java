package dwl.model.jvhe;

import com.fasterxml.jackson.annotation.JsonProperty;
import dwl.properties.JvHeProperties;
import dwl.utils.ParseUtil;
import dwl.utils.SpringContextUtil;
import lombok.Data;

import java.util.Map;

/**
 * @author wenlong.ding
 * @date 2020/11/16 13:40
 */
@Data
public class XiaoHuaReq {


    private String sort = "desc";

    private int page = 1;

    @JsonProperty("pagesize")
    private int pageSize = 20;
    /**
     * 时间，10位时间戳
     */
    private String time;

    private String key;

    public static XiaoHuaReq defaultReq(){
        XiaoHuaReq req = new XiaoHuaReq();
        req.setSort("desc");
        req.setPage(1);
        req.setPageSize(20);
        req.setTime(String.valueOf(System.currentTimeMillis()/1000));
        req.setKey(SpringContextUtil.getBean(JvHeProperties.class).getXhKey());
        return req;
    }

    public Map<String,String> toMap(){
        return ParseUtil.beanToMap(this);
    }
}
