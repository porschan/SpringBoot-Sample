package com.chanchifeng.data.domain;

import lombok.*;
import org.neo4j.ogm.annotation.*;


@AllArgsConstructor
@NoArgsConstructor
@RelationshipEntity(type = "借出")
public class Role {

    @Id @GeneratedValue @Getter @Setter private Long roleId;

    @Getter @Setter private String roleName;

    @EndNode @Getter @Setter private Student student;

    @StartNode @Getter @Setter private Book book;

}
