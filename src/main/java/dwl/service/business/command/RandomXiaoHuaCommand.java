package dwl.service.business.command;

import dwl.config.constant.CommonConstant;
import dwl.config.plugins.BeanRepository;
import dwl.model.WXContext;
import dwl.model.entity.UserInfoDto;
import dwl.model.entity.XiaoHuaDto;
import dwl.model.wxmsg.resp.WXTextRespMessage;
import dwl.service.business.WXCommand;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author wenlong.ding
 * @date 2020/11/25 15:38
 */
@Service
public class RandomXiaoHuaCommand extends BeanRepository implements WXCommand {

    @Override
    public void exec(WXContext context) {

        XiaoHuaDto xiaoHuaDto = xiaoHuaMapper.findOneByRandom();

        UserInfoDto userInfoDto = CommonConstant.GLOBAL_USER_INFO.get();

        context.castOrCrate(WXTextRespMessage.class).setContent(
                Objects.isNull(userInfoDto) ? xiaoHuaDto.getContent() :
                        headAndTailWrapper.wrapper(userInfoDto.getActiveFeatureCode(), xiaoHuaDto.getContent())
        );
    }
}
