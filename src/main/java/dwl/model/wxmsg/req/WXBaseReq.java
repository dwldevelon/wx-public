package dwl.model.wxmsg.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import dwl.config.constant.wx.WxConstant;
import lombok.Data;

/**
 * @author wenlong.ding
 * @date 2020/11/30 17:18
 */
@Data
public class WXBaseReq {
    @JsonProperty(WxConstant.ToUserName)
    private String toUserName;
    @JsonProperty(WxConstant.FromUserName)
    private String fromUserName;
    @JsonProperty(WxConstant.CreateTime)
    private long createTime;
    @JsonProperty(WxConstant.MsgType)
    private String msgType;

}
