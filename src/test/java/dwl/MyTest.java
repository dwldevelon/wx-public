package dwl;

import dwl.mapper.XiaoHuaMapper;
import dwl.model.entity.XiaoHuaDto;
import dwl.model.jvhe.XiaoHuaResp;
import dwl.service.business.JvHeService;
import dwl.service.business.XHService;
import dwl.service.business.impl.WxViewResolverImpl;
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
    @Resource
    WxViewResolverImpl wxViewResolver;

    @Resource
    XiaoHuaMapper xiaoHuaMapper;

    @Test
    public void userMapperTest(){
        XiaoHuaDto oneByRandom = xiaoHuaMapper.findOneByRandom();
        System.out.println(oneByRandom);
    }

    @Test
    public void test1(){
        XiaoHuaResp xiaoHuaResp = null;
        xiaoHuaService.save(xiaoHuaResp);
    }
}
