package cn.yang719.SpringWeb.service;

import cn.yang719.SpringWeb.dto.Result;
import cn.yang719.SpringWeb.entity.Regemail;

public interface RegService {

    Result addLog(Regemail regemail);

    /**
     * 默认邮箱验证码长度，默认为6
     */
    int defaultEmailCodeLen = 6;
    /**
     * 根据输入长度随机生成一个邮箱验证码
     * @param len 要生成验证码的长度，小于1默认为6位
     */
    String emailCodeFactory(int len);

    /**
     * 通过邮箱来搜寻最晚的注册记录
     */
    Result searchLatestLogByEmail(Regemail regemail);

    /**
     * 判断现在现在注册和获取验证码的时间是否过长
     */
    Result isTimeOut(Regemail regNow,Regemail latestLog);

    Result changeEmailCode(Regemail regNow,Regemail latestLog);
}
