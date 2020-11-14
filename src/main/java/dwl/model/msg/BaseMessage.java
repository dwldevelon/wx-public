package dwl.model.msg;

import com.fasterxml.jackson.annotation.JsonProperty;
import dwl.constant.WxConstant;
import lombok.Data;

/**
 * @author wenlong.ding
 * @date 2020/10/22 20:46
 */
@Data
public class BaseMessage {

    @JsonProperty(WxConstant.ToUserName)
    private String toUserName;
    @JsonProperty(WxConstant.FromUserName)
    private String fromUserName;
    @JsonProperty(WxConstant.CreateTime)
    private long createTime;
    @JsonProperty(WxConstant.MsgType)
    private String msgType;
    @JsonProperty(WxConstant.MsgId)
    private long msgId;
    @JsonProperty(WxConstant.FuncFlag)
    private int funcFlag;

}
