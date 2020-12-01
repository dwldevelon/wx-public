package dwl.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wenlong.ding
 * @date 2020/11/26 11:36
 */
@Getter
@AllArgsConstructor
public enum  JvHeRespTypeEnum implements SuperEnum {
    SUCCESS(0,"0","success"),
    LIMIT_EXCEEDED(10012,"10012","超过每日可允许请求次数"),
    UN_KNOW(0,"","未知异常"),
    ;
    private int code;
    private String errorCode;
    private String desc;

}
