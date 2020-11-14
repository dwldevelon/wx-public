package dwl.model.msg;

import com.fasterxml.jackson.annotation.JsonProperty;
import dwl.constant.WxConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author wenlong.ding
 * @date 2020/10/22 20:46
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class TextMessage extends BaseMessage {

    @JsonProperty(WxConstant.Content)
    private String content;

}
