package dwl.model.wxmsg.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import dwl.config.constant.wx.WxConstant;
import lombok.Data;

import java.util.List;

/**
 * @author wenlong.ding
 * @date 2020/11/30 14:07
 */
@Data
public class WXNewsRespMessage extends WXBaseResp {
    /**

     <ToUserName><![CDATA[toUser]]></ToUserName>
     <FromUserName><![CDATA[fromUser]]></FromUserName>
     <CreateTime>12345678</CreateTime>
     <MsgType><![CDATA[news]]></MsgType>
     <ArticleCount>1</ArticleCount>
     <Articles>
     <item>
     <Title><![CDATA[title1]]></Title>
     <Description><![CDATA[description1]]></Description>
     <PicUrl><![CDATA[picurl]]></PicUrl>
     <Url><![CDATA[url]]></Url>
     </item>
     </Articles>

     */

//    @JsonProperty(WxConstant.ToUserName)
//    private String toUserName;
//    @JsonProperty(WxConstant.FromUserName)
//    private String fromUserName;
//    @JsonProperty(WxConstant.CreateTime)
//    private long createTime;
//    @JsonProperty(WxConstant.MsgType)
//    private String msgType;

    @JsonProperty(WxConstant.ARTICLE_COUNT)
    private String articleCount;

    private Articles articles;

    @Data
    public static class Articles{
        private List<Item> item;
    }

    @Data
    public static class Item{
        @JsonProperty(WxConstant.TITLE)
        private String title;
        @JsonProperty(WxConstant.DESCRIPTION)
        private String Description;
        @JsonProperty(WxConstant.PIC_URL)
        private String PicUrl;
        @JsonProperty(WxConstant.URL)
        private String url;
    }

}
