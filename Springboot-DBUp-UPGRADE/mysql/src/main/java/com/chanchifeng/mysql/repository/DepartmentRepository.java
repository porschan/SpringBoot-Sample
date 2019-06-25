package com.chanchifeng.mysql.repository;

import com.chanchifeng.mysql.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
//public interface DepartmentRepository extends ExpandJpaRepository<Department, Long> {
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    /* START 带模糊搜索的分页功能 */

    Page<Department> findAll(Specification<Department> specification, Pageable pageable);

    /* END 带模糊搜索的分页功能 */

}