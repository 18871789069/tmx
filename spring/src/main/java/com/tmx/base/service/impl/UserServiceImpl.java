package com.tmx.base.service.impl;

import com.tmx.base.service.CompanyService;
import com.tmx.base.service.UserService;
import com.tmx.ioc.springIOC.ExtService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created By Riven on 2020-9-24
 */
//@Service
@ExtService
public class UserServiceImpl implements UserService {

    public String a;

    private CompanyServiceImpl companyServiceImpl;

    @Override
    public void add() {
        System.out.println("初始化");
        companyServiceImpl.add();
    }
}
