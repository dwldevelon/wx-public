package dwl.service.business.impl;

import dwl.constant.CommonConstant;
import dwl.model.entity.UserInfoDto;
import dwl.model.enums.FeatureEnum;
import dwl.model.enums.OperateEnum;
import dwl.plugins.BeanRepository;
import dwl.service.business.WxViewResolver;
import dwl.utils.EnumUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author wenlong.ding
 * @date 2020/11/17 9:35
 */
@Service
@Slf4j
public class WxViewResolverImpl extends BeanRepository implements WxViewResolver {

    @Override
    public String resolve(String content) {
        OperateEnum operateEnum = OperateEnum.findByContent(content);
        if(Objects.nonNull(operateEnum)){
            return ctx.getBean(operateEnum.getExecImpl()).exec(content);
        }
        UserInfoDto userInfoDto = CommonConstant.GLOBAL_USER_INFO.get();
        if(Objects.isNull(userInfoDto)){
            log.error("用户不存在");
            return resolve("cd 0");
        }
        int activeFeature = userInfoDto.getActiveFeatureCode();
        FeatureEnum featureEnum = EnumUtil.findOne(FeatureEnum.class, activeFeature);

        if(Objects.nonNull(featureEnum)){
            return ctx.getBean(featureEnum.getClazz()).exec(content);
        }
        log.warn("不能识别的content:{}",content);
        return resolve("cd 0");
    }

}
