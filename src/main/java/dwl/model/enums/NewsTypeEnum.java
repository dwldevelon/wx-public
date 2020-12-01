package dwl.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wenlong.ding
 * @date 2020/11/26 15:02
 */
@AllArgsConstructor
@Getter
public enum  NewsTypeEnum {
    //	类型,,top(头条，默认),shehui(社会),guonei(国内),guoji(国际),yule(娱乐),tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)

    TOP(1,"top","头条"),
    SHE_HUI(2,"shehui","社会"),
    GUO_NEI(3,"guonei","国内"),
    GUO_JI(4,"guoji","国际"),
    YU_LE(5,"yule","娱乐"),
    TI_YU(6,"tiyu","体育"),
    JUN_SHI(7,"junshi","军事"),
    KE_JI(8,"keji","科技"),
    CAI_JING(9,"caijing","财经"),
    SHI_SHANG(10,"shishang","时尚"),
    ;
    private int code;
    private String jvHeCode;
    private String desc;

}
