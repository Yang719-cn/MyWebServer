package cn.yang719.SpringWeb.dto;


/**
 * 统一DTO处理 <br/>
 * 接触了RuoYi框架以及看了螃蟹哥发现老的Result不完善从而搞得实验性DTO <br/>
 * @author Yang
 * @version 0.1 小试牛刀
 * @param <T> 规定数据类
 */
public class R<T> {

    /**
     *状态码
     */
    private Integer code;

    /**
     * 返回文本消息
     */
    private String msg;

    /**
     * 返回是否执行成功
     */
    private Boolean success;

    /**
     *
     */
    private T data;

    private Result result;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Boolean isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }

    public void data(T data){
        this.data = data;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * 构造器私有化，防止外界任意创建对象
     */
    private R(){}
    private <T> R<T> R(T data,Boolean success,String msg,Integer code){
        R<T> r = new R<>();
        return r;
    }

    public R success(boolean success){
        this.success = success;
        return this;
    }


}
