package com.chanchifeng.data.service.impl;

import com.chanchifeng.data.common.vo.PageVO;
import com.chanchifeng.data.domain.Book;
import com.chanchifeng.data.repo.BookRepo;
import com.chanchifeng.data.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepo bookRepo;

    @Override
    public PageVO<Book> finlAllPage(int page, int size) {
        Sort sort = new Sort(Sort.Direction.DESC,"bookCreateTime");
        Page<Book> all = bookRepo.findAll(PageRequest.of(page, size,sort));
        List<Book> content = all.getContent();
        return new PageVO<Book>(all.getNumber(),all.getTotalPages(),all.getContent(),all.getTotalElements());
    }
}
