package com.chanchifeng.data.repo;

import com.chanchifeng.data.domain.Student;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends PagingAndSortingRepository<Student,Long> {
}
