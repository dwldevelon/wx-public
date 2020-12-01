package dwl.model.wxmsg.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import dwl.config.constant.wx.WxConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author wenlong.ding
 * @date 2020/11/14 15:34
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class WXTextRespMessage extends WXBaseResp {
    /*

    <xml>
  <ToUserName><![CDATA[toUser]]></ToUserName>
  <FromUserName><![CDATA[fromUser]]></FromUserName>
  <CreateTime>12345678</CreateTime>
  <MsgType><![CDATA[text]]></MsgType>
  <Content><![CDATA[你好]]></Content>
</xml>
     */
//    @JsonProperty(WxConstant.ToUserName)
//    private String toUserName;
//    @JsonProperty(WxConstant.FromUserName)
//    private String fromUserName;
//    @JsonProperty(WxConstant.CreateTime)
//    private long createTime;
//    @JsonProperty(WxConstant.MsgType)
//    private String msgType;
    @JsonProperty(WxConstant.Content)
    private String content;

}
