package dwl.model.enums;

import dwl.service.business.CommandSupplier;
import dwl.service.business.WXCommand;
import dwl.service.business.command.NextXiaoHuaCommand;
import dwl.service.business.command.PreviousXiaoHuaCommand;
import dwl.service.business.command.RandomXiaoHuaCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wenlong.ding
 * @date 2020/11/25 15:07
 */
@AllArgsConstructor
@Getter
public enum  XiaoHuaFeatureEnum implements SuperEnum, CommandSupplier {

    RANDOM(0,"随机笑话", RandomXiaoHuaCommand.class),
    PREVIOUS(1,"上一个", PreviousXiaoHuaCommand.class),
    NEXT(2,"下一个", NextXiaoHuaCommand.class)
    ;

    private int code;
    private String desc;
    private Class<? extends WXCommand> command;

}
