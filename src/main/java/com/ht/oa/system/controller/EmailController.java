package com.ht.oa.system.controller;


import com.ht.oa.common.entity.Result;
import com.ht.oa.common.entity.ResultCode;
import com.ht.oa.model.domain.system.Email;
import com.ht.oa.system.service.EmailService;
import com.ht.oa.system.service.EmailedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/email")
@CrossOrigin
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private EmailedService emailedService;

    /**
     * 发送邮件,只有level2和level3可以看见信息，员工只能进行发送操作
     */
    @PostMapping("/send")
    public Result sendEmail(@RequestBody Map map) {
        System.out.println(map.isEmpty());
        emailService.insertEmail(map);
        return new Result(ResultCode.SUCCESS);
    }


    /**
     * 显示出所有的邮件请求
     */
    @GetMapping("")
    public Result findAll() {
        List<Email> all = emailService.findAll();
        return new Result(ResultCode.SUCCESS, all);
    }


}
