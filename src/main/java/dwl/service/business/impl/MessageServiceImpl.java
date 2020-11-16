package dwl.service.business.impl;

import dwl.model.jvhe.XiaoHuaResp;
import dwl.model.wxmsg.req.TextMessage;
import dwl.model.wxmsg.resp.TextRespMessage;
import dwl.plugins.BeanRepository;
import dwl.service.business.MessageService;
import dwl.utils.ParseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author wenlong.ding
 * @date 2020/10/22 20:48
 */
@Slf4j
@Service
public class MessageServiceImpl extends BeanRepository implements MessageService {

    @Override
    public String newMessageRequest(String requestBody) {
        try {
            TextMessage textMessage = ParseUtil.xmlToBean(requestBody, TextMessage.class);
            log.info("接收到消息:{}",textMessage.getContent());

            XiaoHuaResp xiaoHua = jvHeService.getXiaoHua();
            xhService.save(xiaoHua);

            XiaoHuaResp.Data data = xiaoHua.getData().get(0);

            TextRespMessage respMessage = new TextRespMessage();
            respMessage.setContent(data.getContent());
            respMessage.setToUserName(textMessage.getFromUserName());
            respMessage.setFromUserName(textMessage.getToUserName());
            respMessage.setCreateTime(new Date().getTime());
            respMessage.setMsgType(textMessage.getMsgType());

            return ParseUtil.beanToXml(respMessage);
        } catch (Exception e) {
            log.error("消息处理异常.",e);
        }
        return "";
    }
}
