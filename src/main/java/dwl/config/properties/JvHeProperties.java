package dwl.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 聚合科技接口配置参数类
 * @author wenlong.ding
 * @date 2020/11/14 17:15
 */
@ConfigurationProperties(prefix = "jh")
@Component
@Data
@PropertySource("classpath:/jh.properties")
public class JvHeProperties {
    private String openId;

    private String url;

    private String xhPath;
    /**
     * 笑话大全key
     */
    private String xhKey;
}
