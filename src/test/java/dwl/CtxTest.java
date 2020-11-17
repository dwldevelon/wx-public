package dwl;

import dwl.model.entity.XiaoHuaDto;
import dwl.service.db.XiaoHuaService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author wenlong.ding
 * @date 2020/11/14 17:02
 */

@Slf4j
public class CtxTest extends BaseTest {
    @Resource
    XiaoHuaService xiaoHuaService;


    @Test
    public void userMapperTest(){
        XiaoHuaDto first = xiaoHuaService.findFirst();
        System.out.println(first);
        XiaoHuaDto last = xiaoHuaService.findLast();
        System.out.println(last);

    }

}
