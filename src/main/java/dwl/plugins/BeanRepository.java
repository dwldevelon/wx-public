package dwl.plugins;

import dwl.service.business.JvHeService;
import dwl.service.business.MessageService;
import dwl.service.business.XHService;
import dwl.service.db.XiaoHuaService;

import javax.annotation.Resource;

/**
 * @author wenlong.ding
 * @date 2020/11/16 9:46
 */

public abstract class BeanRepository {

    @Resource
    protected XiaoHuaService xiaoHuaService;


    @Resource
    protected JvHeService jvHeService;


    @Resource
    protected MessageService messageService;
    @Resource
    protected XHService xhService;


}
