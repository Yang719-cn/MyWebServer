package cn.yang719.SpringWeb.service.Impl;

import cn.yang719.SpringWeb.dto.Result;
import cn.yang719.SpringWeb.entity.Regemail;
import cn.yang719.SpringWeb.entity.User;
import cn.yang719.SpringWeb.mapper.RegemailMapper;
import cn.yang719.SpringWeb.mapper.UserMapper;
import cn.yang719.SpringWeb.service.RegService;
import cn.yang719.utils.Log;
import cn.yang719.utils.SimpleTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class RegServiceImpl implements RegService {

    @Autowired
    RegemailMapper mapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public Result addLog(Regemail regemail) {
        Log.i("RegServiceImpl","进入addLog");
        //判断输入是否为空
        if (!Objects.nonNull(regemail))
            return Result.fail().msg("输入user为空").code(-1);
        //判断基本数据是否为空
        if (regemail.getEmail() == null || regemail.getCode() == null)
            return Result.fail().msg("获取验证码基本信息为空").code(-1);
        //判断是否是注册过的
        User user1 = userMapper.selectByEmail(regemail.getEmail());
        if (Objects.nonNull(user1))
            return Result.fail().msg("已经有此账号了").code(0);

        Log.c("RegServiceImpl","输入判断完毕，开始记录");
        //基本判断完毕，开始添加注册记录以方便记录和保存邮箱验证码
        regemail.setTime(new Date());
        Log.i("RegServiceImpl","regMail储存数据为=>"+regemail.toString());
        //插入并获取返回代码
        int code = mapper.insert(regemail);
        //通过返回代码来判断是否插入成功
        if (code > 0)
            return Result.ok().msg("添加注册记录、保存此用户注册用邮箱验证码成功");
        else {
            Log.e("RegServiceImpl","添加注册记录失败");
            return Result.fail().msg("添加注册记录失败");
        }


    }

    @Override
    public String emailCodeFactory(int len) {
        //判断验证码长度输入，如果为小于1就默认6位
        if (len < -1)
            len = RegService.defaultEmailCodeLen;
        //获取6位随机码作为验证码并返回
        return SimpleTools.getRandomString(len);
    }

    @Override
    public Result searchLatestLogByEmail(Regemail regemail) {
        Log.i("RegServiceImpl","进入searchLatestLogByEmail");
        //判断输入是否为空
        if (!Objects.nonNull(regemail))
            return Result.fail().msg("输入为空").code(-1);
        Log.i("RegServiceImpl",regemail.toString());
        //开始搜寻
        Regemail regemail1 = mapper.selectLatestByEmail(regemail);
        //开始验证搜寻结果
        if (Objects.nonNull(regemail1))
            //如果搜寻结果不为空返回此条数据
            return Result.ok().data(regemail1);

        return Result.fail().msg("并未搜寻到数据");
    }

    @Override
    public Result isTimeOut(Regemail regNow, Regemail latestLog) {
        Log.i("RegServiceImpl","进入isTimeOut");
        //判断输入是否为空
        if (!Objects.nonNull(regNow) || !Objects.nonNull(latestLog)){
            Log.e("RegServiceImpl","基本数据为空");
            return Result.fail().code(-1).msg("基本数据为空");
        }
        //判断两输入邮箱是否相同
        if (!Objects.equals(regNow.getEmail(), latestLog.getEmail())){
            Log.e("RegServiceImpl","你这两个邮箱不同我怎么判断");
            return Result.fail().code(0).msg("两次邮箱不同");
        }
        //获取间隔时间
        long rn = regNow.getTime().getTime();
        long ll = latestLog.getTime().getTime();
        long diff = rn - ll;
        //判断间隔时间是否过长
        long diffMinutes = diff / (60 * 1000) % 60;
        if (diffMinutes > 30)
            return Result.fail().code(0).msg("时间大于30min");
        //全部判断完毕返回
        return Result.ok().msg("时间在30min之间");
    }

    @Override
    public Result changeEmailCode(Regemail regNow,Regemail latestLog) {
        Log.i("RegServiceImpl","准备更改之前的验证码");
        //开始判断输入
        if (!Objects.nonNull(regNow) || !Objects.nonNull(latestLog))
            return Result.fail().msg("输入信息为空");
        if (!Objects.nonNull(latestLog.getEmail()))
            return Result.fail().msg("未输入要更改的注册记录");
        if (mapper.selectLatestByEmail(latestLog) == null)
            return Result.fail().msg("你要输入的东西都没有我怎么改").code(-1);
        if (regNow.getCode() == null)
            return Result.fail().msg("没有要更改的验证码呀");
        if (regNow.getCode().length() != RegService.defaultEmailCodeLen)
            return Result.fail().msg("要更改的验证码的长度不对呀");
        regNow.setId(latestLog.getId());
        //执行更新动作并返回验证码
        int code = mapper.updateByPrimaryKey(regNow);

        if (code > 0)
            return Result.ok().msg("更改成功");
        else
            return Result.fail().msg("更改失败");
    }


}
