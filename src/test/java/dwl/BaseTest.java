package dwl;

import dwl.plugins.BeanRepository;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wenlong.ding
 * @date 2020/11/16 9:21
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WxApplication.class)
public class BaseTest extends BeanRepository {
}
