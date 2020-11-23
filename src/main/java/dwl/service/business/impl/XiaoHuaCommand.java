package dwl.service.business.impl;

import dwl.constant.CommonConstant;
import dwl.model.entity.UserInfoDto;
import dwl.model.entity.XiaoHuaDto;
import dwl.plugins.BeanRepository;
import dwl.service.business.Command;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author wenlong.ding
 * @date 2020/11/17 12:25
 */
@Service
public class XiaoHuaCommand extends BeanRepository implements Command {
    @Override
    public String exec(String content) {
        // 暂时先随便返回一条数据。
        XiaoHuaDto xiaoHuaDto = xiaoHuaMapper.findOneByRandom();

        UserInfoDto userInfoDto = CommonConstant.GLOBAL_USER_INFO.get();
        if(Objects.isNull(userInfoDto)){
            return xiaoHuaDto.getContent();
        }else {
            return headAndTailWrapper.wrapper(userInfoDto.getActiveFeatureCode(),xiaoHuaDto.getContent());
        }
    }
}
