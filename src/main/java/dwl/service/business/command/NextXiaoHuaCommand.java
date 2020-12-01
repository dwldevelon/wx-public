package dwl.service.business.command;

import dwl.config.constant.CommonConstant;
import dwl.config.plugins.BeanRepository;
import dwl.model.WXContext;
import dwl.model.entity.UserInfoDto;
import dwl.model.entity.XiaoHuaDto;
import dwl.model.enums.XiaoHuaFeatureEnum;
import dwl.model.wxmsg.resp.WXTextRespMessage;
import dwl.service.business.WXCommand;
import dwl.utils.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author wenlong.ding
 * @date 2020/11/25 15:43
 */
@Service
@Slf4j
public class NextXiaoHuaCommand extends BeanRepository implements WXCommand {

    @Override
    public void exec(WXContext context) {
        //        WXTextReqMessage wxTextReqMessage = CommonUtil.checkReq(wxBaseReq, WXTextReqMessage.class);

        UserInfoDto userInfoDto = CommonConstant.GLOBAL_USER_INFO.get();
        if(Objects.isNull(userInfoDto)){
            SpringContextUtil.getBean(XiaoHuaFeatureEnum.RANDOM.getCommand()).exec(context);
        }
        XiaoHuaDto next = xiaoHuaService.findNext(userInfoDto.getCurrXiaoHuaId());
        userInfoDto.setCurrXiaoHuaId(next.getId());
        userInfoService.updateById(userInfoDto);
        context.castOrCrate(WXTextRespMessage.class).setContent(headAndTailWrapper.wrapper(userInfoDto.getActiveFeatureCode(),next.getContent()));
    }
}
