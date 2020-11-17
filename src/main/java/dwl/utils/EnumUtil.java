package dwl.utils;

import dwl.model.enums.SuperEnum;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;

/**
 * 枚举通用方法
 * @author wenlong.ding
 * @date 2020/9/15 15:00
 */
public class EnumUtil {

    private EnumUtil(){throw new Error("not support");}

    @SuppressWarnings("all")
    public static <E extends Enum<E> & SuperEnum> E findOne(Class<E> enumClass, Integer searchFiled){
        return findOne(enumClass, e -> e.getCode(),searchFiled);
    }

    public static <T,E extends Enum<E>> E findOne(Class<E> enumClass, Function<E,T> getEnumFieldFunction ,T searchFiled){
        return findOne(enumClass,getEnumFieldFunction,searchFiled,null);
    }

    /**
     * 根据枚举的某一属性查询枚举，例如 code
     * @param enumClass 枚举类型
     * @param getEnumFieldFunction 获取枚举某一属性的函数
     * @param searchFiled 等待和枚举字段值匹配的值，一般数据库存的这个值
     * @param defaultValue 如果查询不到，返回的默认值
     * @param <T> 查询字段的类型，枚举应当包含这种类型的属性
     * @param <E> 枚举类型
     * @return 查询出的枚举，如果查不到，返回默认值
     */
    public static <T,E extends Enum<E>> E findOne(Class<E> enumClass, Function<E,T> getEnumFieldFunction ,T searchFiled,E defaultValue){
        Assert.notNull(enumClass,"class not null");
        Assert.notNull(getEnumFieldFunction,"获取枚举属性函数不能为null");
        return searchFiled == null ? defaultValue :
                Arrays.stream(enumClass.getEnumConstants())
                .filter(e -> Objects.equals(getEnumFieldFunction.apply(e),searchFiled))
                .findFirst().orElse(defaultValue);
    }
}
