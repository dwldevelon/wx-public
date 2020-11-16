package dwl.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapLikeType;
import dwl.constant.WxConstant;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

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


    public static <T> T mapToBean(Map<String, String> map,Class<T> clazz){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(objectMapper.writeValueAsString(map),clazz);
        } catch (IOException e) {
            log.error("mapToBean error: map = {} ,clazz = {}",map,clazz,e);
            return null;
        }
    }

    public static Map<String,String> beanToMap(Object obj){
        if(Objects.isNull(obj)){
            return Collections.emptyMap();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            MapLikeType mapLikeType = objectMapper.getTypeFactory().constructMapLikeType(Map.class, String.class, String.class);
            return objectMapper.readValue(objectMapper.writeValueAsString(obj),mapLikeType);
        }catch (Exception e){
            return Collections.emptyMap();
        }
    }

    public static Map<String, String> xmlToMap(String xml) {
        Map<String, String> result = new HashMap<>();
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(new ByteArrayInputStream(xml.getBytes()));
            Element rootElement = document.getRootElement();
            Iterator<Element> propertiesElementIterator = rootElement.elementIterator();
            while (propertiesElementIterator.hasNext()) {
                Element propertiesElement = propertiesElementIterator.next();
                result.put(propertiesElement.getName(),propertiesElement.getTextTrim());
            }
        } catch (DocumentException e) {
            log.error("解析xml失败:{}",xml,e);
        }
        return result;
    }

    public static String mapToXml(Map<String,String> map){
        Document document = DocumentHelper.createDocument();
        Element rootElement = document.addElement(WxConstant.XML_ROOT);
        map.forEach((k,v)->{
            Element element = rootElement.addElement(k);
            element.addText(v);
        });

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

    public static <T> T xmlToBean(String xml,Class<T> tClass){
        return mapToBean(xmlToMap(xml),tClass);
    }

    public static String beanToXml(Object obj){
        return mapToXml(beanToMap(obj));
    }
}
