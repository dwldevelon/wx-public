package dwl.model.jvhe;

import com.fasterxml.jackson.annotation.JsonProperty;
import dwl.model.enums.JvHeRespTypeEnum;
import dwl.utils.EnumUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

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
        JvHeRespTypeEnum jvHeRespTypeEnum = result();
        switch (jvHeRespTypeEnum) {
            case SUCCESS:
                return true;
            case LIMIT_EXCEEDED:
                return false;
            case UN_KNOW:
                log.warn("聚合数据请求结果异常:error_code={},reason={}", error_code, reason);
            default:
                return false;
        }
    }

    public JvHeRespTypeEnum result() {
        return EnumUtil.findOne(JvHeRespTypeEnum.class, JvHeRespTypeEnum::getErrorCode, error_code, JvHeRespTypeEnum.UN_KNOW);
    }

}
