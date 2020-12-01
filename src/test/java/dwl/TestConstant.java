package dwl;

import dwl.config.constant.wx.WxMsgTypeConstant;
import dwl.model.wxmsg.req.WXTextReqMessage;
import dwl.utils.ParseUtil;

import java.util.Date;
import java.util.function.Consumer;

/**
 * @author wenlong.ding
 * @date 2020/11/14 11:21
 */
public class TestConstant {

    public static String XML = "<xml>\n" +
            "  <ToUserName><![CDATA[toUser]]></ToUserName>\n" +
            "  <FromUserName><![CDATA[fromUser]]></FromUserName>\n" +
            "  <CreateTime>1348831860</CreateTime>\n" +
            "  <MsgType><![CDATA[text]]></MsgType>\n" +
            "  <Content><![CDATA[this is a test]]></Content>\n" +
            "  <MsgId>1234567890123456</MsgId>\n" +
            "</xml>";


    public static String getWxReq(Consumer<WXTextReqMessage> consumer) {
        WXTextReqMessage req = new WXTextReqMessage();

        req.setMsgId(9527L);
        req.setFuncFlag(0);
        req.setToUserName("ding");
        req.setFromUserName("oClUZ1FGJB43M_rZSc48WCpCj2-Q");
        req.setCreateTime(new Date().getTime());
        req.setMsgType(WxMsgTypeConstant.TEXT);
        req.setContent("");
        consumer.accept(req);
        return ParseUtil.beanToXml(req);
    }

}
