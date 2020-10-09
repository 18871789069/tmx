package com.tmx.base.service.impl;

import com.tmx.base.service.CompanyService;
import com.tmx.ioc.springIOC.ExtService;

/**
 * Created By Riven on 2020-10-9
 */
@ExtService
public class CompanyServiceImpl implements CompanyService {

    @Override
    public void add() {
        System.out.println("测试自动注入");
    }
}
