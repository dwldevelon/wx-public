package dwl.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wenlong.ding
 * @date 2020/10/16 17:21
 */
@RestController
public class NotifyController {

    @GetMapping("/notify")
    public Object wxNotify(String echostr){
        return echostr;
    }
}
