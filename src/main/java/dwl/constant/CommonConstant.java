package dwl.constant;

import dwl.model.entity.UserInfoDto;

/**
 * @author wenlong.ding
 * @date 2020/11/17 9:56
 */
public interface CommonConstant {
    int ROOT_PROCESS_TREE_CODE = 0;

    ThreadLocal<UserInfoDto> GLOBAL_USER_INFO = new ThreadLocal<>();

    String RN = "\r\n";

}
