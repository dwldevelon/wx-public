package dwl.model.entity;

import lombok.Data;

/**
 * @author wenlong.ding
 * @date 2020/11/16 14:53
 */
@Data
public class ProcessTreeDto {
    private long id;
    private long parentId;
    /**
     * 流程码
     */
    private int code;
    /**
     * 流程名称
     */
    private String name;
    /**
     * 说明
     */
    private String desc;
}
