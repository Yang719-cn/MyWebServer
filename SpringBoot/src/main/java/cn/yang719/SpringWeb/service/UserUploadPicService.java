package cn.yang719.SpringWeb.service;

import cn.yang719.SpringWeb.dto.Result;
import cn.yang719.SpringWeb.entity.User;
import cn.yang719.SpringWeb.entity.Useruploadpic;

import java.util.List;

public interface UserUploadPicService {

    Result selectUploadedInfoByUserId(User user);

    Result addLog(Useruploadpic log);
}
