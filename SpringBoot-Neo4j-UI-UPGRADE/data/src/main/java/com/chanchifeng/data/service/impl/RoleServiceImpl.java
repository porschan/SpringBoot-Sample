package com.chanchifeng.data.service.impl;

import com.chanchifeng.data.common.vo.PageVO;
import com.chanchifeng.data.domain.Role;
import com.chanchifeng.data.repo.RoleRepo;
import com.chanchifeng.data.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepo roleRepo;

    @Override
    public PageVO<Role> finlAllPage() {
        List<Role> likes = roleRepo.getLikes();
        return new PageVO<Role>(-1,-1,likes,-1);
    }
}
