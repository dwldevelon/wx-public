package dwl.service.business;

/**
 * @author wenlong.ding
 * @date 2020/10/22 20:47
 */
@Deprecated
public interface MessageService {
    /**
     * 微信公众号处理
     * @param request
     * @return
     */
    String newMessageRequest(String request);

}
