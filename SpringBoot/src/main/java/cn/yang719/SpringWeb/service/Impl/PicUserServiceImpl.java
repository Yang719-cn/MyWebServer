package cn.yang719.SpringWeb.service.Impl;

import cn.yang719.SpringWeb.dto.Result;
import cn.yang719.SpringWeb.entity.Picuser;
import cn.yang719.SpringWeb.entity.User;
import cn.yang719.SpringWeb.mapper.PicuserMapper;
import cn.yang719.SpringWeb.service.PicUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PicUserServiceImpl implements PicUserService {

    @Autowired
    PicuserMapper mapper;

    @Override
    public Result ifPicUser(User user) {
        if (!Objects.nonNull(user))
            return Result.fail().msg("输入为空");
        Picuser picuser = mapper.selectByUid(user.getId());
        if (Objects.nonNull(picuser))
            return Result.ok().data(true);
        return Result.fail().data(false);
    }
}
