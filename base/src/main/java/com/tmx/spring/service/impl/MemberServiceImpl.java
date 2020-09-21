package com.tmx.spring.service.impl;

import com.tmx.spring.service.MemberService;
import org.springframework.stereotype.Service;

/**
 * Created By Riven on 2020-9-21
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Override
    public void add() {
        System.out.println("新增成员");
    }

    @Override
    public void update() {
        System.out.println("修改成员");
    }
}
