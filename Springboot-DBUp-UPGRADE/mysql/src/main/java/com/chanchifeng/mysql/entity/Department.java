package com.chanchifeng.mysql.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "department")
public class Department implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") private Date createdate;

}
