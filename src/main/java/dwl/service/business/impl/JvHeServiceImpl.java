package dwl.service.business.impl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import dwl.config.plugins.BeanRepository;
import dwl.model.enums.NewsTypeEnum;
import dwl.model.jvhe.*;
import dwl.service.business.JvHeService;
import dwl.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wenlong.ding
 * @date 2020/11/14 17:45
 */
@Service
@Slf4j
public class JvHeServiceImpl extends BeanRepository implements JvHeService{

    @Override
    public XiaoHuaResp getXiaoHua(XiaoHuaReq req) {
        String url = jvHeProperties.getUrl() + jvHeProperties.getXhPath();
        url += "?";
        Map<String,String> paramMap = req.toMap();
        String param = paramMap.keySet().stream().map(e -> e + "=" + paramMap.get(e)).collect(Collectors.joining("&"));
        url += param;
        String resp = HttpUtil.get(url, String.class);
//        log.info("调用笑话接口返回:{}",resp);
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


    @Override
    public NewsResp getNews(NewsTypeEnum newsType) {
        Assert.notNull(newsType,"新闻类型不能为null");
        String url = jvHeProperties.getUrl() + jvHeProperties.getXhPath();
        url += "?";
        NewsReq req = new NewsReq();
        req.setKey(jvHeProperties.getNewsKey());
        req.setType(newsType.getJvHeCode());

        Map<String,String> paramMap = req.toMap();
        String param = paramMap.keySet().stream().map(e -> e + "=" + paramMap.get(e)).collect(Collectors.joining("&"));
        url += param;
        String resp = HttpUtil.get(url, String.class);
        log.info("调用头条接口返回:{}",resp);

        ObjectMapper objectMapper = new ObjectMapper();
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        JavaType paraType = typeFactory.constructType(NewsResp.class);
        JavaType javaType = typeFactory.constructSimpleType(BaseResp.class, new JavaType[]{paraType});
        BaseResp<NewsResp> result;
        try {
            result = objectMapper.readValue(resp, javaType);
            if(result.success()) {
                return result.getResult();
            }
        } catch (IOException e) {
            log.error("头条json解析异常",e);
        }
        return null;
    }
}
