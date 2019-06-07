package com.chanchifeng.data;

import com.chanchifeng.data.domain.Book;
import com.chanchifeng.data.domain.Student;
import com.chanchifeng.data.repo.BookRepo;
import com.chanchifeng.data.repo.RoleRepo;
import com.chanchifeng.data.repo.StudentRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= StudentRepoTest.class)
@SpringBootApplication
//@EnableJpaRepositories("com.wjm.dao")
//@EntityScan("com.wjm.pojo")
@ComponentScan(basePackages="com.chanchifeng")
public class StudentRepoTest {


    @Resource StudentRepo studentRepo;

    @Resource BookRepo bookRepo;

    @Resource RoleRepo roleRepo;

    @Test
    public void addStudentTest(){
//        studentRepo.deleteAll();
//        bookRepo.deleteAll();

        Student student = new Student();
        student.setStudentName("porschan Book4");
        student.setStudentAge(18);
        student.setStudentBorn(new Date());
        System.out.println("!!" + student);

        Student save = studentRepo.save(student);
        System.out.println(save);

        Book book = new Book();
        book.setBookCreateTime(new Date());
        book.setBookName("pc Book4");

        bookRepo.save(book);
        book.addRole(student,"Role Book4");
        bookRepo.save(book);
    }

    @Test
    public void findStudentTest(){
        Optional<Student> byId = studentRepo.findById(7L);
        Student student = byId.get();
        System.out.println(student);

        Iterable<Student> all = studentRepo.findAll();
        all.forEach(System.out::println);
    }

//    @Autowired
//    StudentService studentService;
//
//    @Test
//    public void PagingAndSortingRepositoryTest(){
//        PageVO<Student> studentPageVO = studentService.finlAllPage(0, 10);
//        System.out.println(studentPageVO.getData().size());
//    }

    @Test
    public void findOne(){
//        Optional<Book> byId = bookRepo.findById(81L);
//        System.out.println(byId.get());
//
//        Optional<Student> byId1 = studentRepo.findById(7L);
//        System.out.println(byId1.get());

//        List<Role> likes = roleRepo.getLikes();
//        System.out.println(likes.size() + "!!!!!");
//        likes.forEach(System.out::println);
//
//        List<Object> likesObj = roleRepo.getLikesObj();
//        System.out.println(likesObj.size() + "!!!!!~~~");
//        likesObj.forEach(System.out::println);

//        Optional<Role> byId = roleRepo.findById(80L);
//        if(Objects.isNull(byId)){
//            System.out.println(byId.get());
//        }
    }

    @Test
    public void manyToOneTest(){
        Optional<Student> byId = studentRepo.findById(87L);
        Student student = byId.get();

        Book book = new Book();
        book.setBookName("manyToOne");
        book.setBookCreateTime(new Date());
        bookRepo.save(book);

        book.addRole(student,"manyRole");
        bookRepo.save(book);
    }

    @Test
    public void dropAll(){
        bookRepo.deleteAll();
        studentRepo.deleteAll();
    }

}
