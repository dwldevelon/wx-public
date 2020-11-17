package dwl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author wenlong.ding
 * @date 2020/11/14 14:11
 */
@Slf4j
public class SimpleTest {
    @Test
    public void beanToXml(){
        String a ="cd";
        String substring = a.substring(2);
        System.out.println("aa:"+substring);

    }
}
