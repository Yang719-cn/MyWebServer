package cn.yang719.SpringWeb.dto;

/**
 * 不同DTO之间的转换 <br/>
 * 和R以及RE同时计划，为了兼容不同DTO转换
 * @author Yang
 * @version 0.1 小试牛刀
 */
public class ResultTool {
    /**
     * DTO-基础普通类转固定成功枚举类
     * @param result
     * @return
     */
    public static RE Result2RE(Result result){
        RE re;
        if (result.isSuccess())
            re = RE.s;
        else
            re = RE.f;
        re.setCode(result.getCode());
        re.setData(result.getData());
        re.setMsg(result.getMsg());

        return re;
    }

    public static RE R2RE(R r){
        RE re;
        if (r.isSuccess())
            re = RE.s;
        else
            re = RE.f;
        re.setCode(r.getCode());
        re.setData(r.getData());
        re.setMsg(r.getMsg());

        return re;
    }
}
