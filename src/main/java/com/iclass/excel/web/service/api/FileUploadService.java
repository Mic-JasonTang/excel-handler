package com.iclass.excel.web.service.api;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * excel-handler
 * <p>
 * Created by yang.tang on 2017/2/22 17:21.
 */
public interface FileUploadService {

    /**
     * 文件上传Controller
     * @param request 获取session,获取文件存储路径
     * @param file 客户端传过来的文件
     * @return 服务器相应信息
     */
    String upload(HttpServletRequest request, MultipartFile file);
}
