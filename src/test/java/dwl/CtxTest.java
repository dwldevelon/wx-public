package dwl;

import dwl.config.constant.CommonConstant;
import dwl.model.entity.UserInfoDto;
import dwl.model.enums.XiaoHuaFeatureEnum;
import dwl.utils.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author wenlong.ding
 * @date 2020/11/14 17:02
 */

@Slf4j
public class CtxTest extends BaseTest {


    @Test
    public void userMapperTest(){
        UserInfoDto userInfoDto = userInfoMapper.selectById(1);
        log.info("{}",userInfoDto);
        CommonConstant.GLOBAL_USER_INFO.set(userInfoDto);

        String exec = SpringContextUtil.getBean(XiaoHuaFeatureEnum.PREVIOUS.getCommand()).exec("1");
        System.out.println(exec);
    }

}
