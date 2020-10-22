package dwl.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wenlong.ding
 * @date 2020/10/16 17:21
 */
@RestController
@Slf4j
public class NotifyController {

    @GetMapping("/notify")
    public Object wxNotify( @RequestBody String requestBody,
                            @RequestParam("signature") String signature,
                            @RequestParam("timestamp") String timestamp,
                            @RequestParam("nonce") String nonce,
                            @RequestParam("openid") String openid,
                            @RequestParam(name = "encrypt_type", required = false) String encType,
                            @RequestParam(name = "msg_signature", required = false) String msgSignature,
                            @RequestParam String echostr){

        log.info("\n接收微信请求：[openid=[{}], [signature=[{}], encType=[{}], msgSignature=[{}],"
                        + " timestamp=[{}], nonce=[{}], requestBody=[\n{},echostr=[{}]\n] ",
                openid, signature, encType, msgSignature, timestamp, nonce, requestBody,echostr);

        return echostr;
    }
}
