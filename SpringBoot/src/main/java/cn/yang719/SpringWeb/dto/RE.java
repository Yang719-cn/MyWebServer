package cn.yang719.SpringWeb.dto;

/**
 * RE -> Result Enum <br/>
 * 这种写法可能会有大问题，先试一下 <br/>
 * @author Yang <br/>
 * @version 0.1 目的是为了固定成功值和快速识别是否成功（注：暂未实验 == 和 equal空指针情况）
 */
public enum RE {
    s(1,"成功",true,null),
    f(0,"失败",false,null);

    /**
     *状态码
     */
    private int code;

    /**
     * 返回文本消息
     */
    private String msg;

    /**
     * 返回是否执行成功
     */
    private  boolean success;

    /**
     * 返回数据
     */
    private Object data;

    private RE(int code, String msg, boolean success, Object data) {
        this.code = code;
        this.msg = msg;
        this.success = success;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }
}
