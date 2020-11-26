package dwl.service.business.impl;

import dwl.model.entity.XiaoHuaDto;
import dwl.model.jvhe.XiaoHuaResp;
import dwl.config.plugins.BeanRepository;
import dwl.service.business.XHService;
import dwl.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author wenlong.ding
 * @date 2020/11/16 9:45
 */
@Service
@Slf4j
public class XHServiceImpl extends BeanRepository implements XHService {

    @Override
    public void save(XiaoHuaResp resp) {
        if (Objects.isNull(resp)) {
            log.warn("笑话数据为空，取消保存");
        }
        List<XiaoHuaResp.Data> xhs = resp.getData();
        List<XiaoHuaDto> existXiaoHuaDtoList = CommonUtil.associateList(xhs, XiaoHuaResp.Data::getHashId, xiaoHuaService::findByHashIds);
        List<String> existHashIds = existXiaoHuaDtoList.stream().map(XiaoHuaDto::getHashId).collect(Collectors.toList());
        List<XiaoHuaDto> xiaoHuaDtoList = xhs.stream()
                // 过滤掉已存在的笑话
                .filter(xh -> {
                    boolean exist = existHashIds.contains(xh.getHashId());
                    if (exist) {
                        log.warn("笑话已存在，跳过入库，hashId={}", xh.getHashId());
                    }
                    return !exist;
                })
                .map(xh -> {
                    XiaoHuaDto xiaoHuaDto = new XiaoHuaDto();
                    xiaoHuaDto.setHashId(xh.getHashId());
                    xiaoHuaDto.setContent(xh.getContent());
                    xiaoHuaDto.setUnixTime(xh.getUnixtime());
                    xiaoHuaDto.setUpdateTime(xh.getUpdatetime());
                    return xiaoHuaDto;
                })
                .collect(Collectors.toList());

        if (xiaoHuaDtoList.size() > 0) {
            xiaoHuaService.saveBatch(xiaoHuaDtoList);
        }

    }
}
