package dwl.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author wenlong.ding
 * @date 2020/11/16 14:53
 */
@Data
@TableName("process_tree")
public class ProcessTreeDto {
    private Long id;
    private Long parentId;
    /**
     * 流程码
     */
    private Long code;
    /**
     * 流程名称
     */
    private String name;
    /**
     * 说明
     */
    private String description;
    /**
     * 功能码
     */
    private Long featureCode;
}
