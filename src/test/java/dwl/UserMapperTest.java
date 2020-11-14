package dwl;

import dwl.model.jvhe.XiaoHuaResp;
import dwl.service.JvHeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author wenlong.ding
 * @date 2020/11/14 17:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WxAppliction.class)
@Slf4j
public class UserMapperTest {
    @Resource
    JvHeService jvHeService;
    @Test
    public void userMapperTest(){
        XiaoHuaResp xiaoHua = jvHeService.getXiaoHua();
        log.info("xiaohua:{}", xiaoHua);
    }
}
