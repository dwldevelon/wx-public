package dwl;

import dwl.model.jvhe.XiaoHuaResp;
import dwl.service.business.JvHeService;
import dwl.service.business.XHService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author wenlong.ding
 * @date 2020/11/14 17:02
 */

@Slf4j
public class MyTest extends BaseTest {
    @Resource
    JvHeService jvHeService;
    @Resource
    XHService xiaoHuaService;
    @Test
    public void userMapperTest(){
        XiaoHuaResp xiaoHua = jvHeService.getXiaoHua();
        log.info("xiaohua:{}", xiaoHua);
    }

    @Test
    public void test1(){
        XiaoHuaResp xiaoHuaResp = null;
        xiaoHuaService.save(xiaoHuaResp);
    }
}
