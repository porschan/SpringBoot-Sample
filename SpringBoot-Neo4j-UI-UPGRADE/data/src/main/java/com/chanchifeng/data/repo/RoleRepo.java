package com.chanchifeng.data.repo;

import com.chanchifeng.data.domain.Role;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepo extends PagingAndSortingRepository<Role,Long> {

    @Query("match p = (n)-[r:`借出`]->(b) return p")
    List<Role> getLikes();

    @Query("match p = (n)-[r:Role]->(b) return p")
    List<Object> getLikesObj();
}
