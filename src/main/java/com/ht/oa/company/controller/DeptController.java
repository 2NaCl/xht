package com.ht.oa.company.controller;

import com.ht.oa.common.entity.Result;
import com.ht.oa.common.entity.ResultCode;
import com.ht.oa.company.service.DeptService;
import com.ht.oa.model.domain.company.Dept;
import com.ht.oa.model.domain.system.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RequestMapping("/dept")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 查询所有部门的情况
     */
    //TODO 增加一个实时显示人数的功能
    @GetMapping("/all")
    public Result findAll() {
        List<Dept> all = deptService.findAll();
        return new Result(ResultCode.SUCCESS, all);
    }

    /**
     * 查询自己同僚
     */
    @GetMapping("/{id}")
    public Result findByid(@PathVariable("id") String id) {
        List<User> byId = deptService.findById(id);
        return new Result(ResultCode.SUCCESS, byId);
    }

    /**
     * 添加部门
     */
    @PostMapping("/add")
    public Result add(@RequestBody Map map) {
        deptService.add(map);
        return new Result(ResultCode.SUCCESS, map);
    }

    /**
     * 删除部门
     * @param id
     * @return
     */
    @DeleteMapping("/del/{id}")
    public Result del(@PathVariable("id") String id) {
        deptService.deleteDept(id);
        return new Result(ResultCode.SUCCESS);
    }

}
