package cn.yang719.SpringWeb.service.Impl;

import cn.yang719.SpringWeb.dto.Result;
import cn.yang719.SpringWeb.entity.Admin;
import cn.yang719.SpringWeb.mapper.AdminMapper;
import cn.yang719.SpringWeb.service.AdminService;
import cn.yang719.utils.PasswordTools.PasswordToMD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper mapper;

    @Override
    public Result add(Admin admin) {
        return null;
    }

    @Override
    public Result login(Admin admin) {
        //判断输入是否为空
        if (!Objects.nonNull(admin))
            return Result.fail().msg("输入为空").code(-1);
        if (!Objects.nonNull(admin.getEmail()) || !Objects.nonNull(admin.getPassword()))
            return Result.fail().msg("邮箱或密码为空").code(-1);
        //开始根据邮箱搜寻数据
        Admin admin1 = mapper.selectByEmail(admin.getEmail());
        if (!Objects.nonNull(admin1))
            return Result.fail().msg("邮箱输入用户不存在").code(-1);
        //开始处理密码业务
        String pwdInput = PasswordToMD5.pwdSalt2MD5(admin.getPassword(),admin1.getSalt());
        if (!Objects.equals(pwdInput,admin1.getPassword()))
            return Result.fail().msg("密码输入错误");
        //判断全部完毕了就可以返回查到的管理员数据了
        return Result.ok().msg("登陆成功").data(admin1);
    }

    @Override
    public Result remove(Admin admin) {
        return null;
    }

    @Override
    public Result update(Admin admin) {
        return null;
    }
}
