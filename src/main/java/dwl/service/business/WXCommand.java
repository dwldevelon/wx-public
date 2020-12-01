package dwl.service.business;

import dwl.model.WXContext;

/**
 * @author wenlong.ding
 * @date 2020/11/30 17:20
 */
public interface WXCommand {
    void exec(WXContext context);
}
