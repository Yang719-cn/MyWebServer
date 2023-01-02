package cn.yang719.SpringWeb.service.Impl;

import cn.yang719.SpringWeb.dto.Result;
import cn.yang719.SpringWeb.entity.Picture;
import cn.yang719.SpringWeb.entity.User;
import cn.yang719.SpringWeb.mapper.PictureMapper;
import cn.yang719.SpringWeb.service.PictureService;
import cn.yang719.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    PictureMapper mapper;

    @Override
    public Result list(User user) {
        List<Picture> list = mapper.selectByUserId(user.getId());
        Log.e("PictureServiceImpl",list.toString());
        return Result.ok().data(list);
    }


    @Override
    public Result pageList(User user,int pageNum,int pageSize) {
        if (pageNum < 1 || pageSize < 1 )
            return Result.fail().msg("获取页面信息输入出错");
        List<Picture> list = mapper.selectPageByUserId(user.getId(),(pageNum - 1) * pageSize,pageSize);
        return Result.ok().data(list);
    }

    @Override
    public Result add(Picture picture) {
        //判断输入是否为空
        if (picture == null)
            return Result.fail().msg("输入图片数据为空");
        if (picture.getPicurl() == null || picture.getPicurl() == "")
            return Result.fail().msg("输入图片路径为空");

        Integer code = mapper.insert(picture);
        Log.i("PictureServiceImpl","insert-code:"+code);

        if (code > 0)
            return Result.ok();
        return Result.fail();
    }

    @Override
    public Result searchByImgUrl(String url) {
        //判断输入是否为空
        if (url == null || url == "")
            return Result.fail().msg("输入图片路径为空");

        Picture picture = mapper.selectByPicUrl(url);

        if (picture == null || !Objects.nonNull(picture))
            return Result.fail().msg("未获取到相关数据");

        return Result.ok().data(picture);
    }

    @Override
    public Result delete(Picture picture) {
        //判断输入是否为空
        if (!Objects.nonNull(picture))
            return Result.fail().code(-1).msg("service没有接收到到picture");

        Integer code = mapper.deleteByPrimaryKey(picture.getId());
        if (code < 0)
            return Result.fail().code(-1).msg("没有删除成功");
        Log.s("PictureServiceImpl","图片删除成功");
        return Result.ok().code(1).msg("删除成功");
    }


}
