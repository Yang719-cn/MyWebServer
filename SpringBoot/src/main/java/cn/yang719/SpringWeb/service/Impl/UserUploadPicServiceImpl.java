package cn.yang719.SpringWeb.service.Impl;

import cn.yang719.SpringWeb.dto.Result;
import cn.yang719.SpringWeb.entity.User;
import cn.yang719.SpringWeb.entity.Useruploadpic;
import cn.yang719.SpringWeb.mapper.UseruploadpicMapper;
import cn.yang719.SpringWeb.service.UserUploadPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserUploadPicServiceImpl implements UserUploadPicService {

    @Autowired
    UseruploadpicMapper mapper;

    @Override
    public Result selectUploadedInfoByUserId(User user) {

        if (user == null)
            return Result.fail().msg("输入用户数据为空").code(-1);
        if (user.getId() < 1)
            return Result.fail().msg("输入用户id有误").code(0);

        List<Useruploadpic> list = mapper.selectWithPicInfoByUserId(user.getId());

        if (list == null)
            return Result.fail().msg("获取数据失败，获取不到数据").code(-1);
        if (list.size() < 1)
            return Result.fail().msg("没有获取到任何数据").code(0);

        return Result.ok().msg("获取到"+list.size()+"条数据").data(list);
    }

    @Override
    public Result addLog(Useruploadpic log) {
        if (log == null)
            return Result.fail().msg("输入的记录为空");

        //准备插入
        Integer code = mapper.insert(log);
        System.out.println(code);

        if (code > 0)
            return Result.ok();
        return Result.fail();
    }
}
