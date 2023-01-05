package cn.yang719.SpringWeb.controller;

import cn.yang719.SpringWeb.dto.Result;
import cn.yang719.SpringWeb.email.SendEmail;
import cn.yang719.SpringWeb.entity.*;
import cn.yang719.SpringWeb.service.*;
import cn.yang719.SpringWeb.utils.file.image.ImageGet;
import cn.yang719.SpringWeb.utils.file.image.ImageSave;
import cn.yang719.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@CrossOrigin //允许跨源访问
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService service;
    @Autowired
    RegService regService;
    @Autowired
    UserUploadPicService uploadPicService;
    @Autowired
    PictureService pictureService;
    @Autowired
    PicUserService picUserService;
    @Autowired
    private HttpSession session;

    @RequestMapping(value = "/test")
    public Result test(User user){
        return uploadPicService.canUserUpload(user);
    }

    @RequestMapping(value = "/ifPicUser")
    public Result ifPicUser(User user){
        Log.i("UserController","进入ifPicUser");
        Log.i("UserController",user.toString());
        if (user == null)
            Result.fail().msg("输入为空").code(-1);
        return picUserService.ifPicUser(user);
    }

    @PostMapping (value = "/login")
    public @ResponseBody Result login(@RequestBody User user){
        Log.i("UserController","进入login");
        Log.i("UserController",user.toString());
        if (user.getEmail() == null && user.getName() == null)
            return Result.fail().msg("未输入邮箱或用户名").code(-1);
        if (user.getPassword() == null)
            return Result.fail().msg("未输入密码").code(0);
        return service.login(user);
    }


    @RequestMapping(value = "/getEmailCode")
    public Result getEmailCode(String regEmail){
        Log.i("UserController","进入getEmailCode");
        if (regEmail == null) {
            Log.e("getEmailCode", "关键信息缺失");
            return Result.fail().msg("关键信息缺失");
        }
        //添加要获取验证码的注册记录
        Regemail regemail = new Regemail();
        regemail.setTime(new Date());
        regemail.setCode(regService.emailCodeFactory(RegService.defaultEmailCodeLen));
        regemail.setEmail(regEmail);
        //看看此用户之前是否要获取验证码，如果之前申请过，就更改老的记录,没有就插入新的
        Regemail latestLog = (Regemail) regService.searchLatestLogByEmail(regemail).getData();
        //判断更改或插入动作是否成功
        Result ifOk;
        if (Objects.nonNull(latestLog))
            ifOk = regService.changeEmailCode(regemail,latestLog);
        else
            ifOk = regService.addLog(regemail);
        //判断返回值
        Log.c("RegServiceImpl","UserController",ifOk.toString());
        if (!ifOk.isSuccess())
            return ifOk;
        //log
        Log.i("regEmail",regemail.getEmail());
        Log.i("mailCode",regemail.getCode());
        //开始发送邮件
        String msg = "您这次的邮箱验证码为："+regemail.getCode();
        String title = "来自yang719.cn的邮箱注册验证码";
        SendEmail.sendEmailCode(regEmail,title,msg);

        return Result.ok();
    }

    @PostMapping(value = "/register")
    @ResponseBody
    public Result register(@RequestBody User user){
        Log.i("UserController","进入register");
        //判断输入是否为空
        if (!Objects.nonNull(user))
            return  Result.fail().msg("输入为空").code(-1);
        //传递时是通过盐暂时保存邮箱密码的，现在取出来
        String code = user.getSalt();
        Log.i("User",user.toString());
        Log.i("mailCode","用户输入的邮箱验证码"+code);
        //判断基本信息是否为空
        if (user.getEmail() == null)
            return Result.fail().msg("未输入邮箱").code(-1);
        if (user.getPassword() == null)
            return Result.fail().msg("未输入密码").code(0);
        if (user.getName() == null)
            return  Result.fail().msg("未输入用户名").code(-1);
        if (code == null)
            return  Result.fail().msg("未输入验证码").code(0);

        Log.s("register","注册信息无误");

        //开始获取最后一次注册记录以来获取验证码
        Regemail regemail = new Regemail();
        regemail.setEmail(user.getEmail());
        regemail.setTime(new Date());
        regemail.setCode(code);
        Regemail regemail1 = (Regemail) regService.searchLatestLogByEmail(regemail).getData();
        //判断是否有获取过验证码
        if (regemail1 == null)
            return Result.fail().msg("请先获取邮箱验证码");
        if (!regService.isTimeOut(regemail,regemail1).isSuccess())
            return Result.fail().msg("与你获取验证码的时间已经超过了30分钟");
        if (!Objects.equals(regemail.getCode(), regemail1.getCode()))
            return Result.fail().msg("输入的验证码不正确");

        return service.add(user);
    }

    @RequestMapping(value = "/update")
    public Result update(User user){
        Log.i("UserController","进入update");
        Log.i("User",user.toString());
        if (user.getId() == null || user.getId() < 1)
            return Result.fail().code(-1).msg("id为空");

        return service.update(user);
    }

    @RequestMapping(value = "/imgList")
    public Result imgList(User user){
        Log.i("UserController","调取imgList");
        Log.i("UserController",user.toString());
        Result result = uploadPicService.selectUploadedInfoByUserId(user);
        Log.i("UserController",result.toString());
        return result;
    }

    @RequestMapping(value = "/imgBase64")
    public String imgBase64(String imgUrl) throws IOException {
        Log.i("UserController","请求转换为base64编码的图片地址为："+imgUrl);
        return (String) ImageGet.getBase64(imgUrl).getData();
    }

    @RequestMapping(value = "/imgListPlus")
    public Result imgListPlus(User user) throws IOException {
        Log.i("UserController","调取imgList");
        Log.i("UserController",user.toString());
        List<Useruploadpic> picList = (List<Useruploadpic>) uploadPicService.selectUploadedInfoByUserId(user).getData();
        List<ImagePlus> imgList = new ArrayList<ImagePlus>();
        for (Useruploadpic pic:picList){
            ImagePlus img = new ImagePlus(pic.getPicture());
            img.setTime(pic.getTime());
            img.setBase64((String) ImageGet.getBase64(img.getPicurl()).getData());
            imgList.add(img);
        }
        Result result = Result.ok().data(imgList);
        Log.i("UserController",result.toString());
        return result;
    }

    @PostMapping(value = "/addImg")
    @ResponseBody
    public Result imgAdd(@RequestParam("file") MultipartFile file,
                         @RequestParam("uid") Integer id) throws IOException {
        //判断输入都是否为空
        if (file == null)
            return Result.fail().code(-1).msg("未获取文件数据");
        if (id == null)
            return Result.fail().code(-1).msg("未获取用户id");
        //根据获取到的id来获取用户信息以来获取用户信息
        User user = new User();
        Log.i("imgAdd","获取到的用户Id为"+id);
        user.setId(id);
        User user1 = (User) service.searchById(id).getData();
        //判断是否有这个用户
        if (user1 == null)
            return Result.fail().code(-1).msg("未获取用户数据");

        //判断用户每天上传的图片是否到达上限
        if (!uploadPicService.canUserUpload(user1).isSuccess())
            return Result.fail().code(0).msg("每天上传的图片已到达上限");

        Result addPicRes = ImageSave.saveAndInsertSQL(file,
                user1.getName(),
                "C:/mc/plugins/Images/",
                "/server/mc/plugins/Images/",
                pictureService);

        //判断图片信息保存好了吗
        if (!addPicRes.isSuccess())
            return Result.fail().msg("图片信息保存失败").code(-1);
        Picture pic = (Picture) addPicRes.getData();
        addPicRes = pictureService.searchByImgUrl(pic.getPicurl());
        if (!addPicRes.isSuccess())
            return Result.fail().msg("图片信息再次失败").code(0);

        //图片信息保存好了就开始保存上传信息了
        pic = (Picture) addPicRes.getData();
        Useruploadpic log = new Useruploadpic();
        log.setPicture(pic);
        log.setPid(pic.getId());
        log.setUid(user1.getId());
        log.setTime(new Date());
        Log.i("UserController",log.toString());
        Result uploadRes = uploadPicService.addLog(log);

        if (uploadRes.isSuccess())
            return Result.ok().msg("上传图片所有步骤已完成");
        else
            return Result.fail().msg("上传信息保存失败");
    }

    @PostMapping(value = "/imgDownload")
    public void imgDownload(HttpServletResponse response)throws IOException{

    }

    @PostMapping(value = "/deleteImg")
    @ResponseBody
    public Result imgDel(@RequestBody Picture picture){
        //判断输入是否为空
        if (!Objects.nonNull(picture))
            return Result.fail().code(-1).msg("没有接收到到picture");
        Log.i("UserController",picture.toString());

        //删文件
        File file = new File(picture.getPicurl());
        if (!file.exists()) {
            System.out.println("删除文件失败：" + picture.getPicurl() + "文件不存在");
            return Result.fail();
        } else {
            if (file.isFile())
                file.delete();
        }

        return pictureService.delete(picture);
    }
}
