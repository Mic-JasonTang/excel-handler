package com.iclass.excel.web.service.api;

import org.apache.poi.ss.usermodel.Cell;

import java.util.Date;

/**
 * excel-handler
 * <p>
 * Created by yang.tang on 2017/2/21 19:15.
 */
public interface ExcelHandlerService<T> {

    /**
     * 读取Excel文件,xls 和 xlsx文件
     * @param path excel文件路径
     * @return 返回对象实体
     */
    public T readExcel(String path);

    /**
     * 读取XLSX 格式的文件
     * @param path excel文件的路径
     * @return 返回对象实体
     */
    public T readXLSX(String path);

    /**
     * 处理XLS 格式的文件
     * @param path excel文件的路径
     * @return 返回对象的实体
     */
    public T readXLS(String path);

    /**
     * 根据Cell 获取cell里面的值
     * @param cell xls 的Cell 和 xlsx 的Cell
     * @return 返回cell 里面的值
     */
    public String getValue(Cell cell);

    /**
     * 将数据批量插入数据库
     * @param t list集合数据
     * @return 是否执行成功
     */
    public boolean insert(T t);

    /**
     * 处理从excel读取过来的数值数据 去掉 .0
     * @param value 原生excel的数值数据
     * @return 原生数值对应的整形数据
     */
    String pointHandler(String value);

    /**
     * 得到一个Date, 使用的是TimeStamp
     * @return 返回一个Date, 与数据库对应
     */
    Date getDateTime();
}
