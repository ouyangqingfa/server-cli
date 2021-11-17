package com.ad.core;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * @author CoderYoung
 */
public class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final int SUCCESS_CODE = 1;
    private final int DEFAULT_ERROR_CODE = -1;

    public <T> BaseResult<T> buildResult(T data) {
        return buildResult(SUCCESS_CODE, "", data);
    }

    public <T> BaseResult<T> buildResult(T data, String msg) {
        return buildResult(SUCCESS_CODE, msg, data);
    }

    public <T> BaseResult<T> buildResult(int code, String msg, T data) {
        BaseResult<T> response = new BaseResult<>();
        response.setCode(code);
        response.setMsg(msg);
        response.setData(data);
        return response;
    }

    public BaseResult buildError(int code) {
        return buildError(code, "未处理的错误");
    }

    public BaseResult buildError(String errMsg) {
        return buildError(DEFAULT_ERROR_CODE, errMsg);
    }

    public BaseResult buildError(int code, String errMsg) {
        BaseResult response = new BaseResult<>();
        response.setCode(code);
        response.setMsg(errMsg);
        return response;
    }

    public <T> PageResult<T> buildResult(IPage<T> page) {
        PageResult<T> response = new PageResult<>();
        response.setPageCount(page.getPages());
        response.setPageIndex(page.getCurrent());
        response.setPageSize(page.getSize());
        response.setTotalSize(page.getTotal());
        response.setData(page.getRecords());

        response.setCode(SUCCESS_CODE);
        return response;
    }


    public <T> PageResult<T> buildResult(Collection<T> data) {
        PageResult<T> response = new PageResult<>();
        response.setPageCount(1L);
        response.setPageIndex(1L);
        response.setPageSize(1L);
        response.setTotalSize((long) data.size());
        response.setData(data);

        response.setCode(SUCCESS_CODE);
        return response;
    }

    public <T> PageResult<T> buildResult(int code, String msg, Collection<T> list) {
        PageResult<T> response = new PageResult<>();
        response.setCode(code);
        response.setMsg(msg);
        if (list != null) {
            response.setPageCount(1L);
            response.setPageIndex(1L);
            response.setPageSize(1L);
            response.setTotalSize((long) list.size());
            response.setData(list);
        }
        return response;
    }
}
