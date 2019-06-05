package com.chanchifeng.data.domain;

import lombok.*;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.typeconversion.DateLong;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@NoArgsConstructor
@NodeEntity
public class Student {

    @Id @GeneratedValue @Getter @Setter private Long studentId;

    @Getter @Setter private String studentName;

    @Getter @Setter private int studentAge;

    @DateLong @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Getter @Setter private Date studentBorn;

}
