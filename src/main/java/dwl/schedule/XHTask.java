package dwl.schedule;

import dwl.constant.XHSort;
import dwl.model.entity.XiaoHuaDto;
import dwl.model.jvhe.XiaoHuaReq;
import dwl.model.jvhe.XiaoHuaResp;
import dwl.plugins.BeanRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author wenlong.ding
 * @date 2020/11/17 17:59
 */
@Component
public class XHTask extends BeanRepository {
    @Scheduled(cron = "1 0 0 1/1 * ? ")
    public void autoGetXiaoHua(){
        internalDo(xiaoHuaService::findFirst,XHSort.DESC);
        internalDo(xiaoHuaService::findLast,XHSort.ASC);
    }

    private void internalDo(Supplier<XiaoHuaDto> xhSupplier,String sort){
        XiaoHuaDto xiaoHuaDto = xhSupplier.get();
        if(xiaoHuaDto == null){
            xiaoHuaDto = new XiaoHuaDto();
            xiaoHuaDto.setUnixTime(System.currentTimeMillis()/1000);
        }
        XiaoHuaReq req = new XiaoHuaReq();
        req.setSort(sort);
        req.setPage(1);
        req.setPageSize(20);
        req.setKey(jvHeProperties.getXhKey());
        while (true){
            req.setTime(String.valueOf(xiaoHuaDto.getUnixTime()));
            XiaoHuaResp xiaoHua = jvHeService.getXiaoHua(req);
            if(Objects.isNull(xiaoHua) || CollectionUtils.isEmpty(xiaoHua.getData())){
                break;
            }
            xhService.save(xiaoHua);
            switch (sort){
                case XHSort.ASC:
                    Long minTime = xiaoHua.getData().stream().map(XiaoHuaResp.Data::getUnixtime).min(Long::compareTo).get();
                    if(!Objects.equals(xiaoHuaDto.getUnixTime(),minTime)){
                        xiaoHuaDto = xhSupplier.get();
                        req.setPage(1);
                    }else{
                        req.setPage(req.getPage() + 1);
                    }
                    break;
                case XHSort.DESC:
                    Long maxTime = xiaoHua.getData().stream().map(XiaoHuaResp.Data::getUnixtime).max(Long::compareTo).get();
                    if(!Objects.equals(xiaoHuaDto.getUnixTime(),maxTime)){
                        xiaoHuaDto = xhSupplier.get();
                        req.setPage(1);
                    }else{
                        req.setPage(req.getPage() + 1);
                    }
                    break;
            }
        }
    }
}
