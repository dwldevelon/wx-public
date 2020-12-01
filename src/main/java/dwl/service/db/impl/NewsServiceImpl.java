package dwl.service.db.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dwl.mapper.NewsMapper;
import dwl.model.entity.NewsDto;
import dwl.service.db.NewsService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.List;

/**
 * @author wenlong.ding
 * @date 2020/11/30 13:59
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, NewsDto> implements NewsService {

    @Override
    public List<NewsDto> findByUniqueKeys(Collection<String> uniqueKeys) {
        Assert.notEmpty(uniqueKeys,"参数不能为空");
        QueryWrapper<NewsDto> qw = new QueryWrapper<>();
        qw.in("uniquekey",uniqueKeys);
        return list(qw);
    }
}
