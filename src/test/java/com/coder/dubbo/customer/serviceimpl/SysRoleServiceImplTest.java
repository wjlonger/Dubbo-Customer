package com.coder.dubbo.customer.serviceimpl;

import com.coder.springbootdomecollection.model.SysMenu;
import com.coder.springbootdomecollection.model.SysPermission;
import com.coder.springbootdomecollection.model.SysRole;
import com.coder.springbootdomecollection.model.SysUser;
import com.coder.springbootdomecollection.service.SysRoleService;
import com.coder.springbootdomecollection.service.SysUserService;
import com.coder.util.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysRoleServiceImplTest {

    @Autowired
    private SysRoleService sysRoleService;

    @Test
    public void testRole(){
        SysRole sysRole = new SysRole();
        sysRole.setRname("aaa");
        sysRoleService.insert(sysRole);
        System.out.println(sysRole.getRid());
    }

}