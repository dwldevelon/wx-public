package dwl.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author wenlong.ding
 * @date 2020/11/14 17:30
 */
@Data
@TableName("xiao_hua")
public class XiaoHuaDto {

    private long id;
    private String hashId;
    private String content;
    private long unixTime;
    private String updateTime;

}
