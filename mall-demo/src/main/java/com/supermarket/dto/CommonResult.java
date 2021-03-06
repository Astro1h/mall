package com.supermarket.dto;

import com.github.pagehelper.PageInfo;
import lombok.Data;
import lombok.ToString;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0 created by chenyichang_fh on 2019/3/25 14:34
 */
@Data
@ToString
public class CommonResult {
    //成功
    public static final int success = 200;
    //失败
    public static final int failed = 500;
    //参数校验失败
    public static final int validate_fail = 404;
    //未认证
    public static final int unauthorize = 401;
    //未授权
    public static final int forbidden = 403;

    private int code;

    private String message;

    private Object data;

    /**
     * 成功返回
     * @param data
     * @return
     */
    public CommonResult success(Object data) {
        this.code = success;
        this.message = "成功";
        this.data = data;
        return this;
    }

    /**
     * 失败返回
     * @return
     */
    public CommonResult failed() {
        this.code = failed;
        this.message = "失败";
        return this;
    }

    /**
     * 参数校验失败返回
     * @param message
     * @return
     */
    public CommonResult validateFailed(String message) {

        this.code = validate_fail;
        this.data = message;
        return this;
    }

    /**
     * 参数校验失败返回
     * @param result 错误信息
     * @return
     */
    public CommonResult validateFailed(BindingResult result) {
        validateFailed(result.getFieldError().getDefaultMessage());
        return this;
    }

    /**
     * 未登录返回
     * @param message
     * @return
     */
    public CommonResult unauthorize(String message) {

        this.code = unauthorize;
        this.message = "暂未登陆或者token已经过期";
        this.data = message;
        return this;
    }

    /**
     * 无权限返回
     * @param message
     * @return
     */
    public CommonResult forbidden(String message) {

        this.code = forbidden;
        this.message = "无权限";
        this.data = message;
        return this;
    }

    /**
     * 返回分页成功的数据
     * @param data
     * @return
     */
    public CommonResult pageSuccess(List data) {
        PageInfo pageInfo = new PageInfo(data);
        Map<String, Object> map = new HashMap<>();
        map.put("pageSize", pageInfo.getPageSize());
        map.put("totalPage", pageInfo.getPages());
        map.put("total", pageInfo.getTotal());
        map.put("pageNum", pageInfo.getPageNum());
        map.put("list", pageInfo.getList());

        this.code = success;
        this.message = "成功";
        this.data = map;
        return this;


    }
}
