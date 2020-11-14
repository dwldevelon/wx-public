package dwl;

import dwl.model.msg.TextMessage;
import dwl.utils.ParseUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author wenlong.ding
 * @date 2020/11/14 14:11
 */
@Slf4j
public class ParseUtilTest {
    @Test
    public void beanToXml(){
        log.info(TestConstant.XML);
        TextMessage textMessage = ParseUtil.xmlToBean(TestConstant.XML, TextMessage.class);
        log.info("bean:{}",textMessage);
        String xml = ParseUtil.beanToXml(textMessage);
        log.info("xml:{}", xml);

    }
}
