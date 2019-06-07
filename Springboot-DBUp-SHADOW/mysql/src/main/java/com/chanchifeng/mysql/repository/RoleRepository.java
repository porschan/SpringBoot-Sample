package com.chanchifeng.mysql.repository;

import com.chanchifeng.dbexpand.jpa.repository.ExpandJpaRepository;
import com.chanchifeng.mysql.entity.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends ExpandJpaRepository<Role, Long> {

}
