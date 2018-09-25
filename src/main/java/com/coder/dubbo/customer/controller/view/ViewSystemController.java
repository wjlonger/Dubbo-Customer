package com.coder.dubbo.customer.controller.view;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.coder.springbootdomecollection.model.SysMenu;
import com.coder.springbootdomecollection.model.SysPermission;
import com.coder.springbootdomecollection.model.SysRole;
import com.coder.springbootdomecollection.service.SysMenuService;
import com.coder.springbootdomecollection.service.SysPermissionService;
import com.coder.springbootdomecollection.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.ws.Action;
import java.util.List;

/**
 * @author 吴俊龙
 * @version 1.0
 * @createdate 2018-09-15 22:21
 */

@Controller
@Scope("prototype")
@RequestMapping("/system")
public class ViewSystemController {

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysPermissionService sysPermissionService;

    @GetMapping("/user")
    public String user(Model model) {
        return "system/user";
    }

    @GetMapping("/role")
    public String role(Model model) {
        List<SysRole> roles = sysRoleService.selectAll(null);
        model.addAttribute("data",JSONObject.toJSON(roles));
        List<SysMenu> menus = sysMenuService.selectAll();
        model.addAttribute("menus",JSONObject.toJSON(menus));
        List<SysPermission> permissions = sysPermissionService.selectAll(null);
        model.addAttribute("permissions",JSONObject.toJSON(permissions));
        return "system/role";
    }

    @GetMapping("/permission")
    public String permission(Model model) {
        return "system/permission";
    }

    @GetMapping("/menu")
    public String menu(Model model) {
        List<SysMenu> menus = sysMenuService.selectAll();
        model.addAttribute("data",JSONObject.toJSON(menus));
        return "system/menu";
    }

}
