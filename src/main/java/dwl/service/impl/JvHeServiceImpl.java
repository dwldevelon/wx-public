package dwl.service.impl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import dwl.model.jvhe.BaseResp;
import dwl.model.jvhe.XiaoHuaResp;
import dwl.properties.JvHeProperties;
import dwl.service.JvHeService;
import dwl.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
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
    public XiaoHuaResp getXiaoHua() {
        String url = jvHeProperties.getUrl() + jvHeProperties.getXhPath();
        url += "?";
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("sort","asc");
        paramMap.put("page","1");
        paramMap.put("pagesize","20");
        paramMap.put("time","1418816972");
        paramMap.put("key",jvHeProperties.getXhKey());
        String param = paramMap.keySet().stream().map(e -> e + "=" + paramMap.get(e)).collect(Collectors.joining("&"));
        url += param;

        String resp = HttpUtil.get(url, String.class);

        log.info("笑话:{}",resp);
        ObjectMapper objectMapper = new ObjectMapper();
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        JavaType paraType = typeFactory.constructType(XiaoHuaResp.class);
        JavaType javaType = typeFactory.constructSimpleType(BaseResp.class, new JavaType[]{paraType});
        BaseResp<XiaoHuaResp> result;
        try {
            result = objectMapper.readValue(resp, javaType);
            return result.getResult();
        } catch (IOException e) {
            log.error("笑话json解析异常",e);
            return null;
        }

    }
}
