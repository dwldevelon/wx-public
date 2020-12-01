package dwl.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapLikeType;
import dwl.config.constant.wx.WxConstant;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.util.CollectionUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;

/**
 * @author wenlong.ding
 * @date 2020/11/6 15:06
 */
@Slf4j
public class ParseUtil {
    private ParseUtil() {
    }


    public static <T> T mapToBean(Map<String, Object> map,Class<T> clazz){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(objectMapper.writeValueAsString(map),clazz);
        } catch (IOException e) {
            log.error("mapToBean error: map = {} ,clazz = {}",map,clazz,e);
            return null;
        }
    }

    public static Map<String,Object> beanToMap(Object obj){
        if(Objects.isNull(obj)){
            return Collections.emptyMap();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            MapLikeType mapLikeType = objectMapper.getTypeFactory().constructMapLikeType(Map.class, String.class, Object.class);
            return objectMapper.readValue(objectMapper.writeValueAsString(obj),mapLikeType);
        }catch (Exception e){
            return Collections.emptyMap();
        }
    }

    public static Map<String, Object> xmlToMap(String xml) {
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(new ByteArrayInputStream(xml.getBytes()));
            Element rootElement = document.getRootElement();
            Map<String, Object> internal = internal(rootElement);
            return (Map<String,Object>)internal.get(WxConstant.XML_ROOT);
        } catch (DocumentException e) {
            log.error("解析xml失败:{}",xml,e);
        }
        return null;
    }

    private static Map<String,Object> internal(Element element){
        Map<String,Object> map = new HashMap<>();
        List<Element> elements = element.elements();
        if(CollectionUtils.isEmpty(elements)){
            map.put(element.getName(),element.getTextTrim());
        }else {
            Map<String, Object> result = elements.stream().map(ParseUtil::internal).reduce(new HashMap<>(), (e1, e2) -> {
                // todo 同一级别相同html标签处理
                e1.putAll(e2);
                return e1;
            });
            map.put(element.getName(),result);
        }
        return map;
    }

    public static String mapToXml(Map<String,Object> map){
        Document document = DocumentHelper.createDocument();
        Element rootElement = document.addElement(WxConstant.XML_ROOT);
        mapToXml(map,rootElement);

        OutputFormat outputFormat = new OutputFormat();
        outputFormat.setEncoding("UTF-8");
//            outputFormat.setSuppressDeclaration(true); //是否不生产xml头
        outputFormat.setIndent(true); //设置是否缩进
        outputFormat.setNewlines(true); //设置是否换行
        StringWriter stringWriter = new StringWriter();
        XMLWriter xmlWriter = new XMLWriter(stringWriter,outputFormat);
        try {
            xmlWriter.write(document);
            return stringWriter.toString();
        } catch (IOException e) {
            log.error("转化为xml error，map={}",map);
            return "";
        }
    }

    private static void mapToXml(Object obj ,Element element){
        if(obj instanceof List){
            String name = element.getName();
            List<?> l = (List) obj;
            for (int i = 0; i < l.size(); i++) {
                if(i > 0){
                    mapToXml( l.get(i),element.getParent().addElement(name));
                }else {
                    mapToXml(l.get(i),element);
                }
            }
        }else if(obj instanceof Map){
            Map<?,?> m = (Map)obj;
            m.forEach((k,v)-> mapToXml(v,element.addElement(String.valueOf(k))));
        }else {
            element.addText(String.valueOf(obj));
        }
    }

    public static <T> T xmlToBean(String xml,Class<T> tClass){
        return mapToBean(xmlToMap(xml),tClass);
    }

    public static String beanToXml(Object obj){
        return mapToXml(beanToMap(obj));
    }
}
