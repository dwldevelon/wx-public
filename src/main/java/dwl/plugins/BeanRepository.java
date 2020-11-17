package dwl.plugins;

import dwl.mapper.UserInfoMapper;
import dwl.mapper.XiaoHuaMapper;
import dwl.service.business.*;
import dwl.service.db.ProcessTreeService;
import dwl.service.db.UserInfoService;
import dwl.service.db.XiaoHuaService;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;

/**
 * @author wenlong.ding
 * @date 2020/11/16 9:46
 */

public abstract class BeanRepository
//        implements ApplicationContextAware
{

    @Resource
    protected ApplicationContext ctx;

    @Resource
    protected XiaoHuaMapper xiaoHuaMapper;
    @Resource
    protected UserInfoMapper userInfoMapper;

    @Resource
    protected XiaoHuaService xiaoHuaService;
    @Resource
    protected ProcessTreeService processTreeService;
    @Resource
    protected UserInfoService userInfoService;


    @Resource
    protected JvHeService jvHeService;
    @Resource
    protected XHService xhService;
    @Resource
    protected PTService ptService;
    @Resource
    protected MsgGateway msgGateway;
    @Resource
    protected WxViewResolver wxViewResolver;

//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        this.ctx = applicationContext;
//    }
}
