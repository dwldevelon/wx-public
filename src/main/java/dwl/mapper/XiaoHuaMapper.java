package dwl.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import dwl.model.entity.XiaoHuaDto;

/**
 * @author wenlong.ding
 * @date 2020/11/14 17:33
 */
public interface XiaoHuaMapper extends BaseMapper<XiaoHuaDto> {
    XiaoHuaDto findOneByRandom();
}
