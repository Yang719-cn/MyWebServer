package cn.yang719.SpringWeb.dto;


public class R<T> {
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
     *
     */
    private T data;

    private Result result;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }

    public void data(T data){
        this.data = data;
    }

    /**
     * 构造器私有化，防止外界任意创建对象
     */
    private R(){}

    public R success(boolean success){
        this.success = success;
        return this;
    }


}
