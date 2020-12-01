package dwl.model;

import dwl.model.wxmsg.req.WXBaseReq;
import dwl.model.wxmsg.resp.WXBaseResp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

/**
 * @author wenlong.ding
 * @date 2020/12/1 10:20
 */
@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class WXContext {

    private WXBaseReq req;

    private WXBaseResp resp;

    public <T extends WXBaseReq> T checkReq(Class<T> tClass) {
        Assert.notNull(req, "请求消息不能为null");
        boolean assignableFrom = tClass.isAssignableFrom(req.getClass());
        if (!assignableFrom) {
            throw new RuntimeException("not support req");
        }
        return (T) req;
    }

    public <T extends WXBaseResp> T castOrCrate(Class<T> mClass) {
        boolean assignableFrom = mClass.isAssignableFrom(resp.getClass());
        if (assignableFrom) {
            return (T) resp;
        }
        T t;
        try {
            t = mClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("{}", e.getMessage());
            throw new RuntimeException("系统异常", e);
        }
        BeanUtils.copyProperties(resp, t);
        this.resp = t;
        return t;
    }

}
