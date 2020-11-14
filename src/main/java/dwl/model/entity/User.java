package dwl.model.entity;

import lombok.Data;

/**
 * @author wenlong.ding
 * @date 2020/11/14 16:54
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
