package dwl;

import dwl.model.enums.FeatureEnum;
import dwl.utils.EnumUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author wenlong.ding
 * @date 2020/11/14 14:11
 */
@Slf4j
public class ParseUtilTest {
    @Test
    public void beanToXml(){
        FeatureEnum one = EnumUtil.findOne(FeatureEnum.class, 1);
        System.out.println(one);

    }
}
