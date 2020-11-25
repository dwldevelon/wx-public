package dwl.service.db;

import com.baomidou.mybatisplus.extension.service.IService;
import dwl.model.entity.XiaoHuaDto;

import java.util.Collection;
import java.util.List;

/**
 * @author wenlong.ding
 * @date 2020/11/14 17:43
 */
public interface XiaoHuaService extends IService<XiaoHuaDto> {

    List<XiaoHuaDto> findByHashIds(Collection<String> hashIds);

    /**
     * 获取时间最早的笑话
     */
    XiaoHuaDto findFirst();

    /**
     * 获取时间最近的笑话
     */
    XiaoHuaDto findLast();

    /**
     * 查询指定id的下一个笑话
     */
    XiaoHuaDto findNext(Long id);

    /**
     * 查询指定id的上一个笑话
     */
    XiaoHuaDto findPrevious(Long id);



}
