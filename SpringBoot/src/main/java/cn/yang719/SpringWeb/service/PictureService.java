package cn.yang719.SpringWeb.service;

import cn.yang719.SpringWeb.dto.Result;
import cn.yang719.SpringWeb.entity.Picture;
import cn.yang719.SpringWeb.entity.User;

public interface PictureService {
    public Result list(User user);

    public Result add(Picture picture);

    public Result searchByImgUrl(String url);

    public Result delete(Picture picture);
}
