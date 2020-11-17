package dwl.service.db.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dwl.mapper.ProcessTreeMapper;
import dwl.model.entity.ProcessTreeDto;
import dwl.service.db.ProcessTreeService;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author wenlong.ding
 * @date 2020/11/17 9:37
 */
@Service
public class ProcessTreeServiceImpl extends ServiceImpl<ProcessTreeMapper, ProcessTreeDto> implements ProcessTreeService {

    @Override
    public ProcessTreeDto findByCode(int code) {
        QueryWrapper<ProcessTreeDto> pw = new QueryWrapper<>();
        pw.eq("code",code);
        return getOne(pw);
    }

    @Override
    public List<ProcessTreeDto> findToRootByCode(int code) {
        List<ProcessTreeDto> result = new ArrayList<>();
        ProcessTreeDto ptd = findByCode(code);
        if(Objects.isNull(ptd)){
            return result;
        }
        result.add(ptd);
        while (ptd.getParentId() != null){
            ProcessTreeDto p = getById(ptd.getParentId());
            if(Objects.isNull(p)){
                break;
            }
            result.add(p);
            ptd = p;
        }
        Collections.reverse(result);
        return result;
    }

    @Override
    public List<ProcessTreeDto> findByParentId(Long parentIds) {
        QueryWrapper<ProcessTreeDto> qw = new QueryWrapper<>();
        qw.eq("parent_id",parentIds);
        return list(qw);
    }
}
