package dwl.model.enums;

import dwl.service.business.WXCommand;
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

    XIAO_HUA(1,"笑话", XiaoHuaCommand.class),
//    NEWS(2,"新闻", NewsCommand.class)

   // todo 2020/12/1 16:09  设置code

    NEWS_TOP(1,"top",null),
    NEWS_SHE_HUI(2,"shehui",null),
    NEWS_GUO_NEI(3,"guonei",null),
    NEWS_GUO_JI(4,"guoji",null),
    NEWS_YU_LE(5,"yule",null),
    NEWS_TI_YU(6,"tiyu",null),
    NEWS_JUN_SHI(7,"junshi",null),
    NEWS_KE_JI(8,"keji",null),
    NEWS_CAI_JING(9,"caijing",null),
    NEWS_SHI_SHANG(10,"shishang",null),

    ;

    private int code;
    private String desc;
    private Class<? extends WXCommand> clazz;
}
