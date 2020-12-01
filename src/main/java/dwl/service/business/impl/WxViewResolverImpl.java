package dwl.service.business.impl;

import dwl.config.plugins.BeanRepository;
import dwl.service.business.WxViewResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author wenlong.ding
 * @date 2020/11/17 9:35
 */
@Service
@Slf4j
public class WxViewResolverImpl extends BeanRepository implements WxViewResolver {
    @Override
    public String resolve(String content) {
        return null;
    }

   /* @Override
    public String resolve(String content) {
        OperateEnum operateEnum = OperateEnum.findByContent(content);
        if(Objects.nonNull(operateEnum)){
            return ctx.getBean(operateEnum.getExecImpl()).exec(content);
        }
        UserInfoDto userInfoDto = CommonConstant.GLOBAL_USER_INFO.get();
        if(Objects.isNull(userInfoDto)){
            log.warn("用户不存在");
            return resolve(OperateEnum.DEFAULT_OPERATE);
        }
        Integer activeFeature = userInfoDto.getActiveFeatureCode();
        FeatureEnum featureEnum = EnumUtil.findOne(FeatureEnum.class, activeFeature);

        if(Objects.nonNull(featureEnum)){
            return ctx.getBean(featureEnum.getClazz()).exec(content);
        }
        log.warn("不能识别的content:{}",content);
        return resolve(OperateEnum.DEFAULT_OPERATE);
    }*/

}
