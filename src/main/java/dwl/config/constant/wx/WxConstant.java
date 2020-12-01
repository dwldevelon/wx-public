package dwl.config.constant.wx;

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
    /**
     * xml外层包装
     */
    String XML_ROOT = "xml";
    /**
     * 微信默认返回空字符串，不向用户发送任何请求
     */
    String DEFAULT_RETURN = "";
    /**
     * 图文消息个数；当用户发送文本、图片、语音、视频、图文、地理位置这六种消息时，开发者只能回复1条图文消息；其余场景最多可回复8条图文消息
     */
    String ARTICLE_COUNT = "ArticleCount";
    /**
     * 图文消息标题
     */
    String TITLE = "Title";
    /**
     * 图文消息描述
     */
    String DESCRIPTION = "Description";
    /**
     * 	图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
     */
    String PIC_URL = "PicUrl";
    /**
     * 点击图文消息跳转链接
     */
    String URL = "Url";

//    String DEFAULT_RESP_CONTENT = "系统识别不了你的请求，请多喝热水！";

}
