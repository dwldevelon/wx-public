package dwl.service.db.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dwl.mapper.UserInfoMapper;
import dwl.model.entity.UserInfoDto;
import dwl.service.db.UserInfoService;
import org.springframework.stereotype.Service;

/**
 * @author wenlong.ding
 * @date 2020/11/17 12:40
 */

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfoDto> implements UserInfoService {

    @Override
    public UserInfoDto findByOpenId(String openId) {
        QueryWrapper<UserInfoDto> wrapper = new QueryWrapper<>();
        wrapper.eq("open_id",openId);
        return getOne(wrapper);

    }
}
