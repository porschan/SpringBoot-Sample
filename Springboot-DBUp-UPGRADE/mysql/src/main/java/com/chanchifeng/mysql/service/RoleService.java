package com.chanchifeng.mysql.service;

import com.chanchifeng.dbexpand.jpa.parameter.LinkEnum;
import com.chanchifeng.dbexpand.jpa.parameter.PredicateBuilder;
import com.chanchifeng.mysql.entity.Role;
import com.chanchifeng.mysql.entity.User;
import com.chanchifeng.mysql.model.RoleQo;
import com.chanchifeng.mysql.redis.RoleRedis;
import com.chanchifeng.mysql.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {

    //    @Autowired
    @Resource
    private RoleRepository roleRepository;

    //    @Autowired
    @Resource
    private RoleRedis roleRedis;

    @Cacheable(value = "mysql:findById:role", keyGenerator = "simpleKey")
    public Role findById(Long id) {
        return roleRepository.findById(id).get();
    }

    @CachePut(value = "mysql:findById:role", keyGenerator = "objectId")
    public Role create(Role role) {
        return roleRepository.save(role);
    }

    @CachePut(value = "mysql:findById:role", keyGenerator = "objectId")
    public Role update(Role role) {
        return roleRepository.save(role);
    }

    @CacheEvict(value = "mysql:findById:role", keyGenerator = "simpleKey")
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

    public List<Role> findAll(){
        List<Role> roleList = roleRedis.getList("mysql:findAll:role");
        if(roleList == null) {
            roleList = roleRepository.findAll();
            if(roleList != null) {
                roleRedis.add("mysql:findAll:role", 5L, roleList);
            }
        }
        return roleList;
    }

    public Page<Role> findPage(RoleQo roleQo){
//        Pageable pageable = new PageRequest(roleQo.getPage(), roleQo.getSize(), new Sort(Sort.Direction.ASC, "id"));
//
//        PredicateBuilder pb  = new PredicateBuilder();

//        if (!StringUtils.isEmpty(roleQo.getName())) {
//            pb.add("name","%" + roleQo.getName() + "%", LinkEnum.LIKE);
//        }

        Pageable pageable = PageRequest.of(roleQo.getPage(), roleQo.getSize());

        Page<Role> all = roleRepository.findAll((Specification<Role>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (roleQo.getName() != null && !roleQo.getName().equals("")) {
                predicates.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + roleQo.getName() + "%"));
            }

            Predicate[] pre = new Predicate[predicates.size()];

            query.where(predicates.toArray(pre));
            return criteriaBuilder.and(predicates.toArray(pre));
        }, pageable);
        return all;

    }
}
