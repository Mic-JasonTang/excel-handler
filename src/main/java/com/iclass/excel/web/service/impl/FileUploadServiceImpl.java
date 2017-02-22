package com.iclass.excel.web.service.impl;

import com.iclass.common.Constant;
import com.iclass.excel.web.service.api.FileUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * excel-handler
 * <p>
 * Created by yang.tang on 2017/2/22 17:22.
 */
@Service
public class FileUploadServiceImpl implements FileUploadService{

    private static final Logger logger = LoggerFactory.getLogger(FileUploadServiceImpl.class);

    /**
     * 文件上传Controller
     * @param request 获取session,获取文件存储路径
     * @param file 客户端传过来的文件
     * @return 服务器相应信息
     */
    @Override
    public String upload(HttpServletRequest request, MultipartFile file) {
        HttpSession session = request.getSession();
        if (file.isEmpty()) {
            return "文件为空";
        }

        // 获取文件名
        String fileName = file.getOriginalFilename();
        logger.info("上传的文件名为：" + fileName);

        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        logger.info("上传的后缀名为：" + suffixName);

        // 文件上传路径
        String filePath = session.getServletContext().getRealPath("/files/");
        logger.info("保存的文件路径为：" + filePath);

        fileName = UUID.randomUUID() + "-" + fileName;
        logger.info("做了唯一性处理之后的文件名为：" + fileName);

        File dest = new File(filePath + fileName);
        String absolutePath = dest.getAbsolutePath();
        logger.info("文件真实存放的路径：" + absolutePath);

        session.setAttribute(Constant.FILE_SAVE_PATH_SESSION_KEY, absolutePath);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }

        try {
            file.transferTo(dest);
            logger.info("上传成功");
            return "上传成功";
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }
}
