package com.chanchifeng.dbexpand.jpa.repository;

import com.chanchifeng.dbexpand.jpa.parameter.Operator;
import com.chanchifeng.dbexpand.jpa.parameter.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@NoRepositoryBean
public interface ExpandJpaRepository<T, ID extends Serializable> extends JpaRepository<T,ID> {

    T findOne(String condition, Object... objects);

//    List<T> findAll(String condition, Object... objects);
//
//    List<T> findAll(Iterable<Predicate> predicates, Operator operator);
//
//    List<T> findAll(Iterable<Predicate> predicates, Operator operator, Sort sort);

//    Page<T> findAll(Iterable<Predicate> predicates, Operator operator, Pageable pageable);

    long count(Iterable<Predicate> predicates, Operator operator);

//    List<T> findAll(String condition, Sort sort, Object... objects);
//
//    Page<T> findAll(String condition, Pageable pageable, Object... objects);

    long count(String condition, Object... objects);

//    todo ban
//    void deleteByIds(Iterable<ID> id);

    Class<T> getEntityClass();

//    todo ban
//    List<Map<String,Object>> nativeQuery4Map(String sql);
//
//    Page<Map> nativeQuery4Map(String sql, Pageable pageable);
//
//    Object nativeQuery4Object(String sql);
}