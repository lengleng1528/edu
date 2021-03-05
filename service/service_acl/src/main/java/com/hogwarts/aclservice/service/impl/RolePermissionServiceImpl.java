package com.hogwarts.aclservice.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hogwarts.aclservice.entity.RolePermission;
import com.hogwarts.aclservice.mapper.RolePermissionMapper;
import com.hogwarts.aclservice.service.RolePermissionService;
import org.springframework.stereotype.Service;


@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

}
