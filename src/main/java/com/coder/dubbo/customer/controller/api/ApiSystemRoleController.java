package com.coder.dubbo.customer.controller.api;

import com.coder.springbootdomecollection.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

@Scope("prototype")
@RestController
@RequestMapping("/api/role")
public class ApiSystemRoleController {

    @Autowired
    private SysRoleService sysRoleService;


}
