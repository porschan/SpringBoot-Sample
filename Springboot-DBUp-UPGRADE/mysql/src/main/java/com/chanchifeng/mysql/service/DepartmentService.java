package com.chanchifeng.mysql.service;

import com.chanchifeng.mysql.entity.Department;
import com.chanchifeng.mysql.model.DepartmentQo;
import com.chanchifeng.mysql.redis.DepartmentRedis;
import com.chanchifeng.mysql.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {

        @Autowired
//    @Resource
    private DepartmentRepository departmentRepository;

        @Autowired
//    @Resource
    private DepartmentRedis departmentRedis;

    @Cacheable(value = "mysql:findById:deparment", keyGenerator = "simpleKey")
    public Department findById(Long id) {
        return departmentRepository.findById(id).get();
    }

    @CachePut(value = "mysql:findById:deparment", keyGenerator = "objectId")
    public Department create(Department deparment) {
        return departmentRepository.save(deparment);
    }

    @CachePut(value = "mysql:findById:deparment", keyGenerator = "objectId")
    public Department update(Department role) {
        return departmentRepository.save(role);
    }

    @CacheEvict(value = "mysql:findById:deparment", keyGenerator = "simpleKey")
    public void delete(Long id) {
        departmentRepository.deleteById(id);
    }

    public List<Department> findAll(){
        System.out.println("findAll 4 department");
        List<Department> deparments = departmentRedis.getList("mysql:findAll:deparment");
        if(deparments == null) {
            deparments = departmentRepository.findAll();
            if(deparments != null) {
                departmentRedis.add("mysql:findAll:deparment", 5L, deparments);
            }
        }
        return deparments;
    }

    public Page<Department> findPage(DepartmentQo deparmentQo){

        Pageable pageable = PageRequest.of(deparmentQo.getPage(), deparmentQo.getSize());
        Page<Department> all = departmentRepository.findAll((Specification<Department>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (deparmentQo.getName() != null && !deparmentQo.getName().equals("")) {
                predicates.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + deparmentQo.getName() + "%"));
            }

            Predicate[] pre = new Predicate[predicates.size()];

            query.where(predicates.toArray(pre));
            return criteriaBuilder.and(predicates.toArray(pre));
        }, pageable);
        return all;
    }
}
