package com.chanchifeng.data.service.impl;

import com.chanchifeng.data.common.vo.PageVO;
import com.chanchifeng.data.domain.Student;
import com.chanchifeng.data.repo.StudentRepo;
import com.chanchifeng.data.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepo studentRepo;

    @Override
    public PageVO<Student> finlAllPage(int page, int size) {
//        Sort.Order student =  new Sort.Order(Sort.Direction.DESC,"studentID");
        Sort sort = new Sort(Sort.Direction.DESC,"studentBorn");
        Page<Student> all = studentRepo.findAll(PageRequest.of(page, size,sort));
        return new PageVO<Student>(all.getNumber(),all.getTotalPages(),all.getContent(),all.getTotalElements());
    }
}
