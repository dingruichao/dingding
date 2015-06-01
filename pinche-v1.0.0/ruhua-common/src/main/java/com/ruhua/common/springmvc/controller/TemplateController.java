//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ruhua.common.springmvc.controller;

//import com.jd.o2o.commons.utils.web.HttpServletUtils;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({"/template"})
public class TemplateController {
    private static final Logger logger = Logger.getLogger(TemplateController.class);
    //@Value("${template.dir}")
    private String templateDir="c:/template";

    public TemplateController() {
    }

    @RequestMapping(
        value = {"/download"},
        method = {RequestMethod.POST}
    )
    public void download(@RequestParam("filePath") String filePath, String targetFileName, HttpServletResponse response) {
        setFileDownloadHeader(response, targetFileName);
        response.setContentType("application/octet-stream");

        try {
            ServletOutputStream e = response.getOutputStream();
            FileCopyUtils.copy(new FileInputStream(this.templateDir + "/" + filePath), e);
        } catch (IOException var5) {
            logger.error("TemplateController->error!", var5);
        }

    }

    public static void setFileDownloadHeader(HttpServletResponse response, String fileName) {
        try {
            String e = new String(fileName.getBytes(), "ISO8859-1");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + e + "\"");
        } catch (UnsupportedEncodingException var3) {
            ;
        }

    }
}
