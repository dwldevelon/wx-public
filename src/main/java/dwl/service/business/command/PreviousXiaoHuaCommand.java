package dwl.service.business.command;

import dwl.config.constant.CommonConstant;
import dwl.model.entity.UserInfoDto;
import dwl.model.entity.XiaoHuaDto;
import dwl.model.enums.XiaoHuaFeatureEnum;
import dwl.config.plugins.BeanRepository;
import dwl.service.business.Command;
import dwl.utils.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author wenlong.ding
 * @date 2020/11/25 15:42
 */
@Slf4j
@Service
public class PreviousXiaoHuaCommand extends BeanRepository implements Command {
    @Override
    public String exec(String content) {
        UserInfoDto userInfoDto = CommonConstant.GLOBAL_USER_INFO.get();
        if(Objects.isNull(userInfoDto)){
            return SpringContextUtil.getBean(XiaoHuaFeatureEnum.RANDOM.getCommand()).exec(content);
        }
        XiaoHuaDto next = xiaoHuaService.findPrevious(userInfoDto.getCurrXiaoHuaId());
        userInfoDto.setCurrXiaoHuaId(next.getId());
        userInfoService.updateById(userInfoDto);
        return headAndTailWrapper.wrapper(userInfoDto.getActiveFeatureCode(),next.getContent());
    }
}
