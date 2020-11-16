package dwl.service.business.impl;

import dwl.constant.WxConstant;
import dwl.model.wxmsg.req.TextMessage;
import dwl.plugins.BeanRepository;
import dwl.service.business.MsgGateway;
import dwl.utils.ParseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author wenlong.ding
 * @date 2020/11/16 15:18
 */
@Service
@Slf4j
public class MsgGatewayImpl extends BeanRepository implements MsgGateway {

    @Override
    public Object handle(String requestBody) {
        Map<String,String> requestMap = ParseUtil.xmlToMap(requestBody);
        String msgType = requestMap.get(WxConstant.MsgType);
        if(StringUtils.isEmpty(msgType)){
            log.warn("msgType为空");
            return "";
        }
        switch (msgType){
            case "text":
                TextMessage textMessage = ParseUtil.mapToBean(requestMap, TextMessage.class);
            default:
                log.warn("未识别的消息类型:msgType={}",msgType);
                return "";
        }
    }
}
