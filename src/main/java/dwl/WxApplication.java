package dwl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author wenlong.ding
 * @date 2020/10/16 17:17
 */
@SpringBootApplication
@EnableAspectJAutoProxy
@MapperScan("dwl.mapper")
@EnableScheduling
public class WxApplication {
    public static void main(String[] args) {
        SpringApplication.run(WxApplication.class,args);
    }
}