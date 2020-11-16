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

}
