package cn.yang719.SpringWeb.service.Impl;

import cn.yang719.SpringWeb.dto.Result;
import cn.yang719.SpringWeb.entity.User;
import cn.yang719.SpringWeb.mapper.UserMapper;
import cn.yang719.SpringWeb.service.UserService;
import cn.yang719.utils.Log;
import cn.yang719.utils.PasswordTools.PasswordToMD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author Yang719 <br/>
 * @version code in Java17 <br/>
 * 处理跟管理员账号的相关服务的实现层
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Result add(User user) {
        User user1 = new User();
        user1.setEmail(user.getEmail());
        if (userMapper.select(user1) == null || userMapper.select(user1).size() != 0)
            return Result.fail().msg("已经有此用户了").code(-1);

        //开始处理密码盐事务
        PasswordToMD5 password = new PasswordToMD5(user.getPassword());
        user.setSalt(password.getPasswordSalt());
        user.setPassword(password.getMD5Code());

        user.setDisable(0);

        //返回码
        int code = userMapper.insert(user);

        if (code == 1)
            return Result.ok().msg("用户添加成功");
        else
            return Result.fail().msg("用户添加失败");
    }

    @Override
    public Result login(User user) {
        Result res = Result.fail().msg("未输入正确用户登录信息");
        //判断是否输入密码
        if (user.getPassword() == null)
            return res;
        //判断是通过邮箱登录还是用户名登录
        if (user.getEmail() != null)
            res = loginByEmail(user.getEmail(),user.getPassword());
        if (user.getName() != null)
            res = Result.fail().msg("用户名登录还未完善，用邮箱注册吧");
        return res;
    }

    private Result loginByEmail(String email,String pwd){
        //根据email获取的用户信息对象
        User user1 = userMapper.selectByEmail(email);
        Log.i("UserServiceImpl","输入的邮箱为:"+email);
        //判断是否有此账户
        if (user1 == null)
            return Result.fail().msg("账号不存在").code(-1);
        Log.i("UserServiceImpl","账号存在");
        //判断此账户是否被禁用
        if (user1.getDisable()==1)
            return Result.fail().msg("账号不可用").code(-1);
        Log.c("UserServiceImpl","用户可用");
        Log.i("UserServiceImpl",user1.toString());
        //获取user1的盐值
        String salt = user1.getSalt();
        //根据输入的密码和user1的盐合二为一加密为MD5
        String md5pwd = PasswordToMD5.pwdSalt2MD5(pwd,salt);
        //判断用户输入的密码是否正确
        if (Objects.equals(user1.getPassword(),md5pwd))
            return Result.ok().code(1).msg("登录成功").data(user1);
        else
            return Result.fail().code(0).msg("密码错误");
    }

    @Override
    public Result remove(User user) {
        //判断所传过来的信息是否能进行删除
        //id是否为空或正确
        if (user.getId() == null || user.getId() < 1)
            return Result.fail().msg("id输入错误").code(-1);
        //你输入id的这位是否能够被删除
        if (userMapper.selectByPrimaryKey(user.getId()) == null)
            return Result.fail().msg("并没有此用户，所以你删不了").code(-1);

        //删除用户数据并返回状态码
        int code = userMapper.deleteByPrimaryKey(user.getId());
        if (code > 0){
            return Result.ok().msg("删除成功").code(1);
        }else
            return Result.fail().msg("删除失败").code(0);
    }

    @Override
    public Result disable(User user) {
        return null;
    }

    @Override
    public Result update(User user) {

        User user1 = userMapper.selectByPrimaryKey(user.getId());

        //判断所传过来的信息是否能进行更改
        if (user1 == null)
            return Result.fail().msg("并未有此用户").code(-1);
        if (user1.getDisable() == 1)
            return Result.fail().msg("此账户已被警用").code(-1);
        if (Objects.equals(user,user1))
            return Result.fail().msg("您并未做出任何修改").code(0);

        //密码变更了要重新设置盐值
        if (PasswordToMD5.pwdSalt2MD5(user.getPassword(),user.getSalt())
                != user1.getPassword()){
            PasswordToMD5 password = new PasswordToMD5(user.getPassword());
            user.setSalt(password.getPasswordSalt());
            user.setPassword(password.getMD5Code());
        }

        //更新用户数据并返回状态码
        int code = userMapper.updateByPrimaryKeySelective(user);
        if (code > 0){
            //重新查询更改后的数据并返回
            User userRes = userMapper.selectByPrimaryKey(user.getId());
            return Result.ok().msg("更新成功").code(1).data(userRes);
        }else
            return Result.fail().msg("更新失败").code(0);
    }

    @Override
    public Result searchById(Integer id) {
        if (id == null)
            return Result.fail().msg("所要搜索的用户id为空");

        User user1 = userMapper.selectById(id);
        if (user1 == null){
            Log.e("UserServiceImpl","userMapper.selectById失败");
        }

        return Result.ok().msg("").code(1).data(user1);
    }


}
