package dwl.service.business;

import dwl.model.enums.NewsTypeEnum;
import dwl.model.jvhe.NewsResp;
import dwl.model.jvhe.XiaoHuaReq;
import dwl.model.jvhe.XiaoHuaResp;

/**
 * @author wenlong.ding
 * @date 2020/11/14 17:44
 */
public interface JvHeService {

    XiaoHuaResp getXiaoHua(XiaoHuaReq req);

    NewsResp getNews(NewsTypeEnum newsType);

}
