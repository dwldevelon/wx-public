package dwl.service.business.command;

import dwl.config.plugins.BeanRepository;
import dwl.model.WXContext;
import dwl.model.enums.XiaoHuaFeatureEnum;
import dwl.model.wxmsg.req.WXTextReqMessage;
import dwl.service.business.WXCommand;
import dwl.utils.EnumUtil;
import dwl.utils.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author wenlong.ding
 * @date 2020/11/17 12:25
 */
@Service
@Slf4j
public class XiaoHuaCommand extends BeanRepository implements WXCommand {


    @Override
    public void exec(WXContext context) {
        WXTextReqMessage wxTextReqMessage = context.checkReq(WXTextReqMessage.class);
        Integer code = null;
        try{
            code = Integer.valueOf(wxTextReqMessage.getContent());
        }catch (Exception e){
            log.warn("code解析失败，使用默认值");
        }
        if(Objects.isNull(code)){
            code = XiaoHuaFeatureEnum.RANDOM.getCode();
        }
        XiaoHuaFeatureEnum xhFeature = EnumUtil.findOne(XiaoHuaFeatureEnum.class,code,XiaoHuaFeatureEnum.RANDOM);
        SpringContextUtil.getBean(xhFeature.getCommand()).exec(context);
    }
}
