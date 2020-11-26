package dwl.service.business.impl;

import dwl.config.constant.CommonConstant;
import dwl.config.constant.exp.ExpObjConstant;
import dwl.config.constant.exp.ExpPlanConstant;
import dwl.config.constant.exp.ExpReasonConstant;
import dwl.config.constant.wx.WxConstant;
import dwl.config.constant.wx.WxMsgTypeConstant;
import dwl.model.entity.UserInfoDto;
import dwl.model.wxmsg.req.TextMessage;
import dwl.model.wxmsg.resp.TextRespMessage;
import dwl.config.plugins.BeanRepository;
import dwl.service.business.MsgGateway;
import dwl.utils.ParseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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

    @Override
    public Object handle(String requestBody) {
        Map<String,String> requestMap = ParseUtil.xmlToMap(requestBody);
        String msgType = requestMap.get(WxConstant.MsgType);
        if(StringUtils.isEmpty(msgType)){
            log.warn("{}{},{}", ExpObjConstant.WX_MSG_TYPE, ExpReasonConstant.IS_EMPTY, ExpPlanConstant.SKIP_WX_MSG_HANDLE);
            return WxConstant.DEFAULT_RETURN;
        }

        // ThreadLocal存储当前用户
        Consumer<String> initUser = openId -> {
            UserInfoDto userInfoDto = userInfoService.findByOpenId(openId);
            if(Objects.isNull(userInfoDto)){
                userInfoDto = new UserInfoDto();
                userInfoDto.setOpenId(openId);
                userInfoService.save(userInfoDto);
            }
            CommonConstant.GLOBAL_USER_INFO.set(userInfoDto);
        };


        switch (msgType){
            case WxMsgTypeConstant.TEXT:
                TextMessage textMessage = ParseUtil.mapToBean(requestMap, TextMessage.class);
                if(Objects.isNull(textMessage)){
                    log.warn("{}{}:[class={},{}={}]",ExpObjConstant.WX_MAP_MSG,ExpReasonConstant.TRANS_OBJ_ERROR,TextMessage.class,ExpObjConstant.IN_MAP,requestMap);
                    return WxConstant.DEFAULT_RETURN;
                }
                initUser.accept(textMessage.getFromUserName());

                TextRespMessage respMessage = new TextRespMessage();
                respMessage.setContent(wrapperContent(wxViewResolver.resolve(textMessage.getContent())));
                respMessage.setToUserName(textMessage.getFromUserName());
                respMessage.setFromUserName(textMessage.getToUserName());
                respMessage.setCreateTime(new Date().getTime());
                respMessage.setMsgType(textMessage.getMsgType());
                return ParseUtil.beanToXml(respMessage);
            default:
                log.warn("{}{},{}",ExpObjConstant.WX_MSG_TYPE,ExpReasonConstant.NOT_RECOGNIZED,ExpPlanConstant.SKIP_WX_MSG_HANDLE);
                return WxConstant.DEFAULT_RETURN;
        }
    }

    /**
     * 包装content
     */
    private String wrapperContent(String content){
        // todo 2020/11/17 12:32
        return content;
    }
}
