//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ruhua.common.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/error"})
public class HTTPErrorController {
    public HTTPErrorController() {
    }

    @RequestMapping({"/403.html"})
    public String handle403() {
        return "error/access-denied";
    }

    @RequestMapping({"/404.html"})
    public String handle404() {
        return "error/uncaught";
    }

    @RequestMapping({"/405.html"})
    public String handle405() {
        return "error/uncaught";
    }

    @RequestMapping({"/500.html"})
    public String handle500() {
        return "error/uncaught";
    }

    @RequestMapping({"/505.html"})
    public String handle505() {
        return "error/uncaught";
    }
}
