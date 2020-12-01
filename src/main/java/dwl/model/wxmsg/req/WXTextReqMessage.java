package dwl.model.wxmsg.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import dwl.config.constant.wx.WxConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author wenlong.ding
 * @date 2020/10/22 20:46
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class WXTextReqMessage extends WXBaseReq {

    @JsonProperty(WxConstant.MsgId)
    private long msgId;
    @JsonProperty(WxConstant.FuncFlag)
    private int funcFlag;
    @JsonProperty(WxConstant.Content)
    private String content;

}
