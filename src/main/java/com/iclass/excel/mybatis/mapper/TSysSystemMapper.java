package com.iclass.excel.mybatis.mapper;

import com.iclass.excel.mybatis.po.TSysSystem;
import org.apache.ibatis.annotations.Param;

public interface TSysSystemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TSysSystem record);

    int insertSelective(TSysSystem record);

    TSysSystem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TSysSystem record);

    int updateByPrimaryKey(TSysSystem record);

    //(SELECT id FROM t_sys_system WHERE code = 'jzerp' AND status = 1)
    Long findIdByCodeAndStatus(@Param("code") String code, @Param("status") int status);

    int findCountByCodeAndStatus(@Param("code") String code, @Param("status") int status);
}