package dwl.model.enums;

import dwl.service.business.WXCommand;
import dwl.service.business.command.CdCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author wenlong.ding
 * @date 2020/11/17 11:10
 */
@AllArgsConstructor
@Getter
public enum  OperateEnum {

    CD(1,"cd","切换流程操作", CdCommand.class);
    ;

    public static final String DEFAULT_OPERATE = "cd 0";

    private int code;
    // 命令
    private String cmd;
    private String desc;
    private Class<? extends WXCommand> execImpl;

    public static OperateEnum findByContent(String content){
        return Objects.isNull(content) ? null :
                Arrays.stream(OperateEnum.values()).filter(e->content.startsWith(e.getCmd())).findFirst().orElse(null);
    }
}
