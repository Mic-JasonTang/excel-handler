package com.iclass.excel.web.service.impl;

import com.iclass.common.Constant;
import com.iclass.excel.mybatis.mapper.TSysResourceMapper;
import com.iclass.excel.mybatis.mapper.TSysSystemMapper;
import com.iclass.excel.mybatis.po.TSysResource;
import com.iclass.excel.web.service.api.ExcelHandlerService;
import com.iclass.util.Util;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * excel-handler
 * <p>
 * Created by yang.tang on 2017/2/22 10:45.
 */
@Service
public class ExcelHandlerServiceImpl implements ExcelHandlerService<List<TSysResource>> {
    private static final Logger logger = LoggerFactory.getLogger(ExcelHandlerServiceImpl.class);

    @Autowired
    TSysResourceMapper sysResourceMapper;

    @Autowired
    TSysSystemMapper sysSystemMapper;

    /**
     * 读取Excel文件,xls 和 xlsx文件
     * @param path excel文件路径
     * @return 返回对象实体
     */
    @Override
    public List<TSysResource> readExcel(String path) {
        List<TSysResource> result = new ArrayList<>();
        if (path == null || Constant.EMPTY.equals(path)) {
            logger.info("path不能为空:"+path+"!!!");
            return null;
        } else {
            String postfix = Util.getPostfix(path);
            if (!Constant.EMPTY.equals(postfix)) {
                if (Constant.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
                    result = readXLS(path);
                    logger.info(result.toString());
                } else if (Constant.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
                    result = readXLSX(path);
                    logger.info(result.toString());
                }
            } else {
                logger.info(path + Constant.NOT_EXCEL_FILE);
            }
        }
        return result;
    }

    /**
     * 处理XLS 格式的文件
     * @param path excel文件的路径
     * @return 返回对象的实体
     */
    @Override
    public List<TSysResource> readXLS(String path) {
        logger.info(Constant.PROCESSING + path);
        File file = new File(path);
        logger.info(file.getName());
        InputStream is = null;
        try {
            is = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            logger.error("路径为"+path+"的文件没有找到");
            logger.error(e.toString());
        }
        HSSFWorkbook hssfWorkbook = null;
        try {
            hssfWorkbook = new HSSFWorkbook(is);
        } catch (IOException e) {
            logger.error(e.toString());
        }
        TSysResource sysResource = null;
        List<TSysResource> list = new ArrayList<>();
        // Read the Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // Read the Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if(rowNum == 1) {
                    if(hssfRow.getLastCellNum() != Constant.MAX_CELL_NUMBER) {
                        logger.error("excel文件的单元格必须为"+Constant.MAX_CELL_NUMBER+"但是只有"+hssfRow.getLastCellNum()+"格");
                    }
                    logger.info("单元格总数为:"+hssfRow.getLastCellNum()+"");
                }
                if (hssfRow != null) {
                    sysResource = new TSysResource();
                    HSSFCell accessCode = hssfRow.getCell(0);
                    HSSFCell available = hssfRow.getCell(1);
                    HSSFCell name = hssfRow.getCell(2);
                    HSSFCell resourceName = hssfRow.getCell(3);
                    HSSFCell resourceType = hssfRow.getCell(4);
                    HSSFCell sortNum = hssfRow.getCell(5);
                    HSSFCell url = hssfRow.getCell(6);
                    HSSFCell systemCode = hssfRow.getCell(7);
                    HSSFCell creatorId = hssfRow.getCell(8);
                    HSSFCell modifierId = hssfRow.getCell(9);
                    HSSFCell status = hssfRow.getCell(10);
                    sysResource = setValue(sysResource, accessCode, available, name, resourceName, resourceType, sortNum, url, systemCode, creatorId, modifierId, status);
                    list.add(sysResource);
                } else {
                    logger.warn(hssfSheet.getSheetName()+"这个sheet的数据为空");
                }
            }
        }
        return list;
    }

    /**
     * 读取XLSX 格式的文件
     * @param path excel文件的路径
     * @return 返回对象实体
     */
    @Override
    public List<TSysResource> readXLSX(String path) {
        logger.info(Constant.PROCESSING + path);
        File file = new File(path);
        logger.info(file.getName());
        InputStream is = null;
        try {
            is = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            logger.error("路径为"+path+"的文件没有找到");
            logger.error(e.toString());
        }
        XSSFWorkbook xssfWorkbook = null;
        try {
            xssfWorkbook = new XSSFWorkbook(is);
        } catch (IOException e) {
            logger.error(e.toString());
        }
        TSysResource sysResource = null;
        List<TSysResource> list = new ArrayList<>();
        // Read the Sheet
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            // Read the Row
            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if(rowNum == 1) {
                    if(xssfRow.getLastCellNum() != Constant.MAX_CELL_NUMBER) {
                        logger.error("excel文件的单元格必须为"+Constant.MAX_CELL_NUMBER+"但是只有"+xssfRow.getLastCellNum()+"格");
                    }
                    logger.info("单元格总数为:"+xssfRow.getLastCellNum()+"");
                }
                if (xssfRow != null) {
                    sysResource = new TSysResource();
                    XSSFCell accessCode = xssfRow.getCell(0);
                    XSSFCell available = xssfRow.getCell(1);
                    XSSFCell name = xssfRow.getCell(2);
                    XSSFCell resourceName = xssfRow.getCell(3);
                    XSSFCell resourceType = xssfRow.getCell(4);
                    XSSFCell sortNum = xssfRow.getCell(5);
                    XSSFCell url = xssfRow.getCell(6);
                    XSSFCell systemCode = xssfRow.getCell(7);
                    XSSFCell creatorId = xssfRow.getCell(8);
                    XSSFCell modifierId = xssfRow.getCell(9);
                    XSSFCell status = xssfRow.getCell(10);
                    sysResource = setValue(sysResource, accessCode, available, name, resourceName, resourceType, sortNum, url, systemCode, creatorId, modifierId, status);
                    list.add(sysResource);
                }
            }
        }
        return list;
    }
    /**
     * 设置实体的值
     * @param sysResource 实体资源
     * @param accessCode 对应 access_code
     * @param available 对应 available
     * @param name 对应 name
     * @param resourceName 这里需要去数据库查询，到的parentId
     * @param resourceType 对应 resourceType
     * @param sortNum 对应 sortNum
     * @param url 对应 url
     * @param systemCode 需要去数据库查询，获得systemId
     * @param creatorId 对应 creatorId
     * @param modifierId 对应 modifierId
     * @param status 对应 status
     * @return 返回设置好的实体
     */
    public TSysResource setValue(TSysResource sysResource, Cell accessCode, Cell available, Cell name, Cell resourceName, Cell resourceType, Cell sortNum, Cell url, Cell systemCode, Cell creatorId, Cell modifierId, Cell status) {
        logger.info("accessCode = [" + accessCode + "], available = [" + available + "], name = [" + name + "], resourceName = [" + resourceName + "], resourceType = [" + resourceType + "], sortNum = [" + sortNum + "], url = [" + url + "], systemCode = [" + systemCode + "], creatorId = [" + creatorId + "], modifierId = [" + modifierId + "], status = [" + status + "]");
        sysResource.setAccessCode(getValue(accessCode));
        boolean bool_available = pointHandler(getValue(available)).equals("1") ? true : false;
        sysResource.setAvailable(bool_available);
        sysResource.setName(getValue(name));
        // 根据resourceName 从resource表中获取 parentId
        Long parentId = null;
        if(resourceName != null) {
           int parentIdCount = sysResourceMapper.findCountByNameAndStatus(getValue(resourceName), 1);
           if(parentIdCount == 1) {
               parentId = sysResourceMapper.findIdByNameAndStatus(getValue(resourceName), 1);
               if (parentId == null) {
                   logger.error("从t_sys_resource表中没有找到: " + resourceName + " 的资源");
                   throw new RuntimeException("从t_sys_resource表中没有找到: " + resourceName + " 的资源");
               } else {
                   logger.info("通过 " + resourceName + " 查询 parentId: " + parentId);
               }
           } else {
               logger.error(resourceName+"对应的资源要求只能为1个,但实际上有"+parentIdCount+"个");
               throw new RuntimeException(resourceName+"对应的资源要求只能为1个,但实际上有"+parentIdCount+"个");
           }
        } else {
            logger.error("resourceName 不能为空");
            throw new RuntimeException("resourceName 不能为空");
        }
        sysResource.setParentId(parentId);
        sysResource.setResourceType(getValue(resourceType));
        sysResource.setSortNum(Integer.valueOf(pointHandler(getValue(sortNum))));
        sysResource.setUrl(getValue(url));
        Long systemId;
        if(systemCode != null) {
            int systemIdCount = sysSystemMapper.findCountByCodeAndStatus(getValue(systemCode), 1);
            if(systemIdCount == 1) {
                systemId = sysSystemMapper.findIdByCodeAndStatus(getValue(systemCode), 1);
                if (systemId == null) {
                    logger.error("从t_sys_system表中没有找到: " + systemCode + " 的资源");
                    throw new RuntimeException("从t_sys_system表中没有找到: " + systemCode + " 的资源");
                } else {
                    logger.info("通过 " + systemCode + " 查询 systemId: " + systemId);
                }
            } else {
                logger.error(systemCode + "对应的资源要求只能为1个,但实际上有" + systemIdCount + "个");
                throw new RuntimeException(systemCode + "对应的资源要求只能为1个,但实际上有" + systemIdCount + "个");
            }
        } else {
            logger.error("systemCode 不能为空");
            throw new RuntimeException("systemCode 不能为空");
        }
        sysResource.setSystemId("" + systemId);
        sysResource.setCreatorId(pointHandler(getValue(creatorId)));
        sysResource.setCreatedTime(getDateTime());
        sysResource.setModifierId(pointHandler(getValue(modifierId)));
        sysResource.setModifiedTime(getDateTime());
        sysResource.setStatus(Integer.valueOf(pointHandler(getValue(status))));
        return sysResource;
    }
    /**
     * 根据Cell 获取cell里面的值
     * @param cell xls 的Cell 和 xlsx 的Cell
     * @return 返回cell 里面的值
     */
    @Override
    public String getValue(Cell cell) {
        try {
            if (cell.getCellType() == cell.CELL_TYPE_BOOLEAN) {
                return String.valueOf(cell.getBooleanCellValue());
            } else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
                return String.valueOf(cell.getNumericCellValue());
            } else {
                return String.valueOf(cell.getStringCellValue());
            }
        } catch (Exception e) {
            logger.error("ExcelHandlerServiceImpl.getValue: "+e.toString());
            return "";
        }
    }

    /**
     * 将数据批量插入数据库
     * @param sysResourcesList list集合数据
     * @return 是否执行成功
     */
    @Override
    @Transactional
    public boolean insert(List<TSysResource> sysResourcesList) {
        Boolean result = false;
        if(sysResourcesList != null && sysResourcesList.size() > 0) {
            for(TSysResource sysResource : sysResourcesList) {
                sysResourceMapper.insertSelective(sysResource);
            }
            result = true;
        } else {
            logger.error("TSysResource 的集合数据不能为空");
            throw new RuntimeException("TSysResource 的集合数据不能为空");
        }
        return result;
    }

    /**
     * 将小数1.0(字符串) 转化为整数
     * @param value 从excel读取过来的数值
     * @return 返回数值对应的整数数值
     */
    public String pointHandler(String value) {
        if(value != null && !value.equals("") && value.contains(".")) {
            return value.substring(0, value.indexOf(Constant.POINT));
        }
        return "";
    }

    /**
     * 获取当前日期
     * @return 返回日期
     */
    public Date getDateTime() {
        return new Timestamp(System.currentTimeMillis());
    }

}
