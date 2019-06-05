package com.chanchifeng.data.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.DateLong;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@NodeEntity
public class Book {

    @Id @GeneratedValue @Getter @Setter private Long bookId;

    @Getter @Setter private String bookName;

    @DateLong @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Getter @Setter private Date bookCreateTime;

    @Relationship(type = "借出") List<Role> roles = new ArrayList<>();

    public Role addRole(Student student,String roleName){
        Role role = new Role(null, roleName, student, this);
        this.roles.add(role);
        return role;
    }

}
