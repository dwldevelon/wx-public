package dwl.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author wenlong.ding
 * @date 2020/9/10 9:58
 */
public class PageCopyUtil {
    private PageCopyUtil(){}

    public static  <T,R> IPage<R> copy(IPage<T> source, Function<T,R>  elementTransFormFunction){
        return copyList(source,s -> s.stream().map(elementTransFormFunction).collect(Collectors.toList()));
    }

    public static  <T,R> IPage<R> copyList(IPage<T> source, Function<List<T>,List<R>>  listTransFormFunction){
        if(source == null || CollectionUtils.isEmpty(source.getRecords())){
            return new Page<>();
        }
        IPage<R> target = new Page<>();
        BeanUtils.copyProperties(source,target,"records");
        return target.setRecords(listTransFormFunction.apply(source.getRecords()));
    }


}
