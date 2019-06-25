package com.chanchifeng.mysql.repository;

import com.chanchifeng.mysql.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//@Repository
//public interface UserRepository extends ExpandJpaRepository<User, Long> {
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select t from User t where t.name =?1 and t.email =?2")
    User findByNameAndEmail(String name, String email);

    @Query("select t from User t where t.name like :name")
    Page<User> findByName(@Param("name") String name, Pageable pageRequest);

    /* START 带模糊搜索的分页功能 */

    Page<User> findAll(Specification<User> specification, Pageable pageable);

    /* END 带模糊搜索的分页功能 */
}
