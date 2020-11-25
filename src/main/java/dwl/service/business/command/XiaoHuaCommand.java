package dwl.service.business.command;

import dwl.model.enums.XiaoHuaFeatureEnum;
import dwl.plugins.BeanRepository;
import dwl.service.business.Command;
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
public class XiaoHuaCommand extends BeanRepository implements Command {
    @Override
    public String exec(String content) {
        Integer code = null;
        try{
            code = Integer.valueOf(content);
        }catch (Exception e){
            log.warn("code解析失败，使用默认值");
        }
        if(Objects.isNull(code)){
            code = XiaoHuaFeatureEnum.RANDOM.getCode();
        }
        XiaoHuaFeatureEnum xhFeature = EnumUtil.findOne(XiaoHuaFeatureEnum.class,code);
        return SpringContextUtil.getBean(xhFeature.getCommand()).exec(content);
    }

}
