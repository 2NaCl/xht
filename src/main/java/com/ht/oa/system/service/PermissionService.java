package com.ht.oa.system.service;


import com.ht.oa.common.utils.BeanMapUtils;
import com.ht.oa.common.utils.IdWorker;
import com.ht.oa.common.utils.PermissionConstants;
import com.ht.oa.model.domain.system.Permission;
import com.ht.oa.system.dao.PermissionDao;
import com.ht.oa.system.dao.RoleDao;
import com.ht.oa.system.dao.UserDao;
import org.apache.kafka.clients.consumer.CommitFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

@Service
@Transactional
public class PermissionService {

    @Autowired
    private PermissionDao permissionDao;


    @Autowired
    private IdWorker idWorker;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;


    /**
     * 添加权限
     */
    public void addPermission(Map map) throws Exception {
        Permission permission = BeanMapUtils.mapToBean(map, Permission.class);
        permission.setId(idWorker.nextId() + "");
        permissionDao.save(permission);
    }

    /**
     * 删除权限
     */
    public void deletePermission(String id) {
        permissionDao.deleteById(id);
    }




    /**
     * 查看所有权限
     */
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }


    /**
     * 修改权限
     */
    public Permission updatePermission(Permission permission) throws Exception {
        Permission permission1 = permissionDao.findById(permission.getId()).get();
        permission1.setDescription(permission.getDescription());
        permission1.setCode(permission.getCode());
        permission1.setName(permission.getName());
        permission1.setType(permission.getType());
        permissionDao.save(permission1);
        return permission1;
    }

    /**
     * 按照条件查询全部
     */
    public List<Permission> findAll(Map<String, Object> map) {
        //1.需要查询条件
        Specification<Permission> spec = new Specification<Permission>() {
            /**
             * 动态拼接查询条件
             * @return
             */
            public Predicate toPredicate(Root<Permission> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                //根据父id查询
                if(!StringUtils.isEmpty(map.get("code"))) {
                    list.add(criteriaBuilder.equal(root.get("code").as(String.class),(String)map.get("code")));
                }

                //根据类型 type
                if(!StringUtils.isEmpty(map.get("type"))) {
                    String ty = (String) map.get("type");
                    CriteriaBuilder.In<Object> in = criteriaBuilder.in(root.get("type"));
                    if("0".equals(ty)) {
                        in.value(1).value(2);
                    }else{
                        in.value(Integer.parseInt(ty));
                    }
                    list.add(in);
                }
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        return permissionDao.findAll(spec);
    }




    }





