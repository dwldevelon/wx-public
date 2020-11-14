package dwl.model.jvhe;

import lombok.Data;

import java.util.List;

/**
 * @author wenlong.ding
 * @date 2020/11/14 17:26
 */
@Data
public class XiaoHuaResp {

    private List<Data> data;

    @lombok.Data
    public static class Data{
        private String content;
        private String hashId;
        private long unixtime;
        private String updatetime;
    }
}
