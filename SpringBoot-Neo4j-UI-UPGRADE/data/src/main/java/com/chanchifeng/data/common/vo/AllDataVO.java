package com.chanchifeng.data.common.vo;

import com.chanchifeng.data.domain.Book;
import com.chanchifeng.data.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class AllDataVO  implements Serializable {
    private Iterable<Student> allStudent;
    private Iterable<Book> allBook;
}
