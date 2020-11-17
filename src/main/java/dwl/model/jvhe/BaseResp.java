package dwl.model.jvhe;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * 聚合基础返回接口
 * @author wenlong.ding
 * @date 2020/11/14 17:25
 */
@Data
@Slf4j
public class BaseResp<T> {
    @JsonProperty("error_code")
    private String error_code;

//    @JsonProperty("reason")
    private String reason;

//    @JsonProperty("result")
    private T result;

    @JsonProperty("resultcode")
    private String resultCode;

    public boolean success(){
        boolean success = Objects.equals(error_code,"0");
        if(!success){
            log.warn("笑话请求结果异常:error_code={},reason={}",error_code,reason);
        }
        return success;
    }

}
