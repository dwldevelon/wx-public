package dwl.service.business.impl;

import dwl.config.constant.CommonConstant;
import dwl.config.constant.exp.ExpObjConstant;
import dwl.config.constant.exp.ExpPlanConstant;
import dwl.config.constant.exp.ExpReasonConstant;
import dwl.config.constant.wx.WxConstant;
import dwl.config.constant.wx.WxMsgTypeConstant;
import dwl.config.plugins.BeanRepository;
import dwl.model.WXContext;
import dwl.model.entity.UserInfoDto;
import dwl.model.enums.FeatureEnum;
import dwl.model.enums.OperateEnum;
import dwl.model.wxmsg.req.WXTextReqMessage;
import dwl.model.wxmsg.resp.WXTextRespMessage;
import dwl.service.business.MsgGateway;
import dwl.utils.EnumUtil;
import dwl.utils.ParseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * @author wenlong.ding
 * @date 2020/11/16 15:18
 */
@Service
@Slf4j
public class MsgGatewayImpl extends BeanRepository implements MsgGateway {

    // ThreadLocal存储当前用户
    private Consumer<String> initUser = openId -> {
        UserInfoDto userInfoDto = userInfoService.findByOpenId(openId);
        if(Objects.isNull(userInfoDto)){
            userInfoDto = new UserInfoDto();
            userInfoDto.setOpenId(openId);
            userInfoService.save(userInfoDto);
        }
        CommonConstant.GLOBAL_USER_INFO.set(userInfoDto);
    };

    @Override
    public Object handle(String requestBody) {
        Map<String,Object> requestMap = ParseUtil.xmlToMap(requestBody);
        String msgType = Objects.nonNull(requestMap)
                ? String.valueOf(requestMap.get(WxConstant.MsgType))
                : null;
        if(StringUtils.isEmpty(msgType)){
            log.warn("{}{},{}", ExpObjConstant.WX_MSG_TYPE, ExpReasonConstant.IS_EMPTY, ExpPlanConstant.SKIP_WX_MSG_HANDLE);
            return WxConstant.DEFAULT_RETURN;
        }

        WXContext context = new WXContext();

        switch (msgType){
            case WxMsgTypeConstant.TEXT:

                WXTextReqMessage textMessage = ParseUtil.mapToBean(requestMap, WXTextReqMessage.class);
                if(Objects.isNull(textMessage)){
                    log.warn("{}{}:[class={},{}={}]",ExpObjConstant.WX_MAP_MSG,ExpReasonConstant.TRANS_OBJ_ERROR, WXTextReqMessage.class,ExpObjConstant.IN_MAP,requestMap);
                    return WxConstant.DEFAULT_RETURN;
                }
                initUser.accept(textMessage.getFromUserName());

                WXTextRespMessage respMessage = new WXTextRespMessage();
                respMessage.setToUserName(textMessage.getFromUserName());
                respMessage.setFromUserName(textMessage.getToUserName());
                respMessage.setCreateTime(new Date().getTime());
                respMessage.setMsgType(textMessage.getMsgType());
                context.setReq(textMessage);
                context.setResp(respMessage);
                resolve(context);
                return ParseUtil.beanToXml(context.getResp());
            default:
                log.warn("{}{},{}",ExpObjConstant.WX_MSG_TYPE,ExpReasonConstant.NOT_RECOGNIZED,ExpPlanConstant.SKIP_WX_MSG_HANDLE);
                return WxConstant.DEFAULT_RETURN;
        }
    }

    private void resolve(WXContext context ) {
        Assert.notNull(context,"微信请求context不能为null");
        WXTextReqMessage wxTextReqMessage = context.checkReq(WXTextReqMessage.class);
        String content = wxTextReqMessage.getContent();
        // 操作命令
        OperateEnum operateEnum = OperateEnum.findByContent(content);
        if(Objects.nonNull(operateEnum)){
            ctx.getBean(operateEnum.getExecImpl()).exec(context);
            return;
        }
        UserInfoDto userInfoDto = CommonConstant.GLOBAL_USER_INFO.get();
        if(Objects.isNull(userInfoDto)){
            log.warn("用户不存在");
            wxTextReqMessage.setContent(OperateEnum.DEFAULT_OPERATE);
            resolve(context);
            return;
        }
        // 功能命令
        FeatureEnum featureEnum = EnumUtil.findOne(FeatureEnum.class, userInfoDto.getActiveFeatureCode());
        if(Objects.nonNull(featureEnum)){
            ctx.getBean(featureEnum.getClazz()).exec(context);
        }

    }
}
