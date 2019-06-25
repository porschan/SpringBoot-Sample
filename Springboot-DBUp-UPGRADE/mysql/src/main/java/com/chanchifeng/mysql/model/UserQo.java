package com.chanchifeng.mysql.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
public class UserQo extends PageQo{

    private Long id;

    private String name;

    private String email;

    private Integer sex;

    @DateTimeFormat(pattern = "yyyy-MM-dd") private Date createdate;

    @DateTimeFormat(pattern = "yyyy-MM-dd") private Date createdateStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd") private Date createdateEnd;

}
