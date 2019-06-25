package com.chanchifeng.mysql.service;

import com.chanchifeng.mysql.entity.User;
import com.chanchifeng.mysql.model.UserQo;
import com.chanchifeng.mysql.redis.UserRedis;
import com.chanchifeng.mysql.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

//    @Autowired
    @Resource
    private UserRepository userRepository;

//    @Autowired
    @Resource
    private UserRedis userRedis;

    private static final String keyHead = "mysql:get:user:";

    public User findById(Long id) {
        User user = userRedis.get(keyHead + id);
        if(user == null){
            user = userRepository.findById(id).get();
            if(user != null) {
                userRedis.add(keyHead + id, 30L, user);
            }
        }
        return user;
    }

    public User create(User user) {
        User newUser = userRepository.save(user);
        if(newUser != null) {
            userRedis.add(keyHead + newUser.getId(), 30L, newUser);
        }
        return newUser;
    }

    public User update(User user) {
        if(user != null) {
            userRedis.delete(keyHead + user.getId());
            userRedis.add(keyHead + user.getId(), 30L, user);
        }
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRedis.delete(keyHead + id);
        userRepository.deleteById(id);
    }

    public Page<User> findPage(UserQo userqo){

        Pageable pageable = PageRequest.of(userqo.getPage(), userqo.getSize());

        Page<User> all = userRepository.findAll((Specification<User>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (userqo.getName() != null && !userqo.getName().equals("")) {
                System.out.println("user.getName():" + userqo.getName());
                predicates.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + userqo.getName() + "%"));
            }

            Predicate[] pre = new Predicate[predicates.size()];

            query.where(predicates.toArray(pre));
            return criteriaBuilder.and(predicates.toArray(pre));
        }, pageable);
        return all;
    }
}
