package cn.yang719.SpringWeb.utils;

import cn.yang719.SpringWeb.dto.Result;
import cn.yang719.SpringWeb.entity.User;

public class UserTool {

    public static Result ifUserInputRight(User user){

        if (user == null)
            return Result.fail().msg("输入用户数据为空").code(-1);
        if (user.getId() < 1)
            return Result.fail().msg("输入用户id有误").code(0);

        return Result.ok().data(user);
    }
}
