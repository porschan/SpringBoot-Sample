package com.chanchifeng.data.service;

import com.chanchifeng.data.common.vo.PageVO;
import com.chanchifeng.data.domain.Book;

public interface BookService {
    PageVO<Book> finlAllPage(int page, int size);
}
