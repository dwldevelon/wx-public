package dwl.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author wenlong.ding
 * @date 2020/11/26 14:54
 */
@Data
@TableName("news")
public class NewsDto {

    private Long id;
    private String uniquekey;
    private String title;
    private String date;
    private String category;
    private String author_name;
    private String url;
    private String thumbnailPicS;
    private String thumbnailPicS02;
    private String thumbnailPicS03;

}
