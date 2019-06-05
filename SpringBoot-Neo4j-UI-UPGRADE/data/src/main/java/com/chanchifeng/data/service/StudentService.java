package com.chanchifeng.data.service;

import com.chanchifeng.data.common.vo.PageVO;
import com.chanchifeng.data.domain.Student;

public interface StudentService {

    PageVO<Student> finlAllPage(int page, int size);

}
