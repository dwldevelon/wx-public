package dwl.controller;

import dwl.plugins.BeanRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author wenlong.ding
 * @date 2020/10/16 17:21
 */
@RestController
@Slf4j
public class NotifyController extends BeanRepository {


    @GetMapping("/notify")
    public Object wxNotify( @RequestBody(required = false) String requestBody,
                            @RequestParam(value = "signature",required = false) String signature,
                            @RequestParam(value = "timestamp",required = false) String timestamp,
                            @RequestParam(value = "nonce",required = false) String nonce,
                            @RequestParam(value = "openid",required = false) String openid,
                            @RequestParam(name = "encrypt_type", required = false) String encType,
                            @RequestParam(name = "msg_signature", required = false) String msgSignature,
                            @RequestParam(value = "echostr",required = false) String echostr){

        log.info("\n接收微信请求：[openid=[{}], [signature=[{}], encType=[{}], msgSignature=[{}],"
                        + " timestamp=[{}], nonce=[{}], requestBody=[\n{},echostr=[{}]\n] ",
                openid, signature, encType, msgSignature, timestamp, nonce, requestBody,echostr);

        return echostr;
    }
    @PostMapping("/notify")
    public Object wxPostNotify( @RequestBody(required = false) String requestBody){
        return msgGateway.handle(requestBody);
    }
}
