package dwl.model.msg.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import dwl.constant.WxConstant;
import lombok.Data;

/**
 * @author wenlong.ding
 * @date 2020/11/14 15:34
 */
@Data
public class TextRespMessage {
    /*

    <xml>
  <ToUserName><![CDATA[toUser]]></ToUserName>
  <FromUserName><![CDATA[fromUser]]></FromUserName>
  <CreateTime>12345678</CreateTime>
  <MsgType><![CDATA[text]]></MsgType>
  <Content><![CDATA[你好]]></Content>
</xml>
     */
    @JsonProperty(WxConstant.ToUserName)
    private String toUserName;
    @JsonProperty(WxConstant.FromUserName)
    private String fromUserName;
    @JsonProperty(WxConstant.CreateTime)
    private long createTime;
    @JsonProperty(WxConstant.MsgType)
    private String msgType;
    @JsonProperty(WxConstant.Content)
    private String content;

}
