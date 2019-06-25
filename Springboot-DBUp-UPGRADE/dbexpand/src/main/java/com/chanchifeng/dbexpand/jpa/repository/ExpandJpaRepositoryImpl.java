package com.chanchifeng.dbexpand.jpa.repository;

import com.chanchifeng.dbexpand.jpa.exception.DataException;
import com.chanchifeng.dbexpand.jpa.parameter.Operator;
import com.chanchifeng.dbexpand.jpa.parameter.Predicate;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.*;

import static org.apache.commons.lang3.StringUtils.*;

public class ExpandJpaRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements ExpandJpaRepository<T, ID> {

    private EntityManager entityManager;

    /*
    * TODO 如何获取entityInformation？
    *
    * */
//    private JpaEntityInformation entityInformation;

    private final Class<T> domainClass;

    public ExpandJpaRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
        this.domainClass = domainClass;
//        this.entityInformation = entityInformation;
    }

    @Override
    public T findOne(String condition, Object... values) {
        if (isEmpty(condition)) {
            throw new NullPointerException("条件不能为空!");
        }
        T result = null;
        try {
            result = (T) createQuery(condition, values).getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return result;
    }

//    @Override
//    public List<T> findAll(String condition, Object... objects) {
//        return createQuery(condition, objects).getResultList();
//    }
//
//    @Override
//    public List<T> findAll(Iterable<Predicate> predicates, Operator operator) {
//        return new JpqlQueryHolder(predicates, operator).createQuery().getResultList();
//    }
//
//    @Override
//    public List<T> findAll(Iterable<Predicate> predicates, Operator operator, Sort sort) {
//        return new JpqlQueryHolder(predicates, operator, sort).createQuery().getResultList();
//    }

//    @Override
//    public Page<T> findAll(Iterable<Predicate> predicates, Operator operator, Pageable pageable) {
//        //todo ban
////        if (pageable == null) {
////            return new PageImpl<T>((List<T>) findAll(predicates, operator));
////        }
//
//        Long total = count(predicates, operator);
//
//        Query query = new JpqlQueryHolder(predicates, operator, pageable.getSort()).createQuery();
//        query.setFirstResult((int) pageable.getOffset());
//        query.setMaxResults(pageable.getPageSize());
//
//        List<T> content = total > pageable.getOffset() ? query.getResultList() : Collections.<T>emptyList();
//
//        return new PageImpl<T>(content, pageable, total);
//    }

    @Override
    public long count(Iterable<Predicate> predicates, Operator operator) {
        return new JpqlQueryHolder(predicates, operator).createCountQuery().getSingleResult();
    }

//    @Override
//    public List<T> findAll(String condition, Sort sort, Object... objects) {
//        return createQuery(condition, sort, objects).getResultList();
//    }
//
//    @Override
//    public Page<T> findAll(String condition, Pageable pageable, Object... objects) {
//        if (pageable == null) {
//            return new PageImpl<T>((List<T>) findAll(condition, objects));
//        }
//
//        Long total = count(condition, objects);
//
//        Query query = createQuery(condition, pageable.getSort(), objects);
//        query.setFirstResult((int) pageable.getOffset());
//        query.setMaxResults(pageable.getPageSize());
//
//        List<T> content = total > pageable.getOffset() ? query.getResultList() : Collections.<T>emptyList();
//
//        return new PageImpl<T>(content, pageable, total);
//    }

    @Override
    public long count(String condition, Object... objects) {
        return new JpqlQueryHolder(condition, objects).createCountQuery().getSingleResult();
    }

//    @Override
//    public void deleteByIds(Iterable<ID> ids) {
//        List<T> tlist = super.findAllById(ids);
//        super.deleteInBatch(tlist);
//    }

    @Override
    public Class<T> getEntityClass() {
        return domainClass;
//        return entityInformation.getJavaType();
    }

//    @Override
//    public List<Map<String, Object>> nativeQuery4Map(String sql) {
//        Query nativeQuery = entityManager.createNativeQuery(sql);
////        过期
////        nativeQuery.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
//        nativeQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
//        return nativeQuery.getResultList();
//    }
//
//    @Override
//    public Page<Map> nativeQuery4Map(String sql, Pageable pageable) {
//        Query nativeQuery = entityManager.createNativeQuery(sql);
////        过期
////        nativeQuery.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
//        nativeQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
//        nativeQuery.setFirstResult((int) pageable.getOffset());
//        nativeQuery.setMaxResults(pageable.getPageSize());
//
//        Query countNativeQuery = entityManager.createNativeQuery("select count(*) from (" + sql + ") a");
//        long total = Long.valueOf(String.valueOf(countNativeQuery.getSingleResult()));
//
//        return new PageImpl<Map>(nativeQuery.getResultList(), pageable, total);
//    }
//
//    @Override
//    public Object nativeQuery4Object(String sql) {
//        Query nativeQuery = entityManager.createNativeQuery(sql);
//
//        List results = nativeQuery.getResultList();
//        if (results == null || results.size() == 0) {
//            return null;
//        }
//        try {
//            if (results.size() > 1) {
//                throw new RuntimeException("结果应当只有一个，但是发现了" + results.size() + "个。");
//            }
//            return results.get(0);
//        } catch (NoResultException e) {
//            return null;
//        }
//    }

    /* start custom code */

    /**
     * 声明entityClass的查询
     */
    private Query createQuery(String condition, Sort sort, Object[] objects) {

        JpqlQueryHolder queryHolder = new JpqlQueryHolder(condition, sort, objects);

        return queryHolder.createQuery();
    }

    /**
     * 声明entityClass的查询
     */
    private Query createQuery(String condition, Object[] objects) {
        return createQuery(condition, null, objects);
    }

    private class JpqlQueryHolder {

        //别名
        private final String ALIAS = "x";

        //QUERY ALL
        private final String FIND_ALL_QUERY_STRING = "from %s " + ALIAS;

        //传入的condition 排除列表
        private final String[] IGNORE_CONSTAINS_CHARSEQUENCE = {"where", "WHERE", "from", "FROM"};

        private String condition = null;
        private Sort sort;
        private Object[] objects;
        private Iterable<Predicate> predicates;
        private Operator operator = Operator.AND;

        private JpqlQueryHolder(Iterable<Predicate> predicates, Operator operator, Sort sort) {
            this.predicates = predicates;
            this.operator = operator;
            this.sort = sort;
        }

        private JpqlQueryHolder(Iterable<Predicate> predicates, Operator operator) {
            this.operator = operator;
            this.predicates = predicates;
        }

        private JpqlQueryHolder(String condition, Sort sort, Object[] objects) {
            this(condition, objects);
            this.sort = sort;
        }

        private JpqlQueryHolder(String condition, Object[] objects) {

            if (startsWithAny(condition, IGNORE_CONSTAINS_CHARSEQUENCE)) {
                throw new DataException(DataException.noPermission, "查询条件中只能包含WHERE条件表达式!");
            }
            this.condition = trimToNull(condition);
            this.objects = objects;
        }

        private Query createQuery() {
            StringBuilder sb = new StringBuilder();
            // select x from table
//            sb.append(QueryUtils.getQueryString(FIND_ALL_QUERY_STRING, entityInformation.getEntityName()))
            sb.append(QueryUtils.getQueryString(FIND_ALL_QUERY_STRING, domainClass.getName()))
                    //where
                    .append(applyCondition());

            Query query = entityManager.createQuery(QueryUtils.applySorting(sb.toString(), sort, ALIAS));
            applyQueryParameter(query);
            return query;
        }

        private TypedQuery<Long> createCountQuery() {
            String ql = String.format(QueryUtils.COUNT_QUERY_STRING, ALIAS, "%s");
//            ql = QueryUtils.getQueryString(ql, entityInformation.getEntityName());
            ql = QueryUtils.getQueryString(ql, domainClass.getName());
            ql += applyCondition();

            TypedQuery<Long> query = entityManager.createQuery(ql, Long.class);
            applyQueryParameter(query);
            return query;
        }

        private List<String> map2Conditions() {
            if (predicates == null || !predicates.iterator().hasNext()) {
                return new ArrayList<String>();
            }
            List<String> conditions = new ArrayList<String>();

            Iterator<Predicate> iterator = predicates.iterator();
            int index = 0;
            while (iterator.hasNext()) {
                Predicate predicate = iterator.next();
                if (predicate.getKey() == null) {
                    continue;
                }
                conditions.add(predicate.toCondition(String.valueOf(index)));
                index++;
            }
            return conditions;
        }

        private String applyCondition() {
            List<String> conditions = map2Conditions();
            if (condition != null) {
                conditions.add(condition);
            }
            condition = join(conditions, " " + operator.name() + " ");
            return isEmpty(condition) ? "" : " where " + condition;
        }

        private void applyQueryParameter(Query query) {
            if (objects != null) {
                int i = 0;
                for (Object value : objects) {
                    i++;
                    query.setParameter(i, value);
                }
            }
            if (predicates != null && predicates.iterator().hasNext()) {
                int index = 0;
                Iterator<Predicate> iterator = predicates.iterator();
                while (iterator.hasNext()) {
                    Predicate predicate = iterator.next();
                    predicate.setParameter(query, String.valueOf(index));
                    index++;
                }
            }
        }
    }

    /* start custom code */
}

