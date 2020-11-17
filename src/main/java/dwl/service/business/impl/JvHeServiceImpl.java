package dwl.service.business.impl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import dwl.model.jvhe.BaseResp;
import dwl.model.jvhe.XiaoHuaReq;
import dwl.model.jvhe.XiaoHuaResp;
import dwl.properties.JvHeProperties;
import dwl.service.business.JvHeService;
import dwl.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wenlong.ding
 * @date 2020/11/14 17:45
 */
@Service
@Slf4j
public class JvHeServiceImpl implements JvHeService{

    @Resource
    JvHeProperties jvHeProperties;
    @Override
    public XiaoHuaResp getXiaoHua(XiaoHuaReq req) {
        String url = jvHeProperties.getUrl() + jvHeProperties.getXhPath();
        url += "?";
        Map<String,String> paramMap = req.toMap();
        String param = paramMap.keySet().stream().map(e -> e + "=" + paramMap.get(e)).collect(Collectors.joining("&"));
        url += param;
        String resp = HttpUtil.get(url, String.class);
        log.info("调用笑话接口返回:{}",resp);
        ObjectMapper objectMapper = new ObjectMapper();
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        JavaType paraType = typeFactory.constructType(XiaoHuaResp.class);
        JavaType javaType = typeFactory.constructSimpleType(BaseResp.class, new JavaType[]{paraType});
        BaseResp<XiaoHuaResp> result;
        try {
            result = objectMapper.readValue(resp, javaType);
            if(result.success()) {
                return result.getResult();
            }
        } catch (IOException e) {
            log.error("笑话json解析异常",e);
        }
        return null;

    }
}
