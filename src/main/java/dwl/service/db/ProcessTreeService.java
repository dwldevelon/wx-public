package dwl.service.db;

import com.baomidou.mybatisplus.extension.service.IService;
import dwl.model.entity.ProcessTreeDto;

import java.util.List;

/**
 * @author wenlong.ding
 * @date 2020/11/17 9:36
 */
public interface ProcessTreeService extends IService<ProcessTreeDto> {

    ProcessTreeDto findByCode(int code);

    /**
     * 查询所有节点，直到到根节点，默认应该以层级排序
     */
    List<ProcessTreeDto> findToRootByCode(int code);

    List<ProcessTreeDto> findByParentId(Long parentIds);

}
