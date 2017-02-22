package com.iclass.excel.web.controller;

import com.iclass.common.Constant;
import com.iclass.excel.mybatis.po.TSysResource;
import com.iclass.excel.web.service.impl.ExcelHandlerServiceImpl;
import com.iclass.excel.web.service.impl.FileUploadServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * excel-handler
 * <p>
 * Created by yang.tang on 2017/2/22 17:43.
 */
@RestController
public class FileUploadController {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    private FileUploadServiceImpl fileUploadService;

    @Autowired
    private ExcelHandlerServiceImpl excelHandlerService;

    @RequestMapping(value = "/upload", produces = "application/text; charset=UTF-8")
    public String upload(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        String result = fileUploadService.upload(request, file);
        HttpSession session = request.getSession();
        String absolutePath = (String)session.getAttribute(Constant.FILE_SAVE_PATH_SESSION_KEY);
        logger.info("FileUploadController.upload: 从session中获取到的文件路径" + absolutePath);
        File excelFile = new File(absolutePath);
        try {
            // 处理 xls 2003-2007
            List<TSysResource> sysResourceList = excelHandlerService.readExcel(excelFile.getCanonicalPath());
            Boolean executeResult = excelHandlerService.insert(sysResourceList);
            if(executeResult) {
                result += ",并执行成功" + ",总共" + sysResourceList.size() + "条数据";
            } else {
                result += ",但执行失败,数据已回滚";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
