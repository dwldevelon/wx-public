package dwl.service.impl;

import dwl.model.msg.TextMessage;
import dwl.service.MessageService;
import dwl.utils.ParseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        log.info("receive message : {}",requestBody);
        try {
            TextMessage textMessage = ParseUtil.xmlToBean(requestBody, TextMessage.class);
            log.info("receive message : {}",textMessage);
            // 收到
            TextMessage respMessage = new TextMessage();
            respMessage.setContent("遇见你真好，显示是："+ LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
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
