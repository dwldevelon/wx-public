package dwl.service.business.command;

import dwl.config.constant.CommonConstant;
import dwl.config.plugins.BeanRepository;
import dwl.model.WXContext;
import dwl.model.entity.ProcessTreeDto;
import dwl.model.entity.UserInfoDto;
import dwl.model.wxmsg.req.WXTextReqMessage;
import dwl.model.wxmsg.resp.WXTextRespMessage;
import dwl.service.business.WXCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author wenlong.ding
 * @date 2020/11/17 11:31
 */
@Service
@Slf4j
public class CdCommand extends BeanRepository implements WXCommand {

    @Override
    public void exec(WXContext context) {
        Assert.notNull(context.getReq(),"请求消息不能为null");
        WXTextReqMessage wxTextReqMessage = context.checkReq(WXTextReqMessage.class);
        String content = wxTextReqMessage.getContent();
        content = content.substring(2).trim();
        int code =  CommonConstant.ROOT_PROCESS_TREE_CODE; ;
        try {
            code = Integer.parseInt(content);
        }catch (Exception e){
            log.warn("无法解析的cd命令参数:"+content);
        }
        ProcessTreeDto ptTree = processTreeService.findByCode(code);
        if(Objects.isNull(ptTree)){
            code = CommonConstant.ROOT_PROCESS_TREE_CODE;
            ptTree = processTreeService.findByCode(code);
        }

        UserInfoDto userInfoDto = CommonConstant.GLOBAL_USER_INFO.get();
        if(Objects.nonNull(userInfoDto)) {
            userInfoDto.setActiveFeatureCode(ptTree.getFeatureCode());
            userInfoMapper.updateById(userInfoDto);
        }

        String result = processTreeService.findByParentId(ptTree.getId())
                .stream()
                .map(e->String.format("-%s[%s]",e.getCode(),e.getName()))
                .collect(Collectors.joining(CommonConstant.RN));

        String wrapper = headAndTailWrapper.wrapper(code, result);
        context.castOrCrate(WXTextRespMessage.class).setContent(wrapper);
    }
}
