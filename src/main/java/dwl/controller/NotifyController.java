package dwl.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wenlong.ding
 * @date 2020/10/16 17:21
 */
@RestController
@Slf4j
public class NotifyController {

    @GetMapping("/notify")
    public Object wxNotify(String echostr){
        log.info("echostr:{}",echostr);
        return echostr;
    }
}
