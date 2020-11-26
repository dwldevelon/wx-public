package dwl.service.business.command;

import dwl.config.constant.CommonConstant;
import dwl.model.entity.UserInfoDto;
import dwl.model.entity.XiaoHuaDto;
import dwl.config.plugins.BeanRepository;
import dwl.service.business.Command;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author wenlong.ding
 * @date 2020/11/25 15:38
 */
@Service
public class RandomXiaoHuaCommand extends BeanRepository implements Command {
    @Override
    public String exec(String content) {

        XiaoHuaDto xiaoHuaDto = xiaoHuaMapper.findOneByRandom();

        UserInfoDto userInfoDto = CommonConstant.GLOBAL_USER_INFO.get();
        if(Objects.isNull(userInfoDto)){
            return xiaoHuaDto.getContent();
        }else {
            // 随机笑话不会重置游标
//            userInfoDto.setCurrXiaoHuaId(xiaoHuaDto.getId());
//            userInfoService.updateById(userInfoDto);
            return headAndTailWrapper.wrapper(userInfoDto.getActiveFeatureCode(),xiaoHuaDto.getContent());
        }
    }
}
