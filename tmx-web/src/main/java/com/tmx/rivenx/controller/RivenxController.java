package com.tmx.rivenx.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created By Riven on 2020-8-27
 */
@RestController
@RequestMapping(value = "/rivenx")
public class RivenxController {

    @RequestMapping("/getRivenx")
    public String getRivenx() {
        return "Rivenx love tmx";
    }
}
