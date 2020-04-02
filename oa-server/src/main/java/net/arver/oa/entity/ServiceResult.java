package net.arver.oa.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 后端返回结果对象.
 * @param <T> 数据类型
 */
@Data
public class ServiceResult<T> implements Serializable {

    /**
     * 是否成功.
     */
    private boolean succeed = true;

    /**
     * 信息.
     */
    private String msg;

    /**
     * code.
     */
    private int code;

    /**
     * 数据.
     */
    private T data;

    /**
     * 构造函数.
     */
    public ServiceResult() {}

    /**
     * 构造函数.
     */
    public ServiceResult(final T data) {
        this.data = data;
    }

    /**
     * 构造函数.
     */
    public ServiceResult(final boolean succeed, final String msg) {
        this.succeed = succeed;
        this.msg = msg;
    }

    /**
     * 构造函数.
     */
    public ServiceResult(final boolean succeed, final T data, final String msg) {
        this.succeed = succeed;
        this.data = data;
        this.msg = msg;
    }
}
