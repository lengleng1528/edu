package com.hogwarts.aclservice.controller;



import com.hogwarts.aclservice.entity.Permission;
import com.hogwarts.aclservice.service.PermissionService;
import com.hogwarts.commonutils.Res;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 权限 菜单管理
 * </p>
 *
 */
@RestController
@RequestMapping("/admin/acl/permission")
//@CrossOrigin
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    //获取全部菜单
    @ApiOperation(value = "查询所有菜单")
    @GetMapping
    public Res indexAllPermission() {
        List<Permission> list =  permissionService.queryAllMenuGuli();
        return Res.ok().data("children",list);
    }

    @ApiOperation(value = "递归删除菜单")
    @DeleteMapping("remove/{id}")
    public Res remove(@PathVariable String id) {
        permissionService.removeChildByIdGuli(id);
        return Res.ok();
    }

    @ApiOperation(value = "给角色分配权限")
    @PostMapping("/doAssign")
    public Res doAssign(String roleId,String[] permissionId) {
        permissionService.saveRolePermissionRealtionShipGuli(roleId,permissionId);
        return Res.ok();
    }

    @ApiOperation(value = "根据角色获取菜单")
    @GetMapping("toAssign/{roleId}")
    public Res toAssign(@PathVariable String roleId) {
        List<Permission> list = permissionService.selectAllMenu(roleId);
        return Res.ok().data("children", list);
    }



    @ApiOperation(value = "新增菜单")
    @PostMapping("save")
    public Res save(@RequestBody Permission permission) {
        permissionService.save(permission);
        return Res.ok();
    }

    @ApiOperation(value = "修改菜单")
    @PutMapping("update")
    public Res updateById(@RequestBody Permission permission) {
        permissionService.updateById(permission);
        return Res.ok();
    }

}

