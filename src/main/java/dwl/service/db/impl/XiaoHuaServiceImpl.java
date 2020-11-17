package dwl.service.db.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dwl.mapper.XiaoHuaMapper;
import dwl.model.entity.XiaoHuaDto;
import dwl.service.db.XiaoHuaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.List;

/**
 * @author wenlong.ding
 * @date 2020/11/14 17:45
 */
@Service
@Slf4j
public class XiaoHuaServiceImpl extends ServiceImpl<XiaoHuaMapper, XiaoHuaDto> implements XiaoHuaService {


    @Override
    public List<XiaoHuaDto> findByHashIds(Collection<String> hashIds) {
        Assert.notEmpty(hashIds,"查询参数hasIds不能为空");
        QueryWrapper<XiaoHuaDto> qw = new QueryWrapper<>();
        qw.in("hash_id",hashIds);
        return list(qw);
    }

    @Override
    public XiaoHuaDto findFirst() {
        Page<XiaoHuaDto> page = new Page<>();
        page.setCurrent(1);
        page.setSize(1);
        QueryWrapper<XiaoHuaDto> qw = new QueryWrapper<>();
        qw.orderByAsc("unix_time");
        return page(page,qw).getRecords().get(0);
    }

    @Override
    public XiaoHuaDto findLast() {
        Page<XiaoHuaDto> page = new Page<>();
        page.setCurrent(1);
        page.setSize(1);
        QueryWrapper<XiaoHuaDto> qw = new QueryWrapper<>();
        qw.orderByDesc("unix_time");
        return page(page,qw).getRecords().get(0);
    }
}
