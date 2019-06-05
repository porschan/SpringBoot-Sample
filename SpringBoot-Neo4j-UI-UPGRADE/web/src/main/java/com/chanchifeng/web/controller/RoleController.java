package com.chanchifeng.web.controller;

import com.chanchifeng.data.common.vo.AllDataVO;
import com.chanchifeng.data.common.vo.PageVO;
import com.chanchifeng.data.common.vo.ResultVO;
import com.chanchifeng.data.domain.Book;
import com.chanchifeng.data.domain.Role;
import com.chanchifeng.data.domain.Student;
import com.chanchifeng.data.repo.BookRepo;
import com.chanchifeng.data.repo.RoleRepo;
import com.chanchifeng.data.repo.StudentRepo;
import com.chanchifeng.data.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    RoleService roleService;

    @Autowired
    BookRepo bookRepo;

    @Autowired
    StudentRepo studentRepo;

    @GetMapping("/list")
    @ResponseBody
    public PageVO<Role> list() {
        return roleService.finlAllPage();
    }

    @PostMapping("/allData")
    @ResponseBody
    public AllDataVO allData(){
        Iterable<Student> all = studentRepo.findAll();
        Iterable<Book> all1 = bookRepo.findAll();
        return new AllDataVO(all,all1);
    }

    @PostMapping("/bing")
    @ResponseBody
    public ResultVO bing(@RequestParam(value = "bookId",defaultValue = "-1") Long bookId,@RequestParam(value = "studentId",defaultValue = "-1") Long studentId){
        if(studentId != null && studentId >0 && bookId != null && bookId > 0) {
            Optional<Book> byId = bookRepo.findById(bookId);
            Optional<Student> byId1 = studentRepo.findById(studentId);
            if (byId != null && byId1 != null) {
                Book book = byId.get();
                Student student = byId1.get();
                if(book != null && student != null){
                    book.addRole(student,"借出");
                    bookRepo.save(book);
                    return new ResultVO(true, "ok");
                }
            }
        }
        return new ResultVO(false,"bookId & studentId is problem");
    }

}
