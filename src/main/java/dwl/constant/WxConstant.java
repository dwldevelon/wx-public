package dwl.constant;

/**
 * @author wenlong.ding
 * @date 2020/11/14 11:33
 */
public interface WxConstant {
    /**
     * 消息id，64位整型
     */
    String  MsgId = "MsgId";
    /**
     * 发送方帐号（一个OpenID）
     */
    String  FromUserName = "FromUserName";
    /**
     * 消息创建时间 （整型）
     */
    String  CreateTime = "CreateTime";
    /**
     * 开发者微信号
     */
    String  ToUserName = "ToUserName";
    /**
     * 消息类型（text/image/location/link）
     */
    String  MsgType = "MsgType";
    /**
     * 位0x0001被标志时，星标刚收到的消息
     */
    String FuncFlag = "FuncFlag";
    /**
     * 消息内容 xml数据
     */
    String Content = "Content";

    String XML_ROOT = "xml";

}
