package dwl.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author wenlong.ding
 * @date 2020/9/21 15:05
 */
@Slf4j
public class CommonUtil {
    private CommonUtil(){}

    /**
     * 根据一个原始集合中，元素的某一字段，查询另一个集合
     * @param source 原始集合
     * @param f 获取元素字段的方法
     * @param resultF 查询另一个集合的方法
     * @param <T> 原始集合中元素的类型
     * @param <M> 集合元素关联字段的类型
     * @param <R> 结果集合的泛型类型
     * @return List<R>
     */
    public static <T,M,R> List<R> associateList(Collection<T> source, Function<T,M> f, Function<Collection<M>,List<R>> resultF) {
        Assert.notNull(source, "source must not null");
        Set<M> mSet = source.stream().map(f).filter(Objects::nonNull).collect(Collectors.toSet());
        return mSet.size() == 0  ? new ArrayList<>() :  resultF.apply(mSet);
    }

    /**
     *
     * @param collector ${@link CommonUtil#associateList(Collection, Function, Function)} 结果集收集器
     */
    public static <T,M,R,A,B> B associateList(Collection<T> source, Function<T,M> f, Function<Collection<M>,List<R>> resultF, Collector<? super R, A, B> collector){
        Assert.notNull(collector,"collector must not null");
        return associateList(source, f, resultF).stream().collect(collector);
    }
/*
    public static <T extends WXBaseResp> T castOrCrate(WXBaseResp resp, Class<T> mClass){
        boolean assignableFrom = mClass .isAssignableFrom(resp.getClass());
        if(assignableFrom){
            return (T) resp;
        }else {
            T t;
            try {
                t = mClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                log.error("{}",e.getMessage());
                throw new RuntimeException("系统异常",e);
            }
            BeanUtils.copyProperties(resp,t);
            return t;
        }
    }

    public static <T extends WXBaseReq> T checkReq(WXBaseReq wxBaseReq,Class<T> tClass){
        Assert.notNull(wxBaseReq,"请求消息不能为null");
        boolean assignableFrom = tClass .isAssignableFrom(wxBaseReq.getClass());
        if(!assignableFrom){
            throw new RuntimeException("not support req");
        }
        return  (T) wxBaseReq;
    }*/


}
