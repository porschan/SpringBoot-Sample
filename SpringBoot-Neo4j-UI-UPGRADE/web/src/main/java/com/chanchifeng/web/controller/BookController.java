package com.chanchifeng.web.controller;

import com.chanchifeng.data.common.vo.PageVO;
import com.chanchifeng.data.common.vo.ResultVO;
import com.chanchifeng.data.domain.Book;
import com.chanchifeng.data.repo.BookRepo;
import com.chanchifeng.data.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Objects;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    BookRepo bookRepo;

    @GetMapping("/list")
    @ResponseBody
    public PageVO<Book> list(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "limit",defaultValue = "10") int limit){

        PageVO<Book> studentPageVO = bookService.finlAllPage(page - 1, limit);
        return studentPageVO;
    }

    @PostMapping("/subBook")
    @ResponseBody
    public ResultVO subBook(Book book){
        if(Objects.isNull(book)){
            return new ResultVO(false,"Object is null");
        }

        book.setBookCreateTime(new Date());
        bookRepo.save(book);
        return new ResultVO(true,"ok");
    }

    @PostMapping("/delBook")
    @ResponseBody
    public ResultVO delBook(@RequestParam(value = "bookId",defaultValue = "-1") Long bookId){
        if(bookId != null && bookId >0){
            bookRepo.deleteById(bookId);
            return new ResultVO(true,"ok");
        }else{
            return new ResultVO(false,"studentId is problem");
        }
    }
}
