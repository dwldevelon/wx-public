package dwl.service.business;

/**
 * @author wenlong.ding
 * @date 2020/11/16 15:15
 */
public interface MsgGateway {

    Object handle(String requestBody);

}
