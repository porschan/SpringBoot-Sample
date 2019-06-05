package com.chanchifeng.web.controller;

import com.chanchifeng.data.common.vo.PageVO;
import com.chanchifeng.data.common.vo.ResultVO;
import com.chanchifeng.data.domain.Student;
import com.chanchifeng.data.repo.StudentRepo;
import com.chanchifeng.data.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Objects;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired StudentService studentService;

    @Autowired StudentRepo studentRepo;

    @GetMapping("/home")
    public String home(){
        return "neo4j/student/index";
    }

    @GetMapping("/list")
    @ResponseBody
    public PageVO<Student> list(@RequestParam(value = "page", defaultValue = "1") int page,@RequestParam(value = "limit",defaultValue = "10") int limit){

        PageVO<Student> studentPageVO = studentService.finlAllPage(page - 1, limit);
        return studentPageVO;
    }

    @PostMapping("/subStudent")
    @ResponseBody
    public ResultVO subStudent(Student student){
        if(Objects.isNull(student)){
            return new ResultVO(false,"Object is null");
        }

        student.setStudentAge((int) (Math.random() * 100));
        student.setStudentBorn(new Date());
        studentRepo.save(student);
        return new ResultVO(true,"ok");
    }

    @PostMapping("/delStudent")
    @ResponseBody
    public ResultVO delStudent(@RequestParam(value = "studentId",defaultValue = "-1") Long studentId){
        if(studentId != null && studentId >0){
            studentRepo.deleteById(studentId);
            return new ResultVO(true,"ok");
        }else{
            return new ResultVO(false,"studentId is problem");
        }
    }

}


/* todo https://blog.csdn.net/mbshqqb/article/details/79551860 */