package cn.yang719.SpringWeb.controller;

import cn.yang719.SpringWeb.dto.Result;
import cn.yang719.utils.Log;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin //允许跨源访问
@RestController
public class HomeController {

    @RequestMapping(value = "")
    public Result home(){
        return testLink();
    }

    @RequestMapping(value = "/testLink")
    public Result testLink(){
        Log.s("HomeController","请求是否能够连接到后端");
        return Result.ok().msg("后端链接成功");
    }
}
