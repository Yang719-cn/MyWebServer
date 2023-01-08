package cn.yang719.SpringWeb.controller;

import cn.yang719.SpringWeb.dto.Result;
import cn.yang719.SpringWeb.entity.Admin;
import cn.yang719.SpringWeb.entity.User;
import cn.yang719.SpringWeb.service.AdminService;
import cn.yang719.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin //允许跨源访问
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService service;

    @PostMapping(value = "/login")
    @ResponseBody
    public Result login(@RequestBody Admin admin){
        //显示出传入的值
        Log.i("AdminController","传入的admin=>"+admin.toString());
        //给service处理用户输入的admin
        Result res = service.login(admin);
        //如果返回有问题，显示出来
        if (!res.isSuccess()){
            Log.e("AdminController","service过来的错误"+res.getMsg());
            return res;
        }
        //如果没有问题，显现出查找成功的admin
        Admin admin1 = (Admin) res.getData();
        //不返回用户的密码盐和MD5密码
        admin1.setPassword(null);
        admin1.setSalt(null);
        //显示出来登录成功的admin
        Log.s("AdminController","登陆成功，查找的数据=》"+admin1.toString());
        return res;
    }

    @RequestMapping(value = "/setImgUser")
    public Result setImgUser(User user){

        Log.c("AdminController","setImgUser");

        return Result.fail();
    }
}
