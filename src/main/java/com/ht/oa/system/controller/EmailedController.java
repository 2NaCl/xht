package com.ht.oa.system.controller;


import com.ht.oa.common.entity.Result;
import com.ht.oa.common.entity.ResultCode;
import com.ht.oa.system.service.EmailService;
import com.ht.oa.system.service.EmailedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/email")
public class EmailedController {

    @Autowired
    private EmailedService emailedService;

    /**
     * 同意
     */
    @GetMapping("/agree")
    public Result agree(String id) {
        emailedService.agree(id);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 否定
     */
    @GetMapping("/refuse")
    public Result refuse(String id) {
        emailedService.refuse(id);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 闲置
     */
    @GetMapping("/free")
    public Result free(String id) {
        emailedService.free(id);
        return new Result(ResultCode.SUCCESS);
    }

}
