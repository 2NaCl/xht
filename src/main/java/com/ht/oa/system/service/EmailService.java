package com.ht.oa.system.service;


import com.ht.oa.model.domain.system.Email;
import com.ht.oa.system.dao.EmailDao;
import io.netty.channel.EventLoopGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class EmailService {

    @Autowired
    private EmailDao emailDao;

    /**
     * 发送邮件,只有level2和level3可以看见信息，员工只能进行发送操作
     */
    public void insertEmail(Map map) {
        Email temp = new Email();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY:MM:DD hh:mm:ss");
        String format = sdf.format(new Date());
        temp.setDdate(format);
        temp.setDept((String) map.get("dept"));
        temp.setDname((String) map.get("dname"));
        temp.setMsg((String) map.get("msg"));
        emailDao.save(temp);
    }


    /**
     * 显示出所有的邮件请求
     */
    public List<Email> findAll() {
        List<Email> all = emailDao.findAll();
        return all;
    }




}
