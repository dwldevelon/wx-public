package dwl.model.enums;

import dwl.service.business.Command;
import dwl.service.business.command.XiaoHuaCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wenlong.ding
 * @date 2020/11/17 10:13
 */
@AllArgsConstructor
@Getter
public enum FeatureEnum implements SuperEnum {

    XIAO_HUA(1,"笑话", XiaoHuaCommand.class)

    ;

    private int code;
    private String desc;
    private Class<? extends Command> clazz;
}
