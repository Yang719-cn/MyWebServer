package cn.yang719.SpringWeb.dto;

/**
 * @author Yang719 <br/>
 * @version code in Java17
 * 服务器后端统一业务层返回结果对象
 */
public class Result {

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
     * 返回成功的数据
     */
    private Object data;


    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public Object getData() {
        return data;
    }

    /**
     * 构造器私有化，防止外界任意创建对象
     */
    private Result(){}

    /**
     * 设置需要返回的信息
     * @param msg 需要返回的数据
     * @return 自己调整好返回数据的对象
     */
    public Result msg(String msg){
        this.msg = msg;
        return this;
    }

    /**
     * 设置返回是否成功
     * @param success 返回是否成功的布尔值
     * @return 自己调整好是否成功的布尔值的对象
     */
    public Result success(boolean success){
        this.success = success;
        return this;
    }

    /**
     * 设置返回状态码
     * @param code 要返回的状态码
     * @return 自己调整好返回状态码的对象
     */
    public Result code(int code){
        this.code = code;
        return this;
    }

    /**
     * 设置返回数据对象
     * @param data 要返回的返回数据对象
     * @return 自己调整好返回数据对象的对象
     */
    public Result data(Object data){
        this.data = data;
        return this;
    }

    /**
     * 返回表示失败的返回对象
     * @return 表示失败的返回对象
     */
    public static Result fail(){
        Result r = new Result();
        r.code = 0;
        r.msg = "执行失败";
        r.success = false;
        return r;
    }

    /**
     * 返回表示成功的返回对象
     * @return 表示成功的返回对象
     */
    public static Result ok(){
        Result r = new Result();
        r.code = 1;
        r.msg = "执行成功";
        r.success = true;
        return r;
    }

    @Override
    public String toString() {
        if (data == null)
            return "Result{" +
                    "code=" + code +
                    ", msg='" + msg + '\'' +
                    ", success=" + success +
                    ", data=" + null +
                    '}';
        else
            return "Result{" +
                    "code=" + code +
                    ", msg='" + msg + '\'' +
                    ", success=" + success +
                    ", data=" + data +
                    '}';
    }
}
