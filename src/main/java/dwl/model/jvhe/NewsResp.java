package dwl.model.jvhe;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author wenlong.ding
 * @date 2020/11/26 14:50
 */

@Data
public class NewsResp {

    private String stat;
    private List<Data> data;

    @lombok.Data
    public static class Data{
        private String uniquekey;
        private String title;
        private String date;
        private String category;
        private String author_name;
        private String url;
        @JsonProperty("thumbnail_pic_s")
        private String thumbnailPicS;
        @JsonProperty("thumbnail_pic_s02")
        private String thumbnailPicS02;
        @JsonProperty("thumbnail_pic_s03")
        private String thumbnailPicS03;
    }


}
