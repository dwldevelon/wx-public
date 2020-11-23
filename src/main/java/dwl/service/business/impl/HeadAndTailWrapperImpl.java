package dwl.service.business.impl;

import dwl.constant.CommonConstant;
import dwl.model.entity.ProcessTreeDto;
import dwl.plugins.BeanRepository;
import dwl.service.business.HeadAndTailWrapper;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author wenlong.ding
 * @date 2020/11/23 14:25
 */
@Slf4j
public class HeadAndTailWrapperImpl extends BeanRepository implements HeadAndTailWrapper {
    @Override
    public String wrapper(Integer code,String content) {
        if(Objects.isNull(code)){
            return content;
        }
        ProcessTreeDto ptTree = processTreeService.findByCode(code);
        if(Objects.isNull(ptTree)){
            log.warn("wrapper失败，code对象不存在，code={}",code);
            return content;
        }
        List<ProcessTreeDto> ptTreeDtoList = processTreeService.findToRootByCode(code);

        String result = ptTreeDtoList.stream().map(e->String.format("%s[%s]",e.getCode(),e.getName())).collect(Collectors.joining("/","/",""));

        result += CommonConstant.RN;
        result += content;
        result += CommonConstant.RN;
        result += ptTree.getDescription();
        return null;
    }
}
