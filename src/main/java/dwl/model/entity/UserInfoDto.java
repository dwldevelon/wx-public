package dwl.model.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author wenlong.ding
 * @date 2020/11/17 10:46
 */
@Data
@TableName("user_info")
public class UserInfoDto {

    private long id;

    /**
     * 微信openId;
     */
    private String openId;

    /**
     * 激活
     */
    @TableField(value = "active_feature_code",updateStrategy = FieldStrategy.IGNORED)
    private Long activeFeatureCode;

    /**
     * 当前笑话id;
     */
    private Long currXiaoHuaId;

}
