package dwl;

import dwl.utils.ParseUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.junit.Test;
import org.springframework.core.io.Resource;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Map;

/**
 * @author wenlong.ding
 * @date 2020/11/14 17:02
 */

@Slf4j
public class CtxTest extends BaseTest {



    @Test
    public void userMapperTest() throws Exception{
        Resource resource = resourceLoader.getResource("classpath:dwl/demo/wx_pic.xml");
        InputStream inputStream = resource.getInputStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        IOUtils.copy(inputStream,out);
        String s = out.toString();
        System.out.println(s);
        Map<String, Object> map = ParseUtil.xmlToMap(s);
        System.out.println(map);
        String s1 = ParseUtil.mapToXml(map);
        System.out.println(s1);
    }

    @Test
    public void commandTest(){
//        internalTest("cd 0");
//        internalTest("cd 1");
        internalTest("2");
//        internalTest("cd 3");
    }

//    @Test
    public void internalTest(String command) {
        String wxReq = TestConstant.getWxReq(e -> e.setContent(command));
        Object handle = msgGateway.handle(wxReq);
        log.info("{}",handle);
    }


}
