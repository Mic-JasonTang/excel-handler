package com.iclass.excel.mybatis.mapper;


import com.iclass.excel.mybatis.po.TSysResource;
import org.apache.ibatis.annotations.Param;

public interface TSysResourceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TSysResource record);

    int insertSelective(TSysResource record);

    TSysResource selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TSysResource record);

    int updateByPrimaryKey(TSysResource record);

    //( SELECT id FROM t_sys_resource ss WHERE NAME = '家装营销管理' AND STATUS = 1 )
    Long findIdByNameAndStatus(@Param("name") String name, @Param("status") int status);

    int findCountByNameAndStatus(@Param("name") String name, @Param("status") int status);
}