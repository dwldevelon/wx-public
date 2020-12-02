package dwl.service.business.impl;

import dwl.config.constant.CommonConstant;
import dwl.model.entity.ProcessTreeDto;
import dwl.config.plugins.BeanRepository;
import dwl.service.business.HeadAndTailWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author wenlong.ding
 * @date 2020/11/23 14:25
 */
@Slf4j
@Service
public class HeadAndTailWrapperImpl extends BeanRepository implements HeadAndTailWrapper {
    @Override
    public String wrapper(Long code, String content) {
        if (Objects.isNull(code)) {
            return content;
        }
        ProcessTreeDto ptTree = processTreeService.findByCode(code);
        if (Objects.isNull(ptTree)) {
            log.warn("wrapper失败，code对象不存在，code={}", code);
            return content;
        }
        List<ProcessTreeDto> ptTreeDtoList = processTreeService.findToRootByCode(code);

        String result = ptTreeDtoList.stream().map(e -> String.format("%s[%s]", e.getCode(), e.getName())).collect(Collectors.joining("/", "/", ""));

        result += CommonConstant.RN;
        if (!StringUtils.isEmpty(content)) {
            result += CommonConstant.RN;
            result += content;
            result += CommonConstant.RN;
        }
        result += CommonConstant.RN;
        result += ptTree.getDescription();
        return result;
    }
}
