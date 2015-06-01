//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ruhua.common.springmvc.controller;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({"/"})
public class IndexController {
    private static Logger logger = Logger.getLogger(IndexController.class);

    public IndexController() {
    }

    @RequestMapping(
        value = {"/"},
        method = {RequestMethod.GET}
    )
    public String index(@RequestParam(
    value = "locale",
    required = false
) Locale locale, HttpServletRequest request, HttpServletResponse response) {
        String viewUrl = "/common/default-frame";
        return viewUrl;
    }

    @RequestMapping(
        value = {"/index"},
        method = {RequestMethod.GET}
    )
    public String index(HttpServletRequest request, HttpServletResponse response) {
        return "/index";
    }

    @RequestMapping(
        value = {"/top"},
        method = {RequestMethod.GET}
    )
    public String top(@RequestParam(
    value = "locale",
    required = false
) Locale locale, HttpServletRequest request, HttpServletResponse response, Model view) {
        return "/common/top";
    }

    @RequestMapping(
        value = {"/center"},
        method = {RequestMethod.GET}
    )
    public String center(@RequestParam(
    value = "locale",
    required = false
) Locale locale, HttpServletRequest request, HttpServletResponse response, Model view) {
        return "/common/center";
    }

    @RequestMapping(
        value = {"/bottom"},
        method = {RequestMethod.GET}
    )
    public String bottom(@RequestParam(
    value = "locale",
    required = false
) Locale locale, HttpServletRequest request, HttpServletResponse response, Model view) {
        return "/common/bottom";
    }

    @RequestMapping(
        value = {"/middle"},
        method = {RequestMethod.GET}
    )
    public String middle(@RequestParam(
    value = "locale",
    required = false
) Locale locale, HttpServletRequest request, HttpServletResponse response, Model view) {
        return "/common/middle";
    }
}
