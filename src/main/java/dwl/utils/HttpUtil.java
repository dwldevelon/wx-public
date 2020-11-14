package dwl.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * 常用http请求工具类
 *
 * @author cherrytang
 * @version 1.0
 * @date 2018/11/5
 */
public final class HttpUtil {

    private HttpUtil(){
        throw new Error("not support");
    }
    /**
     * 发起一个get请求
     * @param url 请求url
     * @param clazz 返回类型
     * @param params url参数
     * @param <R> 返回类型
     * @return R
     */
    public static <R> R get(String url, Class<R> clazz,Object... params){
        Assert.notNull(url,"url 不能为空");
        return new RestTemplate().getForObject(url,clazz,params);
    }

    /**
     * post发送json请求
     * @param url 请求url
     * @param body 请求体
     * @param clazz 返回类型
     * @param params url参数
     * @param <R> 返回类型
     * @param <B> 请求体类型
     * @return R
     */
    public static <R,B> R postJson(String url,B body,Class<R> clazz,Object... params){
        Assert.notNull(url,"url 不能为空");
        String jsonBody;
        if(body == null){
            throw new Error("body不能为null");
        }else if(body instanceof String){
            jsonBody = (String) body;
            if(jsonBody.length() == 0){
                throw new Error("body不能为空");
            }
        }else {
            try {
                jsonBody = new ObjectMapper().writeValueAsString(body);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                throw new IllegalArgumentException("json 序列化异常");
            }
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> formEntity = new HttpEntity<>(jsonBody, headers);
        return new RestTemplate().postForObject(url,formEntity,clazz,params);
    }

    /**
     * post 请求
     * @param url 请求url
     * @param mediaType MULTIPART_FORM_DATA和APPLICATION_FORM_URLENCODED 测试通过
     * @param map 请求体参数
     * @param clazz 返回类型
     * @param params url填充参数
     * @param <R> 返回对象
     * @return R
     */
    private static <R> R post(String url, MediaType mediaType, Map<String,String> map, Class<R> clazz, Object... params){
        Assert.notNull(url,"url 不能为空");
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        map.forEach(multiValueMap::add);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(multiValueMap, headers);
        return new RestTemplate().postForObject(url, httpEntity,clazz,params);
    }

    public static <R> R postFormData(String url, Map<String,String> map ,Class<R> clazz,Object... params){
        return HttpUtil.post(url, MediaType.MULTIPART_FORM_DATA,map,clazz,params);
    }

    public static <R> R postUrlencoded(String url,Map<String,String> map,Class<R> clazz,Object... params){
        return HttpUtil.post(url, MediaType.APPLICATION_FORM_URLENCODED,map,clazz,params);
    }


}
