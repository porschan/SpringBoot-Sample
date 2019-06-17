package com.chanchifeng.dbexpand.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class ExpandJpaRepositoryFactoryBean<R extends JpaRepository<T, ID>, T, ID extends Serializable> extends JpaRepositoryFactoryBean<R, T, ID> {
    /**
     * Creates a new {@link JpaRepositoryFactoryBean} for the given repository interface.
     *
     * @param repositoryInterface must not be {@literal null}.
     */
    public ExpandJpaRepositoryFactoryBean(Class<? extends R> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new ExpandJpaRepositoryFactory<T, ID>(entityManager);
    }
//
    private static class ExpandJpaRepositoryFactory<T, ID extends Serializable> extends JpaRepositoryFactory {
    /**
     * Creates a new {@link JpaRepositoryFactory}.
     *
     * @param entityManager must not be {@literal null}
     */
//    public ExpandJpaRepositoryFactory(EntityManager entityManager) {
//        super(entityManager);
//    }
//
        private final EntityManager entityManager;

        public ExpandJpaRepositoryFactory(EntityManager entityManager) {

            super(entityManager);
            this.entityManager = entityManager;
        }
//
        protected Object getTargetRepository(RepositoryMetadata metadata) {
//            JpaEntityInformation<T, Serializable> entityInformation = (JpaEntityInformation<T, Serializable>) getEntityInformation(metadata.getDomainType());
//            return new ExpandJpaRepositoryImpl<T, ID>(entityInformation, entityManager);
//            return new BaseRepositoryImpl<T, ID>((Class<T>) metadata.getDomainType(), entityManager);

            return new ExpandJpaRepositoryImpl<T, ID>((Class<T>) metadata.getDomainType(), entityManager);
        }

        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return ExpandJpaRepositoryImpl.class;
        }
    }
}