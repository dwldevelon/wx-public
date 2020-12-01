package dwl.service.db;

import com.baomidou.mybatisplus.extension.service.IService;
import dwl.model.entity.NewsDto;

import java.util.Collection;
import java.util.List;

/**
 * @author wenlong.ding
 * @date 2020/11/30 13:58
 */
public interface NewsService extends IService<NewsDto> {

    List<NewsDto> findByUniqueKeys(Collection<String> uniqueKeys);

}
