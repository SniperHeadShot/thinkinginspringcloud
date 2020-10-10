package com.bat.springcloud.datasource.mybatis.dao;

import com.bat.springcloud.bussiness.bean.po.CustomStructurePO;
import org.apache.ibatis.annotations.Param;

/**
 * {@link com.bat.springcloud.bussiness.bean.po.CustomStructurePO} 持久层
 *
 * @author ZhengYu
 * @version 1.0 2020/8/19 13:33
 **/
public interface CustomStructureDao {

    int insertCustomStructure(@Param("customStructurePO") CustomStructurePO customStructurePO);

    CustomStructurePO queryCustomStructureById(Long id);
}
