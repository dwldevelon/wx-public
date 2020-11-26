package dwl.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 微信相关配置文件
 * @author wenlong.ding
 * @date 2020/11/23 10:17
 */
@ConfigurationProperties(prefix = "wx")
@Component("myWx")
@Data
@PropertySource("classpath:/wx.properties")
public class WxProperties {
    private String appSecret;
    private String appId;
    private String token;
    private String encodingAESKey;
}
