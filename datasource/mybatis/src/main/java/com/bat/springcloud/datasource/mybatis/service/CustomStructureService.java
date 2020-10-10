package com.bat.springcloud.datasource.mybatis.service;

import com.bat.springcloud.bussiness.bean.po.CustomStructurePO;

/**
 * {@link CustomStructurePO} Service
 *
 * @author ZhengYu
 * @version 1.0 2020/8/19 13:33
 **/
public interface CustomStructureService {

    Long insertCustomStructure();

    CustomStructurePO queryCustomStructureById(Long id);
}
