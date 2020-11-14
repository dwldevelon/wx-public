package dwl.model.jvhe;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 聚合基础返回接口
 * @author wenlong.ding
 * @date 2020/11/14 17:25
 */
@Data
public class BaseResp<T> {
    @JsonProperty("error_code")
    private String error_code;

//    @JsonProperty("reason")
    private String reason;

//    @JsonProperty("result")
    private T result;

}
