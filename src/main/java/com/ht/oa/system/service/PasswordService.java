package com.ht.oa.system.service;

import com.ht.oa.model.domain.system.Password;
import com.ht.oa.system.dao.PasswordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PasswordService {

    @Autowired
    private PasswordDao passwordDao;

    /**
     * 根据id查询到密码
     */
    public String findById(String id) {
        Password password = passwordDao.findById(id).get();
        return password.getPwd();
    }
}
