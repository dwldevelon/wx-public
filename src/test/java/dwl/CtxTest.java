package dwl;

import dwl.model.entity.UserInfoDto;
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
        UserInfoDto userInfoDto = userInfoMapper.selectById(1L);
        log.info("{}", userInfoDto);
        userInfoDto.setActiveFeatureCode(null);
        int i = userInfoMapper.updateById(userInfoDto);
        log.info("{}", i);
    }

}
