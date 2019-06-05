package com.chanchifeng.data.repo;

import com.chanchifeng.data.domain.Book;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends PagingAndSortingRepository<Book,Long> {
}
