package dwl.service.db;

import com.baomidou.mybatisplus.extension.service.IService;
import dwl.model.entity.UserInfoDto;

/**
 * @author wenlong.ding
 * @date 2020/11/17 12:39
 */
public interface UserInfoService extends IService<UserInfoDto> {

    UserInfoDto findByOpenId(String openId);

}
