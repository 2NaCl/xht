package com.ht.oa.system.service;

import com.ht.oa.model.domain.system.Email;
import com.ht.oa.model.domain.system.Emailed;
import com.ht.oa.system.dao.EmailDao;
import com.ht.oa.system.dao.EmailedDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@SuppressWarnings("unchecked")
@Service
@Transactional
public class EmailedService {

    @Autowired
    private EmailDao emailDao;

    @Autowired
    private EmailedDao emailedDao;

    /**
     * 批准,批准后，邮件消失，回显到客户端，反馈情况
     */
    public void agree(String id) {

        Email byId = emailDao.findById(id).get();
        Emailed emailed = new Emailed();
        emailed.setCondition("同意");
        emailed.setDdate(byId.getDdate());
        emailed.setDept(byId.getDept());
        emailed.setDname(byId.getDname());
        emailedDao.save(emailed);
        emailDao.delete(byId);
    }

    /**
     * 否定
     */

    public void refuse(String id) {
        Email byId = emailDao.findById(id).get();
        Emailed emailed = new Emailed();
        emailed.setCondition("不同意");
        emailed.setDdate(byId.getDdate());
        emailed.setDept(byId.getDept());
        emailed.setDname(byId.getDname());
        emailedDao.save(emailed);
        emailDao.delete(byId);
    }

    /**
     * 闲置
     */

    public void free(String id) {
        Email byId = emailDao.findById(id).get();
        Emailed emailed = new Emailed();
        emailed.setCondition("被闲置了");
        emailed.setDdate(byId.getDdate());
        emailed.setDept(byId.getDept());
        emailed.setDname(byId.getDname());
        emailedDao.save(emailed);
        emailDao.delete(byId);
    }

}
