package dwl.controller;

import dwl.config.constant.CommonConstant;
import dwl.model.entity.UserInfoDto;
import dwl.model.enums.XiaoHuaFeatureEnum;
import dwl.config.plugins.BeanRepository;
import dwl.utils.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wenlong.ding
 * @date 2020/11/17 18:56
 */
@RestController
@RequestMapping("/mock")
@Slf4j
public class MockController extends BeanRepository {

    /**
     * 触发任务
     */
    @GetMapping("/trigger/task/xiaohua")
    public Object triggerXHTask(){
        xhTask.autoGetXiaoHua();
        return "success";
    }

    @GetMapping("/next")
    public Object next(){
        UserInfoDto userInfoDto = userInfoMapper.selectById(1);
        log.info("{}",userInfoDto);
        CommonConstant.GLOBAL_USER_INFO.set(userInfoDto);
        return
        SpringContextUtil.getBean(XiaoHuaFeatureEnum.NEXT.getCommand()).exec("1");
    }


    @GetMapping("/previous")
    public Object previous(){
        UserInfoDto userInfoDto = userInfoMapper.selectById(1);
        log.info("{}",userInfoDto);
        CommonConstant.GLOBAL_USER_INFO.set(userInfoDto);
        return
        SpringContextUtil.getBean(XiaoHuaFeatureEnum.PREVIOUS.getCommand()).exec("1");
    }



}
