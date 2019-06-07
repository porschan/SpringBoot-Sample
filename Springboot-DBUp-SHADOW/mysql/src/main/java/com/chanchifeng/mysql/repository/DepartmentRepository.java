package com.chanchifeng.mysql.repository;

import com.chanchifeng.dbexpand.jpa.repository.ExpandJpaRepository;
import com.chanchifeng.mysql.entity.Department;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends ExpandJpaRepository<Department, Long> {
}
