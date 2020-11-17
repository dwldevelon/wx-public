package dwl.service.business.impl;

import dwl.constant.CommonConstant;
import dwl.model.entity.ProcessTreeDto;
import dwl.model.entity.UserInfoDto;
import dwl.model.enums.OperateEnum;
import dwl.plugins.BeanRepository;
import dwl.service.business.Command;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author wenlong.ding
 * @date 2020/11/17 11:31
 */
@Service
public class CdCommand extends BeanRepository implements Command {

    @Override
    public String exec(String content) {
        Assert.notNull(content,"cd命名内容不能为null");
        Assert.isTrue(content.startsWith(OperateEnum.CD.getCmd()),"命令不对");
        content = content.substring(2).trim();
        int code = Integer.parseInt(content);
        ProcessTreeDto ptTree = processTreeService.findByCode(code);
        code = CommonConstant.ROOT_PROCESS_TREE_CODE;
        
        if(Objects.isNull(ptTree)){
            ptTree = processTreeService.findByCode(code);
        }

        UserInfoDto userInfoDto = CommonConstant.GLOBAL_USER_INFO.get();
        if(Objects.nonNull(userInfoDto)) {
            userInfoDto.setActiveFeatureCode(ptTree.getFeatureCode());
            userInfoMapper.updateById(userInfoDto);
        }

        List<ProcessTreeDto> ptTreeDtoList = processTreeService.findToRootByCode(code);

        String result = ptTreeDtoList.stream().map(e->String.format("%s[%s]",e.getCode(),e.getName())).collect(Collectors.joining("/","/",""));

        result += processTreeService.findByParentId(ptTree.getId())
                .stream()
                .map(e->String.format("-%s[%s]",e.getCode(),e.getName()))
                .collect(Collectors.joining("\r\n","\r\n","\r\n"));

        result += ptTree.getDescription();
        return result;
    }
}
