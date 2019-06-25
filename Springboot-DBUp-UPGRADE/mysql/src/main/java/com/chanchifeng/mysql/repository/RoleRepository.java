package com.chanchifeng.mysql.repository;

import com.chanchifeng.mysql.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
//public interface RoleRepository extends ExpandJpaRepository<Role, Long> {
public interface RoleRepository extends JpaRepository<Role, Long> {

    /* START 带模糊搜索的分页功能 */

    Page<Role> findAll(Specification<Role> specification, Pageable pageable);

    /* END 带模糊搜索的分页功能 */

}
