package dwl.plugins;

import dwl.mapper.ProcessTreeMapper;
import dwl.mapper.UserInfoMapper;
import dwl.mapper.XiaoHuaMapper;
import dwl.properties.JvHeProperties;
import dwl.properties.WxProperties;
import dwl.schedule.XHTask;
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
{

    // ------------------ spring boot ---------------------
    @Resource
    protected ApplicationContext ctx;



    // ----------------- properties -------------------------
    @Resource
    protected JvHeProperties jvHeProperties;
    @Resource
    protected WxProperties wxProperties;



    // ----------------- mapper --------------------
    @Resource
    protected XiaoHuaMapper xiaoHuaMapper;
    @Resource
    protected UserInfoMapper userInfoMapper;
    @Resource
    protected ProcessTreeMapper processTreeMapper;



    // --------------------- simple service --------------
    @Resource
    protected XiaoHuaService xiaoHuaService;
    @Resource
    protected UserInfoService userInfoService;
    @Resource
    protected ProcessTreeService processTreeService;



    // -------------------- business service ------------
    @Resource
    protected XHService xhService;
    @Resource
    protected PTService ptService;


    // --------------- extend service --------------------
    @Resource
    protected JvHeService jvHeService;
    @Resource
    protected MsgGateway msgGateway;
    @Resource
    protected WxViewResolver wxViewResolver;
    @Resource
    protected HeadAndTailWrapper headAndTailWrapper;



    // ------------------- Scheduled -----------------------
    @Resource
    protected XHTask xhTask;
}
