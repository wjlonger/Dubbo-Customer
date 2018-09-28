package com.coder.dubbo.customer.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.coder.dubbo.customer.util.JsonUtils;
import com.coder.springbootdomecollection.mapper.RoleMenuMapper;
import com.coder.springbootdomecollection.mapper.RolePermissionMapper;
import com.coder.springbootdomecollection.model.RoleMenu;
import com.coder.springbootdomecollection.model.RolePermission;
import com.coder.springbootdomecollection.model.SysRole;
import com.coder.springbootdomecollection.service.RoleMenuService;
import com.coder.springbootdomecollection.service.RolePermissionService;
import com.coder.springbootdomecollection.service.SysRoleService;
import com.coder.util.CollectionUtils;
import com.coder.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import java.util.List;

@Scope("prototype")
@RestController
@RequestMapping("/api/role")
public class ApiSystemRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private RoleMenuService roleMenuService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @PostMapping("/menu")
    private String saveMenu(RoleMenu roleMenu){
        int i = roleMenuService.insert(roleMenu);
        JSONObject json = new JSONObject();
        JsonUtils.addMessage(i,json);
        if(i > 0){
            SysRole sysRole = sysRoleService.selectByPrimaryKey(roleMenu.getRoleid());
            json.put("role",sysRole);
        }
        return json.toString();
    }

    @PostMapping("/permission")
    private String savePermission(RolePermission rolePermission){
        int i = rolePermissionService.insert(rolePermission);
        JSONObject json = new JSONObject();
        JsonUtils.addMessage(i,json);
        if(i > 0){
            SysRole sysRole = sysRoleService.selectByPrimaryKey(rolePermission.getRid());
            json.put("role",sysRole);
        }
        return json.toString();
    }

    @DeleteMapping("/menu/{roleid}/{menuid}")
    private String deleteMenu(@PathVariable int roleid, @PathVariable int menuid){
        RoleMenu roleMenu = new RoleMenu();
        roleMenu.setRoleid(roleid);
        roleMenu.setMenuid(menuid);
        int i = roleMenuService.deleteByPrimaryKey(roleMenu);
        JSONObject json = new JSONObject();
        JsonUtils.addMessage(i,json);
        if(i > 0){
            SysRole sysRole = sysRoleService.selectByPrimaryKey(roleMenu.getRoleid());
            json.put("role",sysRole);
        }
        return json.toString();
    }

    @DeleteMapping("/permission/{roleid}/{permissionid}")
    private String deletePermission(@PathVariable int roleid, @PathVariable int permissionid){
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRid(roleid);
        rolePermission.setPid(permissionid);
        int i = rolePermissionService.deleteByPrimaryKey(rolePermission);
        JSONObject json = new JSONObject();
        JsonUtils.addMessage(i,json);
        if(i > 0){
            SysRole sysRole = sysRoleService.selectByPrimaryKey(rolePermission.getRid());
            json.put("role",sysRole);
        }
        return json.toString();
    }

    @PostMapping
    private String save(SysRole sysRole,String[] mids, String[] pids){
        int i = sysRoleService.save(sysRole);
        JSONObject json = new JSONObject();
        JsonUtils.addMessage(i,json);
        if(i > 0){
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleid(sysRole.getRid());
            roleMenuService.deleteByProperty(roleMenu);
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRid(sysRole.getRid());
            rolePermissionService.deleteByProperty(rolePermission);
            if(!CollectionUtils.isNullOrEmptyStrict(mids)){
                for(String mid : mids){
                    if(!StringUtils.isNullOrSpace(mid)){
                        int imid = Integer.parseInt(mid);
                        if(imid > 0){
                            RoleMenu rm = new RoleMenu();
                            rm.setRoleid(sysRole.getRid());
                            rm.setMenuid(imid);
                            roleMenuService.insert(rm);
                        }
                    }
                }
            }
            if(!CollectionUtils.isNullOrEmptyStrict(pids)){
                for(String pid : pids){
                    if(!StringUtils.isNullOrSpace(pid)){
                        int ipid = Integer.parseInt(pid);
                        if(ipid > 0){
                            RolePermission rp = new RolePermission();
                            rp.setRid(roleMenu.getRoleid());
                            rp.setPid(ipid);
                            rolePermissionService.insert(rp);
                        }
                    }
                }
            }
            List<SysRole> sysRoles = sysRoleService.selectAll(null);
            json.put("roles",sysRoles);
        }
        return json.toString();
    }

}
