package dwl.service.impl;

import dwl.model.wxmsg.req.TextMessage;
import dwl.model.wxmsg.resp.TextRespMessage;
import dwl.service.MessageService;
import dwl.utils.ParseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author wenlong.ding
 * @date 2020/10/22 20:48
 */
@Slf4j
@Service
public class MessageServiceImpl implements MessageService {

    @Override
    public String newMessageRequest(String requestBody) {
        try {
            TextMessage textMessage = ParseUtil.xmlToBean(requestBody, TextMessage.class);
            log.info("接收到消息:{}",textMessage.getContent());
            TextRespMessage respMessage = new TextRespMessage();
            respMessage.setContent("遇见你真好，显示是："+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
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
