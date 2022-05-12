package com.ad.core;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.List;

/**
 * @author CoderYoung
 */
public class BizController<S extends IService<M>, M> extends BaseController {

    @Autowired
    protected S baseService;

    @ApiOperation(value = "新增或修改")
    @PostMapping(path = "saveOrUpdate")
    public BaseResult<Integer> saveOrUpdate(@RequestBody M entity) {
        boolean save = baseService.saveOrUpdate(entity);
        return buildResult(save ? 1 : 0);
    }

    @ApiOperation(value = "删除")
    @PostMapping(path = "delete")
    public BaseResult<Integer> delete(@RequestParam String id) {
        boolean del = baseService.removeById(id);
        return buildResult(del ? 1 : 0);
    }

    @ApiOperation(value = "查询所有数据")
    @PostMapping(path = "selectAll")
    public PageResult<M> selectAll() {
        List<M> list = baseService.list();
        return buildResult(list);
    }

    @ApiOperation(value = "分页查询所有数据")
    @PostMapping(path = "selectAllByPage")
    public PageResult<M> selectAllByPage(@RequestParam Integer currentPage, @RequestParam Integer pageSize) {
        return buildResult(baseService.page(new Page<>(currentPage, pageSize)));
    }

}
