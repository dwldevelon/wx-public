package dwl.model.entity;

import lombok.Data;

/**
 * @author wenlong.ding
 * @date 2020/11/14 17:30
 */
@Data
public class XiaoHua {
    private long id;
    private String hashId;
    private String content;
    private long unixtime;
    private String updatetime;
}
