package dwl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author wenlong.ding
 * @date 2020/10/16 17:17
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class WxAppliction {
    public static void main(String[] args) {
        SpringApplication.run(WxAppliction.class,args);
    }
}
